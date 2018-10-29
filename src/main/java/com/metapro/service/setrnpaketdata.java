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
import com.metapro.dao.TrnPaketDataDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.TrnNasabah;
import com.metapro.model.TrnPaketData;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
@WebServlet(urlPatterns = "/api/setrnpaketdata")
public class setrnpaketdata extends HttpServlet {

    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    MstNasabahDao service = new MstNasabahDao();
    ValidateAccountDao valid = new ValidateAccountDao();
    TrnNasabahDao trnDao = new TrnNasabahDao();
    TrnPaketDataDao trnDataDao = new TrnPaketDataDao();
    StringFormat st = new StringFormat();

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

        TrnPaketData trnData = new TrnPaketData();
        trnData.setTrnNumber("PDT" + dateCode + trnCode);
        trnData.setAccountNumber(accountNumber);
        trnData.setPaketDataCode(request.getParameter("xpaketdatacode"));
        trnData.setPhoneNumber(request.getParameter("xphonenumber"));
        trnData.setAmount(vNominal);
        trnData.setUserTran(username);
        trnData.setDateTran(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        trnData.setTimeTran(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        trnData.setLocationTran(request.getParameter("xlocation"));
        trnData.setIpAddressTran(request.getRemoteAddr());

        boolean status = valid.checkAccount(accountNumber, pin);
        boolean validate = valid.validateToken(username, token);

        if (status && validate) {
            BigDecimal saldo = service.getSaldo(accountNumber, pin);
            int res = vNominal.compareTo(saldo);
            if (res == -1) {
                BigDecimal nominalValue = null;
                nominalValue = saldo.subtract(vNominal);
                boolean result = service.updateSaldo(accountNumber, nominalValue);
                boolean statusPulsa = trnDataDao.payPaketData(trnData);
                if (result && statusPulsa) {
                    TrnNasabah trnNasabah = new TrnNasabah();
                    trnNasabah.setTran_number("DTA" + dateCode + trnCode);
                    trnNasabah.setAccountNumber(accountNumber);
                    trnNasabah.setTrnType("DTA");
                    trnNasabah.setAmount(vNominal.negate());
//                    trnNasabah.setTranFee(new BigDecimal("5000"));
//                    trnNasabah.setNote(request.getParameter("xketerangan"));
                    trnNasabah.setStatus("1");
                    trnNasabah.setUserTran(username);
                    trnNasabah.setTranDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                    trnNasabah.setTranTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                    trnNasabah.setLocationTran(request.getParameter("xlocation"));
                    trnNasabah.setIpAddressTran(request.getRemoteAddr());
                    trnNasabah.setTranFrom(request.getParameter("xtranfrom"));
                    trnDao.insert(trnNasabah);

                    obj.put("MESSAGE", "PEMBELIAN PAKET DATA BERHASIL");
                    obj.put("STATUS", "OK");
                } else {
                    obj.put("MESSAGE", "PEMBELIAN PAKETDATA GAGAL!");
                    obj.put("STATUS", "ERROR");
                }

            } else {
                obj.put("MESSAGE", "MAAF SALDO ANDA TIDAK MENCUKUPI");
                obj.put("STATUS", "ERROR");
            }

        } else {
            obj.put("MESSAGE", "PIN YANG ANDA MASUKAN SALAH!");
            obj.put("STATUS", "ERROR");
        }
        out.println(obj);
    }

}
