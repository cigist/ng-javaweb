/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstNasabahDao;
import com.metapro.dao.TrnNasabahDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.TrnNasabah;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
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
@WebServlet(urlPatterns = "/api/sesetornasabah")
public class sesetornasabah extends HttpServlet {

    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    MstNasabahDao service = new MstNasabahDao();
    TrnNasabahDao trnDao = new TrnNasabahDao();
    ValidateAccountDao valid = new ValidateAccountDao();
    StringFormat st = new StringFormat();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    public ServletConfig getServletConfig() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set response content type
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");

        String accountNumber = request.getParameter("xaccountnumber");
        String nominal = request.getParameter("xnominal");
        BigDecimal vNominal = new BigDecimal(nominal);
        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");

        String dateCode = CigistDateTimeNow.getDateTimeNow("yyMMdd");
        String trnCode = st.autoCode("");
        BigDecimal fee = new BigDecimal("5000");

        TrnNasabah trnNasabah = new TrnNasabah();
        trnNasabah.setTran_number("STR" + dateCode + trnCode);
        trnNasabah.setAccountNumber(accountNumber);
        trnNasabah.setTrnType("STR");
        trnNasabah.setAmount(vNominal);
        trnNasabah.setTranFee(new BigDecimal("5000"));
        trnNasabah.setNote(request.getParameter("xketerangan"));
        trnNasabah.setStatus("1");
        trnNasabah.setUserTran(username);
        trnNasabah.setTranDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        trnNasabah.setTranTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        trnNasabah.setLocationTran(request.getParameter("xlocation"));
        trnNasabah.setIpAddressTran(request.getRemoteAddr());
        trnNasabah.setTranFrom(request.getParameter("xtranfrom"));
//        trnNasabah.setAccountNumberAgen(request.getParameter("xaccountnumberagen"));
//        String date = request.getParameter("xdate");
//        String time = request.getParameter("xdate");
//        trnNasabah.setTranAgenDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
//        trnNasabah.setTranAgenTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
//        trnNasabah.setLocationAgenTran(request.getParameter("xlocation"));

        boolean status = valid.validateAccount(accountNumber);
        boolean validate = valid.validateToken(username, token);

        if (status && validate) {
            BigDecimal saldo = valid.getSaldo(accountNumber);
            if (Double.parseDouble(nominal) > 50000) {
                BigDecimal nominalValue = null;
                nominalValue = saldo.add(vNominal);
                boolean result = service.updateSaldo(accountNumber, nominalValue);
                if (result) {
                    trnDao.insert(trnNasabah);
                    obj.put("MESSAGE", "SETOR BERHASIL");
                    obj.put("STATUS", "OK");
                } else {
                    obj.put("MESSAGE", "SETOR GAGAl!");
                    obj.put("STATUS", "ERROR");
                }

            } else {
                obj.put("MESSAGE", "MAAF NOMINAL KURANG DARI Rp. 50000");
                obj.put("STATUS", "ERROR");
            }

        } else {
            obj.put("MESSAGE", "Access Denied! \nWrong Account Number");
            obj.put("STATUS", "ERROR");
        }
        out.println(obj);
    }

}
