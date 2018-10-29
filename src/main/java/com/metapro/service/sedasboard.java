/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.StringFormat;
import com.metapro.dao.DasboardDao;
import com.metapro.dao.LoginDao;
import com.metapro.dao.MstCustomerDao;

import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstCustomer;
import com.metapro.model.TrnUserLogin;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
@WebServlet(urlPatterns = "/api/sedasboard")
public class sedasboard extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    LoginDao login = new LoginDao();
    ValidateAccountDao valiDao = new ValidateAccountDao();
    DasboardDao dasboard = new DasboardDao();
    MstCustomerDao cusDao = new MstCustomerDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        
        String xemail = request.getParameter("xemail");
        
        Vector<MstCustomer> listCustomer = new Vector<>();
        listCustomer =cusDao.getDataCustomer(xemail);
        BigDecimal totalTransaksi = dasboard.totalTransaki();
        BigDecimal totalPembayaran = dasboard.totalPembayaran();
        BigDecimal totalPembelianPulsa = dasboard.totalPembelianPulsa();
        BigDecimal totalPembelianData = dasboard.totalPembelianData();
        BigDecimal totalPembelian=totalPembelianPulsa.add(totalPembelianData);
        int totalNasabah = dasboard.totalNasabah();
        String xaction = request.getParameter("xaction");
        if (xaction.equals("dasboard")) {
            status.put("DATA_USER", listCustomer);
            status.put("TOTAL_NASABAH", totalNasabah);
            status.put("TOTAL_PEMBELIAN", totalPembelian);
            status.put("TOTAL_PEMBAYARAN", totalPembayaran);
            status.put("TOTAL_TRANSAKI", totalTransaksi);
        } else if (xaction.equals("login")) {
            Vector<TrnUserLogin> dataNasabah = new Vector<>();
            dataNasabah = login.getAllData();
            obj.put("LIST_LOGIN", dataNasabah);
            obj.put("STATUS", "OK");
        } else {

        }
        out.println(status);
    }

}
