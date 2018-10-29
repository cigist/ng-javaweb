/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstAirportDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstAirline;
import com.metapro.model.MstAirport;
import com.metapro.model.MstPulsa;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
@WebServlet(urlPatterns = "/api/semstairport")
public class semstairport extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();

    ValidateAccountDao validate = new ValidateAccountDao();
    MstAirportDao airpotDao = new MstAirportDao();

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
                MstAirport airport = new MstAirport();
                airport.setAirportCode(request.getParameter("xairportcode").toUpperCase());
                airport.setAirportName(request.getParameter("xairportname").toUpperCase());
                airport.setDomestic(Integer.valueOf(request.getParameter("xdomestic").toUpperCase()));
                airport.setUserCreate(request.getParameter("xusername"));
                airport.setFlagActive(request.getParameter("xflagactive"));
                airport.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                airport.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                airport.setUserUpdate(request.getParameter("xusername"));
                airport.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                airport.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = airpotDao.insertUpdate(airport);
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
                Vector<MstAirport> listAirport = new Vector<>();
                listAirport = airpotDao.getAirportAll();
                result.put("STATUS", "OK");
                result.put("MESSAGE", "SUCCESS");
                result.put("DATA", listAirport);
            } else if (action.equals("delete")) {
                MstAirport airport = new MstAirport();
                airport.setAirportCode(request.getParameter("xairportcode").toUpperCase());
                airport.setFlagActive("No");
                airport.setUserUpdate(request.getParameter("xusername"));
                airport.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                airport.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = airpotDao.changeAktif(airport);
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
