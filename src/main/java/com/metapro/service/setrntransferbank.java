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
import com.metapro.model.TrnTransfer;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
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
@WebServlet(urlPatterns = "/api/setrntransferbank")
public class setrntransferbank extends HttpServlet {

    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();
    JSONObject status = new JSONObject();
    MstNasabahDao service = new MstNasabahDao();
    ValidateAccountDao valid = new ValidateAccountDao();
    TrnNasabahDao trnDao = new TrnNasabahDao();
    StringFormat st = new StringFormat();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set response content type
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        response.addHeader("Access-Control-Allow-Origin", "*");

        String accountNumber = request.getParameter("xaccountnumber");
        String accountNumberto = request.getParameter("xaccountnumberto");
        String nominal = request.getParameter("xnominal");
        BigDecimal vNominal = new BigDecimal(nominal);
        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");

        String dateCode = CigistDateTimeNow.getDateTimeNow("yyMMdd");
        String trnCode = st.autoCode("");
        BigDecimal fee = new BigDecimal("5000");

        TrnNasabah trnNasabah = new TrnNasabah();
        trnNasabah.setTran_number("TRF" + dateCode + trnCode);
        trnNasabah.setAccountNumber(accountNumber);
        trnNasabah.setTrnType("TRF");
        trnNasabah.setAmount(vNominal.negate());
        trnNasabah.setTranFee(new BigDecimal("5000"));
        trnNasabah.setNote(request.getParameter("xketerangan"));
        trnNasabah.setUserTran(username);
        trnNasabah.setTranDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        trnNasabah.setTranTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        trnNasabah.setLocationTran(request.getParameter("xlocation"));
        trnNasabah.setIpAddressTran(request.getRemoteAddr());
        trnNasabah.setTranFrom(request.getParameter("xtranfrom"));

        trnNasabah.setAccountNumberAgen(accountNumberto);
        trnNasabah.setStatus("1");
        trnNasabah.setTranAgenDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        trnNasabah.setTranAgenTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        trnNasabah.setLocationAgenTran(request.getParameter("xlocation"));
        trnNasabah.setLocationAgenTran(request.getRemoteAddr());

        boolean status = valid.validateAccount(accountNumber);
        boolean validate = valid.validateToken(username, token);

        if (status && validate) {
            BigDecimal saldo = valid.getSaldo(accountNumber);
            int res = vNominal.compareTo(saldo);
            if (res == -1) {
                BigDecimal nominalValue = null;
                nominalValue = saldo.subtract(vNominal);
                boolean result = service.updateSaldo(accountNumber, nominalValue);
                trnDao.insert(trnNasabah);
                if (result) {
                    // UPDATE SALDO NSABAH TO
                    TrnNasabah trnNasabahStr = new TrnNasabah();
                    trnNasabahStr.setTran_number("STR" + dateCode + trnCode);
                    trnNasabahStr.setAccountNumber(accountNumberto);
                    trnNasabahStr.setTrnType("STR");
                    trnNasabahStr.setAmount(vNominal);
                    trnNasabahStr.setTranFee(new BigDecimal("5000"));
                    trnNasabahStr.setNote(request.getParameter("xketerangan"));
                    trnNasabahStr.setUserTran(username);
                    trnNasabahStr.setTranDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                    trnNasabahStr.setTranTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                    trnNasabahStr.setLocationTran(request.getParameter("xlocation"));
                    trnNasabahStr.setIpAddressTran(request.getRemoteAddr());
                    trnNasabahStr.setTranFrom(request.getParameter("xtranfrom"));

                    trnNasabahStr.setAccountNumberAgen(accountNumber);
                    trnNasabahStr.setStatus("1");
                    trnNasabahStr.setTranAgenDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                    trnNasabahStr.setTranAgenTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                    trnNasabahStr.setLocationAgenTran(request.getParameter("xlocation"));
                    trnNasabahStr.setLocationAgenTran(request.getRemoteAddr());

                    BigDecimal saldoTo = valid.getSaldo(accountNumberto);
                    BigDecimal nominalTransferTo = null;
                    nominalTransferTo = saldoTo.add(vNominal);
                    service.updateSaldo(accountNumberto, nominalTransferTo);
                    trnDao.insert(trnNasabahStr);
                    obj.put("MESSAGE", "TRANSFER BERHASIL");
                    obj.put("STATUS", "OK");
                } else {
                    obj.put("MESSAGE", "TRANSFER GAGAl!");
                    obj.put("STATUS", "ERROR");
                }

            } else {
                obj.put("MESSAGE", "MAAF SALDO ANDA TIDAK MENCUKUPI");
                obj.put("STATUS", "ERROR");
            }

        } else {
            obj.put("MESSAGE", "Access Denied! \nWrong Account Number");
            obj.put("STATUS", "ERROR");
        }
        out.println(obj);
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
