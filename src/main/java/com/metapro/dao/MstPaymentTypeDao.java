/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstPaymentType;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cigist
 */
public class MstPaymentTypeDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstPaymentType> getPaymentAll() {
        Vector<MstPaymentType> listData = new Vector<>();
        try {
            String sql = "SELECT payment_code,payment_desc,fee,flag_active from mst_payment_type where flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPaymentType payment = new MstPaymentType();
                payment.setPaymentCode(rs.getString(1));
                payment.setPaymnetDesc(rs.getString(2));
                payment.setFee(rs.getBigDecimal(3));
                payment.setFlagActive(rs.getString(4));

                listData.add(payment);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    synchronized public boolean insertUpdate(MstPaymentType payment) throws IOException {
        PreparedStatement ps = null;
        try {
            String sql = "select payment_code from mst_payment_type where "
                    + "payment_code='" + payment.getPaymentCode() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "update mst_payment_type set payment_desc=?,fee=?,"
                        + "flag_active=?,user_update=?,date_update=?,time_update=? where payment_code=?";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(1, payment.getPaymnetDesc());
                ps.setBigDecimal(2, payment.getFee());
                ps.setString(3, payment.getFlagActive());
                ps.setString(4, payment.getUserUpdate());
                ps.setDate(5, payment.getDateUpdate());
                ps.setTime(6, payment.getTimeUpdate());
                ps.setString(7, payment.getPaymentCode());

                ps.executeUpdate();
                ps.close();
                connection.closeConnection();
            } else {
                Vector column = cigistDao.getColumn("mst_payment_type", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_payment_type", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, payment.getPaymentCode());
                ps.setString(2, payment.getPaymnetDesc());
                ps.setBigDecimal(3, payment.getFee());
                ps.setString(4, payment.getFlagActive());
                ps.setString(5, payment.getUserCreate());
                ps.setDate(6, payment.getDateCreate());
                ps.setTime(7, payment.getTimeCreate());
                ps.setString(8, payment.getUserUpdate());
                ps.setDate(9, payment.getDateUpdate());
                ps.setTime(10, payment.getTimeUpdate());

                ps.executeUpdate();
            }
            ps.close();
            connection.closeConnection();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    synchronized public boolean changeAktif(MstPaymentType payment) throws IOException {
        PreparedStatement ps = null;
        try {
            String QueryUpdate = "update mst_payment_code set flag_active=?,user_update=?,date_update=?,time_update=? where payment_code=?";
            ps = connection.getConnection().prepareStatement(QueryUpdate);
            ps.setString(1, payment.getFlagActive());
            ps.setString(2, payment.getUserUpdate());
            ps.setDate(3, payment.getDateUpdate());
            ps.setTime(4, payment.getTimeUpdate());
            
            ps.setString(5, payment.getPaymentCode());
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
