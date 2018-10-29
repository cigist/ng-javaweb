/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistEncrypDecryp;
import com.metapro.dao.MstNasabahDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.CheckSaldo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Vector;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
@WebServlet(urlPatterns = "/api/sechecksaldo")
public class sechecksaldo extends HttpServlet {

    Random rand = new Random();
    StringBuilder sBuildz = new StringBuilder();
    int number;
    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    MstNasabahDao service = new MstNasabahDao();
    ValidateAccountDao valiDao = new ValidateAccountDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        number = rand.nextInt(50) + 1;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        
        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");
        boolean validate = valiDao.validateToken(username, token);

        Vector<CheckSaldo> dataCs = new Vector<>();
        String accountNumber = request.getParameter("xaccountnumber");
        String xpin = request.getParameter("xpin");
        String pin=CigistEncrypDecryp.encrypt(xpin);
        dataCs = service.checkSaldo(accountNumber, pin);

        if (validate && !token.isEmpty() && !username.isEmpty()) {
            if (dataCs != null) {
                obj.put("MESSAGE", "SISA SALDO");
                obj.put("SALDO", dataCs);
                obj.put("STATUS", "OK");
            } else {
                obj.put("MESSAGE", "CHECK SALDO GAGAL");
                obj.put("SALDO", "");
                obj.put("STATUS", "ERROR");
            }
        } else {
            obj.put("MESSAGE", "Acsess Denied !");
            obj.put("SALDO", "");
            obj.put("STATUS", "ERROR");
        }

        out.println(obj);

    }

}
