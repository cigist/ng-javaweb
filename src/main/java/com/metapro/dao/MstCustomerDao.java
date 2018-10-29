/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.cigist.framework.core.CigistDaoInterface;
import com.metapro.model.MstCustomer;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
public class MstCustomerDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstCustomer> getAllData() {
        Vector<MstCustomer> listAbsensi = new Vector<>();
        try {
            String sql = "select customer_code,fullname,email,phone_number,flag_active from mst_customer where flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    MstCustomer customer = new MstCustomer();
                    customer.setCusCode(rs.getString(1));
                    customer.setFullname(rs.getString(2));
                    customer.setEmail(rs.getString(3));
                    customer.setPhoneNumber(rs.getString(4));
                    customer.setFlagActive(rs.getString(5));

                    listAbsensi.add(customer);
                }
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAbsensi;
    }

    public Vector<MstCustomer> getDataCustomer(String email) {
        Vector<MstCustomer> listAbsensi = new Vector<>();
        try {
            String sql = "select customer_code,fullname,email,phone_number,flag_active from mst_customer where "
                    + "email='" + email + "' and flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    MstCustomer customer = new MstCustomer();
                    customer.setCusCode(rs.getString(1));
                    customer.setFullname(rs.getString(2));
                    customer.setEmail(rs.getString(3));
                    customer.setPhoneNumber(rs.getString(4));
                    customer.setFlagActive(rs.getString(5));

                    listAbsensi.add(customer);
                }
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listAbsensi;
    }

    synchronized public String isertUpdate(MstCustomer obj) {
        PreparedStatement ps = null;
        try {
            String sql = "select * from mst_customer where email='" + obj.getEmail() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                return "Your mail has been registered !";

            } else {
                Vector column = cigistDao.getColumn("mst_customer", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_customer", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, obj.getCusCode());
                ps.setString(2, obj.getFullname());
                ps.setString(3, obj.getEmail());
                ps.setString(4, obj.getPhoneNumber());
                ps.setString(5, obj.getActivationCode());
                ps.setString(6, obj.getFlagActive());
                ps.setString(7, obj.getUserCreate());
                ps.setDate(8, obj.getDateCreate());
                ps.setTime(9, obj.getTimeCreate());
                ps.setString(10, obj.getUserUpdate());
                ps.setDate(11, obj.getDateUpdate());
                ps.setTime(12, obj.getTimeUpdate());
                ps.setString(13, obj.getLocation());
                ps.setString(14, obj.getIpAddress());
                ps.setString(15, obj.getImgUrl());
                ps.setString(16, obj.getAddress());
                ps.executeUpdate();
            }

            ps.close();
            connection.closeConnection();
            return "OK";
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed Operation";
        }
    }
}
