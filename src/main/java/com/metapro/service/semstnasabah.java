/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.CigistEncrypDecryp;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstCustomerDao;
import com.metapro.dao.MstNasabahDao;
import com.metapro.dao.MstUserDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstCustomer;
import com.metapro.model.MstNasabah;
import com.metapro.model.MstUser;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
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
@WebServlet(urlPatterns = "/api/semstnasabah")
public class semstnasabah extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    MstNasabahDao nasabahDao = new MstNasabahDao();
    ValidateAccountDao valiDao = new ValidateAccountDao();
    MstCustomerDao service = new MstCustomerDao();
    MstUserDao userDao = new MstUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");

        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");
        boolean validate = valiDao.validateToken(username, token);
        Vector<MstNasabah> dataNasabah = new Vector<>();
        if (validate && !token.isEmpty() && !username.isEmpty()) {
            dataNasabah = nasabahDao.getAllData();
            obj.put("LIST_NASABAH", dataNasabah);
            obj.put("MESSAGE", "");
            obj.put("STATUS", "OK");
        } else {
            obj.put("LIST_NASABAH", dataNasabah);
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
        String imageUrl = request.getParameter("ximage");
        File myFile = new File(imageUrl);
        String image = myFile.getName();

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
        customer.setLocation(request.getParameter("xloanglat"));
        customer.setIpAddress(request.getRemoteAddr());
        customer.setImgUrl("img/" + image);
        customer.setAddress(request.getParameter("xaddress"));

        BigDecimal vNominal = new BigDecimal("0");
        String pin = st.activCode();
        String pincode=CigistEncrypDecryp.encrypt(pin);

        MstNasabah mstNasabah = new MstNasabah();
        mstNasabah.setAccountNumber(dateCode + cusCode);
        mstNasabah.setAccountName(name);
        mstNasabah.setPin(pincode);
        mstNasabah.setAccountType("N");
        mstNasabah.setBiginingBalance(vNominal);
        mstNasabah.setEndingBalance(vNominal);
        mstNasabah.setFlagActive("Yes");
        mstNasabah.setUserCreate(request.getParameter("xusername"));
        mstNasabah.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        mstNasabah.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        mstNasabah.setLocationCreate(request.getParameter("xloanglat"));
        mstNasabah.setIpAddressCreate(request.getRemoteAddr());
        mstNasabah.setUserUpdate(request.getParameter("xusername"));
        mstNasabah.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        mstNasabah.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        mstNasabah.setLocationUpdate(request.getParameter("xloanglat"));
        mstNasabah.setIpAddressUpdate(request.getRemoteAddr());

        MstUser user = new MstUser();
        String xpassword = st.activCode();
        String password =CigistEncrypDecryp.encrypt(xpassword);
        user.setUserCode(st.autoCode("USR"));
        user.setUsername(email);
        user.setPassword(password);
        user.setFlagActive("Yes");
        user.setUserCreate(request.getParameter("xusername"));
        user.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        user.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        user.setUserUpdate(request.getParameter("xusername"));
        user.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        user.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        user.setLocation(request.getParameter("xlocation"));
        user.setIpAddress(request.getRemoteAddr());

        if (!name.isEmpty() && !email.isEmpty() && !phone.isEmpty()) {
            String insert = userDao.isertUpdate(user);
            String insertNasabha =nasabahDao.isertUpdate(mstNasabah);
            String insertCustomer=service.isertUpdate(customer);
            if (insert.equals("OK".trim())&& insertNasabha.equals("OK".trim()) && insertCustomer.equals("OK".trim())) {
                //JSON
                status.put("STATUS", insert);
                status.put("MESSAGE", "PENDAFTARAN BERHASIL SILAHKAN CEK EMAIL ");
                out.println(status);
                out.close();
                
                String depassword =CigistEncrypDecryp.decrypt(password);
                String depin = CigistEncrypDecryp.decrypt(pincode);
//                SEND MAIL
                Mailer.send(email, "Pendaftaran Nasabah berhasil",
                        " Username = " + email + " \n"
                        + " Password = " + depassword + " \n"
                        + " Account Number = " + dateCode + cusCode + "\n"
                        + " pin = " + depin + " \n"
                        + "\n"
                        + "\n"
                        + "\n"
                        + "Terima Kasih \n"
                        + " Metatech");
            } else {
                status.put("STATUS", insert);
                status.put("MESSAGE", "PENDAFTARAN GAGAL! ");

                out.println(status);
            }
        } else {
            status.put("STATUS", "ERORR");
            status.put("MESSAGE", "PENDAFTARAN GAGAL! ");

            out.println(status);
        }

    }

}
