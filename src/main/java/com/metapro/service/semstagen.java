/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstAgenDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstVendor;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = "/api/semstagen")
public class semstagen extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();

    ValidateAccountDao validate = new ValidateAccountDao();
    MstAgenDao agenDao = new MstAgenDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control", "public");

        String username = request.getParameter("xusername");
        String token = request.getParameter("xtoken");
        String action = request.getParameter("xaction").toLowerCase();
        String accountNumber = request.getParameter("xaccountnumber");
        boolean validateAccount = validate.validateToken(username, token);
        if (validateAccount) {
            if (action.equals("setagen")) {
                boolean status = agenDao.setAgen(accountNumber, "A");
                if (status) {
                    result.put("STATUS", "OK");
                    result.put("MESSAGE", "SELAMAT ANDA TELAH MENJADI AGEN");
                } else {
                    result.put("STATUS", "ERORR");
                    result.put("MESSAGE", "SALDO ANDA KURANG DARI Rp 25.000.0000,-");
                }
            } else if (action.equals("list")) {

            }

        } else {
            result.put("STATUS", "ERORR");
            result.put("MESSAGE", "ACCESS DENIED!!");
        }
        out.println(result);
    }
}
