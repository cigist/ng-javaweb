/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstPulsaDao;
import com.metapro.dao.ValidateAccountDao;
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
@WebServlet(urlPatterns = "/api/semstpulsa")
public class semstpulsa extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();

    ValidateAccountDao validate = new ValidateAccountDao();
    MstPulsaDao pulsaDao = new MstPulsaDao();

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
        String xprice = request.getParameter("xprice");
        String xdiscount = request.getParameter("xdiscount");
        String action = request.getParameter("xaction");

        boolean validateAccount = validate.validateToken(username, token);
        if (validateAccount) {
            if (action.equals("insert")) {
                String xamount = request.getParameter("xamount");
//                String xvendorPrice = request.getParameter("xvendorprice");
                
                BigDecimal amount = new BigDecimal(xamount);
//                BigDecimal vendorprice = new BigDecimal(xvendorPrice);
                BigDecimal price = new BigDecimal(xprice);
//                Float discount = new Float(xdiscount);
                
                MstPulsa pulsa = new MstPulsa();
                pulsa.setVendorCode(request.getParameter("xvendorcode").toUpperCase());
                pulsa.setPulsaCode(request.getParameter("xpulsacode").toUpperCase());
                pulsa.setPulsaName(request.getParameter("xpulsaname").toUpperCase());
                pulsa.setAmount(amount);
//                pulsa.setVendorPrice(vendorprice);
                pulsa.setPrice(price);
//                pulsa.setDiscount(discount);
                pulsa.setUserCreate(request.getParameter("xusername"));
                pulsa.setFlagActive(request.getParameter("xflagactive"));
                pulsa.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                pulsa.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                pulsa.setUserUpdate(request.getParameter("xusername"));
                pulsa.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                pulsa.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = pulsaDao.insertUpdate(pulsa);
                if (resultInsert) {
                    result.put("STATUS", "OK");
                    result.put("MESSAGE", "SUCCESS");
                    result.put("DATA", "");
                } else {
                    result.put("STATUS", "ERORR");
                    result.put("MESSAGE", "FAILED PROCESS");
                    result.put("DATA", "");
                }
            } else if (action.equals("change")) {
                BigDecimal price1 = new BigDecimal(xprice);
                Float discount1 = new Float(xdiscount);
                MstPulsa pulsa = new MstPulsa();
                pulsa.setVendorCode(request.getParameter("xvendorcode").toUpperCase());
                pulsa.setPulsaCode(request.getParameter("xpulsacode").toUpperCase());
                pulsa.setPrice(price1);
                pulsa.setDiscount(discount1);
                pulsa.setUserUpdate(request.getParameter("xusername"));
                pulsa.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                pulsa.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = pulsaDao.changePrice(pulsa);
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
                Vector<MstPulsa> listPulsa = new Vector<>();
                listPulsa = pulsaDao.getPulsaAll(request.getParameter("xpulsatype").toUpperCase());
                result.put("STATUS", "OK");
                result.put("MESSAGE", "SUCCESS");
                result.put("DATA", listPulsa);
            }

        } else {
            result.put("STATUS", "ERORR");
            result.put("MESSAGE", "ACCESS DENIED!!");
            result.put("DATA", "");
        }

        out.println(result);
    }
}
