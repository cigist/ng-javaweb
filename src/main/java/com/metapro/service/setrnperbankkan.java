/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.TrnPerbankkanDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.TrnPerbankkan;
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
@WebServlet(urlPatterns = "/api/setrnperbankkan")
public class setrnperbankkan extends HttpServlet {

    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONObject status = new JSONObject();
    ValidateAccountDao valid = new ValidateAccountDao();
    TrnPerbankkanDao trnBankDao = new TrnPerbankkanDao();
    StringFormat st = new StringFormat();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set response content type
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");

        String accountNumber = request.getParameter("xaccountnumber");
        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");

        String dateCode = CigistDateTimeNow.getDateTimeNow("yyMMdd");
        String trnCode = st.autoCode("");

        TrnPerbankkan trnBank = new TrnPerbankkan();
        trnBank.setTrnNumber("PBK" + dateCode + trnCode);
        trnBank.setCusCode(accountNumber);
        trnBank.setBankCode(request.getParameter("xbankcode"));
        trnBank.setProductCode(request.getParameter("xproductcode"));
        trnBank.setUserTran(username);
        trnBank.setDateTran(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        trnBank.setTimeTran(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        trnBank.setLocationTran(request.getParameter("xlocation"));
        trnBank.setIpAddressTran(request.getRemoteAddr());

        boolean validate = valid.validateToken(username, token);

        if (validate && !username.isEmpty() && !token.isEmpty()) {
            boolean statusPulsa = trnBankDao.booking(trnBank);
            if (statusPulsa) {
                obj.put("MESSAGE", "PENGAJUAN PEMBEIAYAAN ANDA AKAN SEGERA DIPROSES");
                obj.put("STATUS", "OK");
            } else {
                obj.put("MESSAGE", "PENGAJUAN PEMBIAYAAN GAGAL!");
                obj.put("STATUS", "ERROR");
            }

        } else {
            obj.put("MESSAGE", "ACESS DENIED!");
            obj.put("STATUS", "ERROR");
        }
        out.println(obj);
    }

}
