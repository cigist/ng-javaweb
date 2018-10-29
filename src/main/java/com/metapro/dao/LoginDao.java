/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.cigist.framework.util.CigistDateTimeNow;
import com.metapro.model.MstNasabah;
import com.metapro.model.TrnUserLogin;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
public class LoginDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public boolean login(String username, String password) {
        try {
            String sql = "select username,password from mst_user_login where "
                    + "username='" + username + "' and password='" + password + "' and flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                return true;
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return false;
    }

    public String getAccountNumber(String email) {
        try {
            String sql = "select customer_code from mst_customer where "
                    + "email='" + email + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                String accountNumber = rs.getString(1);

                return accountNumber;

            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(LoginDao.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR";
        }
        return "ERROR";
    }

    public Vector<MstNasabah> getDataUser(String email) {
        Vector<MstNasabah> listNasabah = new Vector<>();
        try {
            String sql = "select a.email,b.account_number,a.fullname,b.account_type from mst_customer a\n"
                    + "inner join mst_nasabah b on a.customer_code=b.account_number \n"
                    + "where a.email='"+email+"'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstNasabah nasabah = new MstNasabah();
                nasabah.setEmail(rs.getString(1));
                nasabah.setAccountNumber(rs.getString(2));
                nasabah.setAccountName(rs.getString(3));
                nasabah.setAccountType(rs.getString(4));
                listNasabah.add(nasabah);
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {

        }
        return listNasabah;
    }

    public boolean insetUserLogin(TrnUserLogin obj) {
        PreparedStatement ps = null;
        try {
            Vector column = cigistDao.getColumn("trn_user_login", connection.getConnection());
            String QueryInsert = cigistDao.cigistQueryInsert("trn_user_login", column);
            ps = connection.getConnection().prepareStatement(QueryInsert);
            ps.setString(1, obj.getTrnLoginCode());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getSessionID());
            ps.setString(4, obj.getToken());
            ps.setDate(5, obj.getDateLogin());
            ps.setTime(6, obj.getTimeLogin());
            ps.setString(7, obj.getLocation());
            ps.setString(8, obj.getIpAddress());
            ps.setString(9, obj.getLoginFrom());
            ps.executeUpdate();

            connection.closeConnection();
            return true;
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void updateStatusLogin(String username, String password, int status) {
        PreparedStatement ps = null;
        try {
            String Query = "update mst_user_login set status=? where username=? and password=?";
            ps = connection.getConnection().prepareStatement(Query);
            ps.setInt(1, status);
            ps.setString(2, username);
            ps.setString(3, password);

            ps.executeUpdate();

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateStatusLogout(String username, int status) {
        PreparedStatement ps = null;
        try {
            String Query = "update mst_user_login set status=? where username=?";
            ps = connection.getConnection().prepareStatement(Query);
            ps.setInt(1, status);
            ps.setString(2, username);

            ps.executeUpdate();
            connection.closeConnection();
            return true;
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Vector<TrnUserLogin> getAllData() {
        Vector<TrnUserLogin> listLogin = new Vector<>();
        String Date = CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd");
        try {
            String sql = "select trn_login_code,username,date_login,time_login,location,ip_address,login_from from trn_user_login where date_login='" + Date + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    TrnUserLogin login = new TrnUserLogin();
                    login.setTrnLoginCode(rs.getString(1));
                    login.setUsername(rs.getString(2));
                    login.setDateLogin(rs.getDate(3));
                    login.setTimeLogin(rs.getTime(4));
                    login.setLocation(rs.getString(5));
                    login.setIpAddress(rs.getString(6));
                    login.setLoginFrom(rs.getString(7));

                    listLogin.add(login);
                }
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listLogin;
    }
}
