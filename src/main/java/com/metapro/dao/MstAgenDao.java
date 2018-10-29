/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cigist
 */
public class MstAgenDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public boolean setAgen(String accountNumber, String AccountType) {
        PreparedStatement ps = null;
        try {
            String sql = "select account_number from mst_nasabah where account_number='" + accountNumber + "' and ending_balance>25000000;";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "update mst_nasabah set account_type=? where account_number=? ";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(1, AccountType);
                ps.setString(2, accountNumber);
                ps.executeUpdate();

                ps.close();
                connection.closeConnection();
                return true;
            } else {
                return false;
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
