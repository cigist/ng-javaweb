/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.CigistEncrypDecryp;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstUserDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstUser;
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
@WebServlet(urlPatterns = "/api/sechangepassword")
public class sechangepassword extends HttpServlet {

    StringFormat st = new StringFormat();
    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONObject status = new JSONObject();
    MstUserDao service = new MstUserDao();
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
        String xoldpassword = request.getParameter("xoldpassword");
        String password = request.getParameter("xnewpassword");

        String oldpassword = CigistEncrypDecryp.encrypt(xoldpassword);
        String newPassword = CigistEncrypDecryp.encrypt(password);
        

        MstUser user = new MstUser();
        user.setUsername(request.getParameter("xusername").trim());
        user.setPassword(newPassword);
        user.setUserUpdate(username);
        user.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        user.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        user.setLocation(request.getParameter("xlocation"));
        user.setIpAddress(request.getRemoteAddr());

        boolean validate = valiDao.validateToken(username, token);
        if (validate && !token.isEmpty() && !username.isEmpty()) {
            boolean passValidate = service.checkPassword(username, oldpassword);
            if (passValidate) {
                String insert = service.changePassword(user);
                if (insert.equals("OK")) {
                    status.put("STATUS", insert);
                } else {
                    status.put("STATUS", insert);
                }
            } else {
                status.put("STATUS", "PASSWORD LAMA ANDA SALAH");
            }
        } else {
            status.put("STATUS", "FAILED");
        }

        out.println(status);

    }
}

