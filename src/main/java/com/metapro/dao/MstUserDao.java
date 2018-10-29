/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.cigist.framework.core.CigistDaoInterface;
import com.metapro.model.MstUser;
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
public class MstUserDao implements CigistDaoInterface<MstUser> {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    @Override
    synchronized public String isertUpdate(MstUser obj) {
        PreparedStatement ps = null;
        try {
            String sql = "select username from mst_user_login where username='" + obj.getUsername() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                return "Your mail has been registered !";

            } else {
                Vector column = cigistDao.getColumn("mst_user_login", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_user_login", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, obj.getUserCode());
                ps.setString(2, obj.getUsername());
                ps.setString(3, obj.getPassword());
                ps.setString(4, obj.getFlagActive());
                ps.setString(5, obj.getUserCreate());
                ps.setDate(6, obj.getDateCreate());
                ps.setTime(7, obj.getTimeCreate());
                ps.setString(8, obj.getUserUpdate());
                ps.setDate(9, obj.getDateUpdate());
                ps.setTime(10, obj.getTimeUpdate());
                ps.setString(11, obj.getLocation());
                ps.setString(12, obj.getIpAddress());
                ps.setInt(13, 0);
                ps.executeUpdate();
            }
            connection.closeConnection();
            return "OK";
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed";
        }
    }

    public String delete(String obj) {
        PreparedStatement ps = null;
        try {
            String QueryInsert = "update mst_user_login set flag_active ='No' where username=?";
            ps = connection.getConnection().prepareStatement(QueryInsert);
            ps.setString(1, obj);

            ps.executeUpdate();

            connection.closeConnection();
            return "OK";
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed";
        }
    }

    public boolean checkPassword(String username,String password) {
        PreparedStatement ps = null;
        try {
            String sql = "select username from mst_user_login where username='" +username + "'"
                    + "and password='" +password+ "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                
                
                
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

    public String changePassword(MstUser obj) {
        PreparedStatement ps = null;
        try {
            String Query = "update mst_user_login set password=?,user_update=?,date_update=?,time_update=?,"
                    + "location=?, ip_address=? where username=?";
            ps = connection.getConnection().prepareStatement(Query);
            ps.setString(7, obj.getUsername());

            ps.setString(1, obj.getPassword());
            ps.setString(2, obj.getUserUpdate());
            ps.setDate(3, obj.getDateUpdate());
            ps.setTime(4, obj.getTimeUpdate());
            ps.setString(5, obj.getLocation());
            ps.setString(6, obj.getIpAddress());
            ps.executeUpdate();

            ps.close();
            connection.closeConnection();
            return "OK";

        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed";
        }
    }

    @Override
    public Vector<MstUser> getAllData() {
        Vector<MstUser> listUser = new Vector<>();
        try {
            String sql = "select user_code,username,flag_active from mst_user_login where flag_active='Yes' order by user_code asc";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    MstUser user = new MstUser();
                    user.setUserCode(rs.getString(1));
                    user.setUsername(rs.getString(2));
                    user.setFlagActive(rs.getString(3));

                    listUser.add(user);
                }
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listUser;
    }

    @Override
    public Vector<MstUser> getDataBy(String fieldName, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector<MstUser> getIndexKey(String... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(MstUser obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
