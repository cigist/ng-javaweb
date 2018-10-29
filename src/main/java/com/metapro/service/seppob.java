/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstPpobDao;
import com.metapro.dao.MstPulsaDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstPpob;
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
@WebServlet(urlPatterns = "/api/seppob")
public class seppob extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();

    ValidateAccountDao validate = new ValidateAccountDao();
    MstPpobDao ppobDao = new MstPpobDao();

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
            if (action.equals("vendor")) {
                Vector<MstPpob> listPpob = new Vector<>();
                listPpob = ppobDao.getPpobByVendor(request.getParameter("xvendor"), request.getParameter("xtype").toUpperCase());
                result.put("STATUS", "OK");
                result.put("DATA", listPpob);
            } else if (action.equals("type")) {
                Vector<MstPpob> listPpob = new Vector<>();
                listPpob = ppobDao.getPpobByType(request.getParameter("xtype").toUpperCase());
                result.put("STATUS", "OK");
                result.put("DATA", listPpob);
            } else if (action.equals("change")) {
                String nominal = request.getParameter("xprice");
                BigDecimal vNominal = new BigDecimal(nominal);
                MstPpob ppob = new MstPpob();
                ppob.setVendorCode(request.getParameter("xvendorcode"));
                ppob.setPpobCode(request.getParameter("xppobcode"));
                ppob.setPrice(vNominal);
                ppob.setDiscount(Float.valueOf(request.getParameter("xdiscount")));
                ppob.setUserUpdate(username);
                ppob.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                ppob.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                boolean status = ppobDao.changePrice(ppob);
                if (status) {
                    result.put("STATUS", "OK");
                } else {
                    result.put("STATUS", "ERROR");
                }
            } else {
                Vector<MstPpob> listPpob = new Vector<>();
                listPpob = ppobDao.getAllPpob();
                result.put("STATUS", "OK");
                result.put("DATA", listPpob);
            }

        } else {
            result.put("STATUS", "ERORR");
            result.put("MESSAGE", "ACCESS DENIED!!");
            result.put("DATA", "");
        }

        out.println(result);
    }
}
