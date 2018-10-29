/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistEncrypDecryp;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.dao.TrnPaymentDao;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = "/api/sevalidateaccount")
public class sevalidateaccount extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private int countCall;

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    ValidateAccountDao validateAccountDao = new ValidateAccountDao();
    TrnPaymentDao paymentDao = new TrnPaymentDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        countCall = 0;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control", "public");
        PrintWriter out = response.getWriter();

        String accoutNumber = request.getParameter("xaccountnumber");
        String xpin = request.getParameter("xpin");
        String pin = CigistEncrypDecryp.encrypt(xpin);
        boolean validateAccount = validateAccountDao.checkAccount(accoutNumber, pin);
        if (validateAccount) {
            result.put("STATUS", "OK");
        } else {
            result.put("STATUS", "ERORR");
        }
        out.println(result);
    }
}
