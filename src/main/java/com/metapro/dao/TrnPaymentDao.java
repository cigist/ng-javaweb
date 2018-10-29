/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.cigist.framework.core.CigistDaoInterface;
import com.metapro.model.TrnPayment;
import com.metapro.model.TrnPayment;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
public class TrnPaymentDao{

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public String isertUpdate(TrnPayment obj) {
        PreparedStatement ps = null;
        try {
            Vector column = cigistDao.getColumn("trn_payment", connection.getConnection());
            String QueryInsert = cigistDao.cigistQueryInsert("trn_payment", column);
            ps = connection.getConnection().prepareStatement(QueryInsert);
            ps.setString(1, obj.getTrnNumber());
            ps.setString(2, obj.getAccountNumberFrom());
            ps.setString(3, obj.getAccountNumberTo());
            ps.setBigDecimal(4, obj.getBeginingBalance());
            ps.setString(5, obj.getPaymentType());
            ps.setString(6, obj.getTrnType());
            ps.setBigDecimal(7, obj.getAmount());
            ps.setBigDecimal(8, obj.getTransfee());
            ps.setBigDecimal(9, obj.getEndingBalance());
            ps.setString(10, obj.getUserCreate());
            ps.setDate(11, obj.getTranDate());
            ps.setTime(12, obj.getTranTime());
            ps.setString(13, obj.getLocationtTran());
            ps.setString(14, obj.getIpAddressTran());

            ps.executeUpdate();
            connection.closeConnection();
            return "OK";
        } catch (IOException | SQLException ex) {
            Logger.getLogger(TrnPaymentDao.class.getName()).log(Level.SEVERE, null, ex);
            return "Process Failed";
        }
    }
}
