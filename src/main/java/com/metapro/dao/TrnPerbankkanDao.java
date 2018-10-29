/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.TrnPerbankkan;
import com.metapro.model.TrnPulsa;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cigist
 */
public class TrnPerbankkanDao {
     CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    synchronized public boolean booking(TrnPerbankkan bank) throws IOException {
        PreparedStatement ps = null;
        try {

            Vector column = cigistDao.getColumn("trn_perbankkan", connection.getConnection());
            String QueryInsert = cigistDao.cigistQueryInsert("trn_perbankkan", column);
            ps = connection.getConnection().prepareStatement(QueryInsert);
            ps.setString(1, bank.getTrnNumber());
            ps.setString(2, bank.getCusCode());
            ps.setString(3, bank.getBankCode());
            ps.setString(4, bank.getProductCode());
            ps.setString(5, bank.getUserTran());
            ps.setDate(6, bank.getDateTran());
            ps.setTime(7, bank.getTimeTran());
            ps.setString(8, bank.getIpAddressTran());
            ps.setString(9, bank.getLocationTran());
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
