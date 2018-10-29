/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.vendor;

import com.cigist.framework.conn.CigistHttpConn;
import com.cigist.framework.util.CigistDateTimeNow;
import com.metapro.dao.MstPpobDao;
import com.metapro.dao.MstPulsaDao;
import com.metapro.dao.MstVendorDao;
import com.metapro.model.MstPpob;
import com.metapro.model.MstPulsa;
import com.metapro.model.MstVendor;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Irwan Cigist <cigist.developer@gmail.com>
 */
public class ServiceXpay {

    CigistHttpConn conn = new CigistHttpConn();
    ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
    MstPpobDao ppobDao = new MstPpobDao();

    public String getProductPBOB(String xuid, String xpass) {
        nameValuePairs.add(new BasicNameValuePair("xuid", xuid));
        nameValuePairs.add(new BasicNameValuePair("xpass", xpass));
        String respone = conn.servicePost("semstppob", nameValuePairs);
        if (respone != null) {
            JSONObject root = new JSONObject(respone);
            JSONArray dataArray = root.getJSONArray("DATA");
            System.out.println(dataArray.length());
            for (int i = 0; i < dataArray.length(); i++) {
                try {
                    JSONObject ppob = dataArray.getJSONObject(i);
                    String vendor = ppob.getString("providerId");
                    MstPpob ppobObj = new MstPpob();
                    ppobObj.setVendorCode(vendor);
                    ppobObj.setPpobCode(ppob.getString("productId"));
                    ppobObj.setPpobName(ppob.getString("description"));
                    ppobObj.setVendorPrice(ppob.getBigDecimal("distriPrice"));
                    ppobObj.setPrice(ppob.getBigDecimal("distriPrice"));
                    ppobObj.setDiscount(0);
                    ppobObj.setPpobType(ppob.getString("productType"));
                    ppobObj.setUserCreate(xuid);
                    ppobObj.setFlagActive("Yes");
                    ppobObj.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                    ppobObj.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                    ppobObj.setUserUpdate(xuid);
                    ppobObj.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                    ppobObj.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                    ppobObj.setResponse(ppob.getString("responese"));
                    ppobDao.insertUpdate(ppobObj);
                    System.out.println("KODE PRODUK = " + ppob.getString("productId"));

                } catch (IOException ex) {
                    Logger.getLogger(ServiceXpay.class.getName()).log(Level.SEVERE, null, ex);
                    return "ERROR";
                }

            }
            return "OK";
        }
        return null;

    }
}
