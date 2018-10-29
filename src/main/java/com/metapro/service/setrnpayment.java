/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.metapro.dao.ValidateAccountDao;
import com.metapro.dao.TrnPaymentDao;
import com.metapro.model.TrnPayment;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
@WebServlet(urlPatterns = "/api/setrnpayment")
public class setrnpayment extends HttpServlet{
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        response.addHeader("Access-Control-Allow-Origin","*");
        response.addHeader("Cache-Control","public");
        
        String accoutNumber = request.getParameter("xaccountnumber");
        String pin = request.getParameter("xpin");

        TrnPayment  payment = new TrnPayment();
    

        boolean validateAccount = validateAccountDao.checkAccount(accoutNumber,pin);
        if (validateAccount) {
            paymentDao.isertUpdate(payment);
            result.put("STATUS", "OK");
            result.put("MESSAGE", "PAYMENT SUCCSESS!");
        } else {
            result.put("STATUS", "ERORR");
            result.put("MESSAGE", "WRONG ACCOUNT NUMBER OR PIN!");
        }
        out.println(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
