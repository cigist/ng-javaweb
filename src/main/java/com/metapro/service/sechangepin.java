/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.CigistEncrypDecryp;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstNasabahDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstNasabah;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
@WebServlet(urlPatterns = "/api/sechangepin")
public class sechangepin extends HttpServlet {

    StringFormat st = new StringFormat();
    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONObject status = new JSONObject();
    MstNasabahDao service = new MstNasabahDao();
    ValidateAccountDao valiDao = new ValidateAccountDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");
        String accountNumber = request.getParameter("xaccountnumber");
        String xoldpin = request.getParameter("xoldpin");
        String xnewpin = request.getParameter("xnewpin");
        
        String oldpin = CigistEncrypDecryp.encrypt(xoldpin);
        String newpin = CigistEncrypDecryp.encrypt(xnewpin);

        MstNasabah nasabah = new MstNasabah();
        nasabah.setAccountNumber(accountNumber);
        nasabah.setPin(newpin);
        nasabah.setUserUpdate(username);
        nasabah.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        nasabah.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        nasabah.setLocationUpdate(request.getParameter("xlocation"));
        nasabah.setIpAddressUpdate(request.getRemoteAddr());

        boolean validate = valiDao.validateToken(username, token);
        if (validate && !token.isEmpty() && !username.isEmpty()) {
            boolean pinValidate = valiDao.checkAccount(accountNumber, oldpin);
            if (pinValidate) {
                String insert = service.changePin(nasabah);
                if (insert.equals("OK")) {
                    status.put("STATUS", insert);
                } else {
                    status.put("STATUS", insert);
                }
            } else {
                status.put("STATUS", "PIN LAMA ANDA SALAH");
            }
        } else {
            status.put("STATUS", "FAILED !");
        }

        out.println(status);
    }

}
