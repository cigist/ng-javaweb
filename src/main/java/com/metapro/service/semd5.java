/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistEncrypDecryp;
import com.metapro.dao.LoginDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = "/api/semd5")
public class semd5 extends HttpServlet {

    private static final long serialVersionUID = 1L;
    JSONObject result = new JSONObject();
    LoginDao loginDao = new LoginDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            PrintWriter out = response.getWriter();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Cache-Control", "public");

            String xPass = null;

            String password = request.getParameter("xpassword");
            String xaction = request.getParameter("xaction").toLowerCase();
            if (xaction.equals("de")) {
                xPass = CigistEncrypDecryp.decrypt(password);
            } else if (xaction.equals("en")) {
                xPass = CigistEncrypDecryp.encrypt(password);
            } else {

            }
            if (!password.isEmpty()) {
                result.put("STATUS", "OK");
                result.put("INPUT", password);
                result.put("RESULT", xPass);
            } else {
                result.put("STATUS", "ERORR");
            }
            out.println(result);
        } catch (Exception ex) {
            Logger.getLogger(semd5.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
