/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstPaketDataDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstPaketData;
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
@WebServlet(urlPatterns = "/api/semstpaketdata")
public class semstpaketdata extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();

    ValidateAccountDao validate = new ValidateAccountDao();
    MstPaketDataDao dataDao = new MstPaketDataDao();

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
        String paketdatacode =request.getParameter("xpaketdatacode").toUpperCase();
        String vendorcode = request.getParameter("xvendorcode").toLowerCase();

        boolean validateAccount = validate.validateToken(username, token);
        if (validateAccount) {
            if (action.equals("insert")) {
                BigDecimal price = new BigDecimal(xprice);

                
                MstPaketData data = new MstPaketData();
                data.setVendorCode(vendorcode);
                data.setPaketDataCode(paketdatacode);
                data.setPaketDataName(request.getParameter("xpaketdataname").toUpperCase());
                data.setKuota(request.getParameter("xkuota"));
//                data.setVendorPrice(vendorPrice);
                data.setPrice(price);
//                data.setDiscount(discount);
                data.setUserCreate(request.getParameter("xusername"));
                data.setFlagActive(request.getParameter("xflagactive"));
                data.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                data.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                data.setUserUpdate(request.getParameter("xusername"));
                data.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                data.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = dataDao.insertUpdate(data);
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
                MstPaketData data = new MstPaketData();
                data.setVendorCode(vendorcode);
                data.setPaketDataCode(paketdatacode);
                data.setPrice(price1);
                data.setDiscount(discount1);
                data.setUserUpdate(request.getParameter("xusername"));
                data.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                data.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = dataDao.changePrice(data);
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
                Vector<MstPaketData> listPulsa = new Vector<>();
                listPulsa = dataDao.getPaketDataAll();
                result.put("STATUS", "OK");
                result.put("MESSAGE", "SUCCESS");
                result.put("DATA", listPulsa);
            }else if (action.equals("data")) {
                Vector<MstPaketData> listPulsa = new Vector<>();
                listPulsa = dataDao.getDataByVendor(vendorcode);
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
