/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstNasabahDao;
import com.metapro.dao.MstUserDao;
import com.metapro.dao.TrnNasabahDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstNasabah;
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
import org.json.JSONObject;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
@WebServlet(urlPatterns = "/api/setrnnasabah")
public class setrnnasabah extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();

    JSONObject obj = new JSONObject();
    MstNasabahDao nasabahDao = new MstNasabahDao();
    ValidateAccountDao valiDao = new ValidateAccountDao();
    MstUserDao userDao = new MstUserDao();
    MstNasabahDao service = new MstNasabahDao();
    TrnNasabahDao trnNasabahDao = new TrnNasabahDao();
    TrnNasabahDao trnDao = new TrnNasabahDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        String accountNumber = request.getParameter("xaccountnumber");
        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");
        String xaction = request.getParameter("xaction");
        TrnNasabah trnNasabah = new TrnNasabah();

        boolean validate = valiDao.validateToken(username, token);
        if (validate && !token.isEmpty() && !username.isEmpty()) {
            if (xaction.equals("list")) {
                Vector<MstNasabah> dataNasabah = new Vector<>();
                dataNasabah = nasabahDao.getAllData();
                obj.put("NASABAH", dataNasabah);
                obj.put("MESSAGE", "");
                obj.put("STATUS", "OK");
            } else if (xaction.equals("trnagen")) {
                String nominal = request.getParameter("xnominal");
                BigDecimal vNominal = new BigDecimal(nominal);

                trnNasabah.setTran_number(request.getParameter("xtrnnumber"));
                trnNasabah.setAccountNumber(request.getParameter("xaccountnumber"));
                trnNasabah.setAccountNumberAgen(request.getParameter("xaccountnumberagen"));
                trnNasabah.setStatus("1");
                String date = request.getParameter("xdate");
                String time = request.getParameter("xdate");
                trnNasabah.setTranAgenDate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                trnNasabah.setTranAgenTime(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                trnNasabah.setLocationAgenTran(request.getParameter("xlocation"));

                //Update table trn_nasabah
                boolean statusTrn = trnNasabahDao.tranNasabah(trnNasabah);
                //Cek saldo Nasabah
                if (statusTrn) {
                    BigDecimal saldo = valiDao.getSaldo(accountNumber);
                    //Update Saldo Nasabah
                    BigDecimal nominalValue = null;
                    nominalValue = saldo.add(vNominal);
                    boolean result = service.updateSaldo(accountNumber, nominalValue);
                    obj.put("NASABAH", accountNumber);
                    obj.put("MESSAGE", "TRANSAKI BERHASIL");
                    obj.put("STATUS", "OK");
                } else {
                    obj.put("NASABAH", "");
                    obj.put("MESSAGE", "TRANSAKSI GAGAL");
                    obj.put("STATUS", "ERROR");
                }
            } else if (xaction.equals("history")) {
                Vector<TrnNasabah> historyNasabah = new Vector<>();
                historyNasabah = trnNasabahDao.getHistoryTran(request.getParameter("xaccountnumber"));
                obj.put("NASABAH", historyNasabah);
                obj.put("MESSAGE", "");
                obj.put("STATUS", "OK");
            } else if (xaction.equals("detail")) {
                Vector<TrnNasabah> detailTrnNasabah = new Vector<>();
                detailTrnNasabah = trnNasabahDao.getDetailTran(accountNumber);
                obj.put("NASABAH", detailTrnNasabah);
                obj.put("MESSAGE", "");
                obj.put("STATUS", "OK");
            } else if (xaction.equals("type")) {
                String tranType=request.getParameter("xtrantype");
                Vector<TrnNasabah> detailTrnNasabah = new Vector<>();
                detailTrnNasabah = trnNasabahDao.getDataTranByType(tranType);
                obj.put("NASABAH", detailTrnNasabah);
                obj.put("MESSAGE", "");
                obj.put("STATUS", "OK");

            } else {
                obj.put("MESSAGE", "Acsess Denied !");
                obj.put("STATUS", "ERROR");
            };

        } else {
            obj.put("HIS_NASABAH", "");
            obj.put("MESSAGE", "Acsess Denied !");
            obj.put("STATUS", "ERROR");
        }

        out.println(obj);
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
