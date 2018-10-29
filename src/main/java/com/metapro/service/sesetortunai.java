/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.CigistEncrypDecryp;
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
@WebServlet(urlPatterns = "/api/sesetortunai")
public class sesetortunai extends HttpServlet {

    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    MstNasabahDao service = new MstNasabahDao();
    ValidateAccountDao valid = new ValidateAccountDao();
    StringFormat st = new StringFormat();
    TrnNasabahDao trnDao = new TrnNasabahDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set response content type
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");

        String accountNumber = request.getParameter("xaccountnumber");
        String xpin = request.getParameter("xpin");
        String pin = CigistEncrypDecryp.encrypt(xpin);

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
        trnNasabah.setUserTran(username);
        trnNasabah.setStatus("0");
        trnNasabah.setTranDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        trnNasabah.setTranTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        trnNasabah.setLocationTran(request.getParameter("xlocation"));
        trnNasabah.setIpAddressTran(request.getRemoteAddr());
        trnNasabah.setTranFrom(request.getParameter("xtranfrom"));

        boolean status = valid.checkAccount(accountNumber, pin);
        boolean validate = valid.validateToken(username, token);
        Vector<TrnNasabah> dataTran = new Vector<>();

        if (status && validate) {
            BigDecimal saldo = service.getSaldo(accountNumber, pin);
            if (Double.parseDouble(nominal) > 50000) {
//                BigDecimal nominalValue = null;
//                nominalValue = saldo.add(vNominal);
//                boolean result = service.updateSaldo(accountNumber, nominalValue);
                boolean result = trnDao.insert(trnNasabah);
                if (result) {
                    dataTran = trnDao.getDataTran(trnNasabah);
                    obj.put("MESSAGE", "SETOR TUNAI BERHASIL, AGEN TERDEKAT AKAN SEGERA DATANG");
                    obj.put("STATUS", "OK");
                    obj.put("DATA", dataTran);
                } else {
                    obj.put("MESSAGE", "SETOR TUNAI GAGAl!");
                    obj.put("STATUS", "ERROR");
                    obj.put("DATA", "");
                }

            } else {
                obj.put("MESSAGE", "MAAF NOMINAL KURANG DARI Rp. 50000");
                obj.put("STATUS", "ERROR");
                obj.put("DATA", "");
            }

        } else {
            obj.put("MESSAGE", "PIN YANG ANDA MASUKAN SALAH!");
            obj.put("STATUS", "ERROR");
            obj.put("DATA", "");
        }
        out.println(obj);
    }

}
