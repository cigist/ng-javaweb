/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistAutoGenerate;
import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.CigistEncrypDecryp;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.LoginDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstNasabah;
import com.metapro.model.TrnUserLogin;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = "/api/selogin")
public class selogin extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();
    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    ValidateAccountDao validateAccountDao = new ValidateAccountDao();
    LoginDao loginDao = new LoginDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control", "public");

        String trnUSerCode = st.autoCode("");        
        String username = request.getParameter("xusername");
        String xpassword = request.getParameter("xpassword");
        String password = CigistEncrypDecryp.encrypt(xpassword);
        TrnUserLogin tlogin = new TrnUserLogin();
        tlogin.setTrnLoginCode(trnUSerCode);
        tlogin.setUsername(username);
        tlogin.setSessionID(session.getId());
        tlogin.setToken(CigistAutoGenerate.genToken());
        tlogin.setDateLogin(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        tlogin.setTimeLogin(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        tlogin.setLocation(request.getParameter("xlocation"));
        tlogin.setIpAddress(request.getRemoteAddr());
        tlogin.setLoginFrom(request.getParameter("xloginfrom"));
        boolean validateAccount = loginDao.login(username, password);
        if (validateAccount) {
            Vector<MstNasabah> listNasabah = new Vector<>();
            listNasabah = loginDao.getDataUser(username);
            loginDao.insetUserLogin(tlogin);
            loginDao.updateStatusLogin(username, password, 1);
            String accountNumber = loginDao.getAccountNumber(username);
            result.put("STATUS", "OK");
            result.put("MESSAGE", "LOGIN SUCCSESS!");
            result.put("TOKEN", tlogin.getToken());
            result.put("ACCOUNT", accountNumber);
            result.put("DATA", listNasabah);
            
        } else {
            result.put("STATUS", "ERORR");
            result.put("MESSAGE", "WRONG USERNAME OR PASSWORD!");
            result.put("TOKEN", "");
            result.put("ACCOUNT", "");
            result.put("DATA", "");
        }
        out.println(result);
    }
}
