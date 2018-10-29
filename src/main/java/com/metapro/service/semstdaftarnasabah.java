/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstDaftarNasabahDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstDaftarNasabah;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
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
@WebServlet(urlPatterns = "/api/semstdaftarnasabah")
public class semstdaftarnasabah extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    MstDaftarNasabahDao nasabahDao = new MstDaftarNasabahDao();
    ValidateAccountDao valiDao = new ValidateAccountDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        String accountnumber = request.getParameter("xaccountnumber").trim().toUpperCase();
        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");
        String action = request.getParameter("xaction");

        boolean validate = valiDao.validateToken(username, token);

        if (action.equals("list")) {
            if (validate && !token.isEmpty() && !username.isEmpty()) {
                Vector<MstDaftarNasabah> dataNasabah = new Vector<>();
                dataNasabah = nasabahDao.getDataNasabahAll(accountnumber);
                obj.put("LIST_NASABAH", dataNasabah);
                obj.put("MESSAGE", "");
                obj.put("STATUS", "OK");
            } else {
                obj.put("LIST_NASABAH", "");
                obj.put("MESSAGE", "Acsess Denied !");
                obj.put("STATUS", "ERROR");
            }

            out.println(obj);
        } else if (action.equals("insert")) {
              String accountNumberTo = request.getParameter("xaccountnumberto").trim();
            if (!accountnumber.isEmpty() && !accountNumberTo.isEmpty()) {
                MstDaftarNasabah mstNasabah = new MstDaftarNasabah();
                mstNasabah.setAccountNumber(accountnumber);
                mstNasabah.setAccountNumberTo(accountNumberTo);
                mstNasabah.setNasabahName(request.getParameter("xnasabahname"));
                mstNasabah.setBankCode(request.getParameter("xbankcode"));
                mstNasabah.setFlagActive("Yes");
                mstNasabah.setUserCreate(request.getParameter("xusername"));
                mstNasabah.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                mstNasabah.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean insert = nasabahDao.insertUpdate(mstNasabah);
                String email=valiDao.getAccountEmail(accountnumber);
                if (insert) {
                    //JSON
                    status.put("STATUS", insert);
                    status.put("MESSAGE", "PENDAFTARAN BERHASIL SILAHKAN CEK EMAIL ");
                    out.println(status);
                    out.close();

//                SEND MAIL
                    Mailer.send(email, "Pendaftaran Nasabah berhasil",
                            " No Rekening = " + accountNumberTo + " \n"
                            + " Atas Nama = " + mstNasabah.getNasabahName() + " \n"
                            + "\n"
                            + "\n"
                            + "\n"
                            + "Terima Kasih \n"
                            + " Metatech");
                } else {
                    status.put("STATUS", "OK");
                    status.put("MESSAGE", "PENDAFTARAN GAGAL! ");

                    out.println(status);
                }
            } else {
                status.put("STATUS", "ERORR");
                status.put("MESSAGE", "PENDAFTARAN GAGAL! ");

                out.println(status);
            }
        } else {

        }

    }

}
