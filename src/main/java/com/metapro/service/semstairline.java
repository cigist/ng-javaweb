/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstAirlineDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstAirline;
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
@WebServlet(urlPatterns = "/api/semstairline")
public class semstairline extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();

    ValidateAccountDao validate = new ValidateAccountDao();
    MstAirlineDao airlineDao = new MstAirlineDao();

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
        String action = request.getParameter("xaction");

        boolean validateAccount = validate.validateToken(username, token);
        if (validateAccount) {
            if (action.equals("insert")) {
                MstAirline airline = new MstAirline();
                airline.setAirlineCode(request.getParameter("xairlinecode").toUpperCase());
                airline.setAirlineName(request.getParameter("xairlinename").toUpperCase());
                airline.setDomestic(Integer.valueOf(request.getParameter("xdomestic").toUpperCase()));
                airline.setLinkIcon("");
                airline.setUserCreate(request.getParameter("xusername"));
                airline.setFlagActive(request.getParameter("xflagactive"));
                airline.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                airline.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                airline.setUserUpdate(request.getParameter("xusername"));
                airline.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                airline.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = airlineDao.insertUpdate(airline);
                if (resultInsert) {
                    result.put("STATUS", "OK");
                    result.put("MESSAGE", "SUCCESS");
                    result.put("DATA", "");
                } else {
                    result.put("STATUS", "ERORR");
                    result.put("MESSAGE", "FAILED PROCESS");
                    result.put("DATA", "");
                }
            } else if (action.equals("list")) {
                Vector<MstAirline> listAirline = new Vector<>();
                listAirline = airlineDao.getAirportAll();
                result.put("STATUS", "OK");
                result.put("MESSAGE", "SUCCESS");
                result.put("DATA", listAirline);
            }else if (action.equals("delete")) {
                MstAirline airline = new MstAirline();
                airline.setAirlineCode(request.getParameter("xairlinecode").toUpperCase());
                airline.setFlagActive("No");
                airline.setUserUpdate(request.getParameter("xusername"));
                airline.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                airline.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = airlineDao.changeAktif(airline);
                if (resultInsert) {
                    result.put("STATUS", "OK");
                    result.put("MESSAGE", "SUCCESS");
                    result.put("DATA", "");
                } else {
                    result.put("STATUS", "ERORR");
                    result.put("MESSAGE", "FAILED PROCESS");
                    result.put("DATA", "");
                }
            }

        } else {
            result.put("STATUS", "ERORR");
            result.put("MESSAGE", "ACCESS DENIED!!");
            result.put("DATA", "");
        }

        out.println(result);
    }
}
