/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.TrnAgenLocationDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.TrnAgenLocation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
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
@WebServlet(urlPatterns = "/api/sewhereagen")
public class sewhereagen extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();

    ValidateAccountDao valid = new ValidateAccountDao();
    TrnAgenLocationDao service = new TrnAgenLocationDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    public ServletConfig getServletConfig() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("xusername");
        String token = request.getParameter("xtoken");

//        TrnAgenLocation agen = new TrnAgenLocation();
//        agen.setAgenAccountNumber(request.getParameter("xaccountnumber"));
//        agen.setLocation(request.getParameter("xlocation"));
//        agen.setImei(request.getParameter("ximei"));
//        agen.setLocationDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
//        agen.setLocationTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        boolean validate = valid.validateToken(username, token);
        if (validate && !username.isEmpty() && !token.isEmpty()) {
            Vector<TrnAgenLocation> data=new Vector<>();
            data=service.getAgenLocation();
            result.put("STATUS", "OK");
            result.put("DATA", data);
        } else {
            result.put("STATUS", "ERROR");
            result.put("DATA", "NULL");
        }

        out.println(result);

    }

    @Override
    public String getServletInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
