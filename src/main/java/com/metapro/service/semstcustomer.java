/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstCustomerDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstCustomer;
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
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
@WebServlet(urlPatterns = "/api/semstcustomer")
public class semstcustomer extends HttpServlet {

    private String message;
    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    MstCustomerDao service = new MstCustomerDao();
    ValidateAccountDao valiDao = new ValidateAccountDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        response.addHeader("Access-Control-Allow-Origin", "*");

        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");
        boolean validate = valiDao.validateToken(username, token);

        if (validate && !token.isEmpty() && !username.isEmpty()) {
            Vector<MstCustomer> dataCus = new Vector<>();
            dataCus = service.getAllData();
            obj.put("LIST_CUSTOMER", dataCus);
            obj.put("STATUS", "OK");
        } else {
            obj.put("MESSAGE", "Acsess Denied !");
            obj.put("STATUS", "ERROR");
        }
        out.println(obj);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("xfullname").trim().toUpperCase();
        String email = request.getParameter("xemail").trim();
        String phone = request.getParameter("xphonenumber").trim();

        String dateCode = CigistDateTimeNow.getDateTimeNow("yyMMdd");
        String cusCode = st.autoCode("");
        MstCustomer customer = new MstCustomer();
        customer.setCusCode(dateCode + cusCode);
        customer.setFullname(name);
        customer.setEmail(email);
        customer.setPhoneNumber(phone);
        customer.setActivationCode(st.activCode());
        customer.setFlagActive("Yes");
        customer.setUserCreate("admin");
        customer.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        customer.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        customer.setUserUpdate("admin");
        customer.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        customer.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        customer.setLocation(request.getParameter("xlocation"));
        customer.setIpAddress(request.getRemoteAddr());

        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");
        boolean validate = valiDao.validateToken(username, token);

        if (validate && !name.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
            String insert = service.isertUpdate(customer);
            if (insert.equals("OK".trim())) {
                status.put("STATUS", insert);
                status.put("MESSAGE", "PENDAFTARAN BERHASIL ");
            } else {
                status.put("STATUS", insert);
                status.put("MESSAGE", "PENDAFTARAN GAGAL! ");
            }
        } else {
            status.put("STATUS", "ERORR");
            status.put("MESSAGE", "PENDAFTARAN GAGAL! ");
        }

        out.println(status);
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
