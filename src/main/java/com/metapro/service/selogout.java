/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistAutoGenerate;
import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.LoginDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.TrnUserLogin;
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
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
@WebServlet(urlPatterns = "/api/selogout")
public class selogout extends HttpServlet {

    private static final long serialVersionUID = 1L;
    JSONObject result = new JSONObject();
    LoginDao loginDao = new LoginDao();

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
        boolean status =loginDao.updateStatusLogout(username, 0);
        if (status) {
            result.put("STATUS", "OK");
        } else {
            result.put("STATUS", "ERORR");
        }
        out.println(result);
    }
}
