/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.metapro.model.TrnPulsa;
import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cigist
 */
public class TrnPulsaDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    synchronized public boolean payPulsa(TrnPulsa pulsa) throws IOException {
        PreparedStatement ps = null;
        try {

            Vector column = cigistDao.getColumn("trn_pulsa", connection.getConnection());
            String QueryInsert = cigistDao.cigistQueryInsert("trn_pulsa", column);
            ps = connection.getConnection().prepareStatement(QueryInsert);
            ps.setString(1, pulsa.getTrnNumber());
            ps.setString(2, pulsa.getAccountNumber());
            ps.setString(3, pulsa.getPulsaCode());
            ps.setString(4, pulsa.getPhoneNumber());
            ps.setBigDecimal(5, pulsa.getAmount());
            ps.setString(6, pulsa.getUserTran());
            ps.setDate(7, pulsa.getDateTran());
            ps.setTime(8, pulsa.getTimeTran());
            ps.setString(9, pulsa.getIpAddressTran());
            ps.setString(10, pulsa.getLocationTran());
            ps.executeUpdate();

            ps.close();
            connection.closeConnection();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

}
