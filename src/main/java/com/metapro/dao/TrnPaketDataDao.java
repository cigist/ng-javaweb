/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.TrnPaketData;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cigist
 */
public class TrnPaketDataDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    synchronized public boolean payPaketData(TrnPaketData paketdata) throws IOException {
        PreparedStatement ps = null;
        try {

            Vector column = cigistDao.getColumn("trn_paket_data", connection.getConnection());
            String QueryInsert = cigistDao.cigistQueryInsert("trn_paket_data", column);
            ps = connection.getConnection().prepareStatement(QueryInsert);
            ps.setString(1, paketdata.getTrnNumber());
            ps.setString(2, paketdata.getAccountNumber());
            ps.setString(3, paketdata.getPaketDataCode());
            ps.setString(4, paketdata.getPhoneNumber());
            ps.setBigDecimal(5, paketdata.getAmount());
            ps.setString(6, paketdata.getUserTran());
            ps.setDate(7, paketdata.getDateTran());
            ps.setTime(8, paketdata.getTimeTran());
            ps.setString(9, paketdata.getIpAddressTran());
            ps.setString(10, paketdata.getLocationTran());
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
