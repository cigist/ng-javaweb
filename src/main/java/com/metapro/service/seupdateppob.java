/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistEncrypDecryp;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.vendor.ServiceXpay;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Irwan Cigist <cigist.developer@gmail.com>
 */
@WebServlet(urlPatterns = "/api/seupdateppob")
public class seupdateppob extends HttpServlet {

    ValidateAccountDao validate = new ValidateAccountDao();
    JSONObject result = new JSONObject();
    JSONArray data = new JSONArray();
    ServiceXpay apiXpay = new ServiceXpay();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Cache-Control", "public");
//
//        String username = request.getParameter("xusername");
//        String token = request.getParameter("xtoken");

//        boolean validateAccount = validate.validateToken(username, token);
//        if (validateAccount && !token.isEmpty() && !username.isEmpty()) {
            String dataResponse = apiXpay.getProductPBOB("XPM1760832", "root");
            result.put("STATUS", dataResponse);
//        } else {
//            result.put("STATUS", "ERROR");;
//            result.put("DATA", "UID or Password not valid !");
//        }

        out.println(result);
    }
}
