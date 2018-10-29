/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistEncrypDecryp;
import com.metapro.dao.MstNasabahDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.CheckSaldo;
import com.metapro.model.TrnNasabah;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Random;
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
@WebServlet(urlPatterns = "/api/serekening")
public class serekening extends HttpServlet {

    Random rand = new Random();
    StringBuilder sBuildz = new StringBuilder();
    int number;
    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    MstNasabahDao service = new MstNasabahDao();
    ValidateAccountDao valiDao = new ValidateAccountDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        number = rand.nextInt(50) + 1;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");
        String xaction = request.getParameter("xaction");
        String accountNumber = request.getParameter("xaccountnumber");
        String xpin = request.getParameter("xpin");
        String pin = CigistEncrypDecryp.encrypt(xpin);
        boolean validate = valiDao.validateToken(username, token);
        boolean validateAccount = valiDao.checkAccount(accountNumber, pin);

        if (validate && validateAccount && !token.isEmpty() && !username.isEmpty()) {
            if (xaction.equals("rekening")) {
                Vector<CheckSaldo> sisaSaldo = new Vector<>();
                Vector<TrnNasabah> rekening = new Vector<>();
                sisaSaldo = service.checkSaldo(accountNumber, pin);
                rekening = service.cekRekening(accountNumber);
                if (sisaSaldo != null) {
                    obj.put("SALDO", sisaSaldo);
                    obj.put("DATA", rekening);
                    obj.put("STATUS", "OK");
                } else {
                    obj.put("DATA", "");
                    obj.put("SALDO", "");
                    obj.put("STATUS", "ERROR");
                }
            } else if (xaction.equals("mutasi")) {
                Vector<TrnNasabah> mutasi = new Vector<>();
                String datefrom = request.getParameter("xdatefrom");
                String dateto = request.getParameter("xdateto");
                BigDecimal sisaSaldo = service.getSaldo(accountNumber, pin);
                mutasi = service.mutasiRekening(accountNumber, datefrom, dateto);
                BigDecimal totalTransaksi = service.totalTransaki(accountNumber);
                BigDecimal nominalValue;
                if (mutasi.isEmpty()) {
                    nominalValue = null;
                } else {
                    nominalValue = sisaSaldo.add(totalTransaksi);
                }

                if (mutasi != null) {
                    obj.put("SALDO", sisaSaldo);
                    obj.put("DATA", mutasi);
                    obj.put("SALDO AWAL", nominalValue);
                    obj.put("STATUS", "OK");
                } else {
                    obj.put("DATA", "");
                    obj.put("SALDO", "");
                    obj.put("STATUS", "ERROR");
                    obj.put("SALDO AWAL", "");
                }
            } else {
                obj.put("DATA", "Acsess Denied !");
                obj.put("SALDO", "");
                obj.put("STATUS", "ERROR");
            }
        } else {
            obj.put("DATA", "Acsess Denied !");
            obj.put("SALDO", "");
            obj.put("STATUS", "ERROR");
        }

        out.println(obj);

    }

}
