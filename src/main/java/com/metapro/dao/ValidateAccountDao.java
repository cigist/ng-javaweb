/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
public class ValidateAccountDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public boolean checkAccount(String accountNumber, String pin) {
        try {
            String sql = "select account_number,pin from mst_nasabah where "
                    + "account_number='" + accountNumber + "' and pin='" + pin + "' and flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                return true;
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public String getAccountEmail(String accountNumber) {
        try {
            String sql = "select email from mst_customer where "
                    + "customer_code='" + accountNumber + "' and flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                String email = rs.getString(1);
                return email;
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class.getName()).log(Level.SEVERE, null, ex);
            return "ERROR";
        }
        return "ERROR";
    }

    public String checkSaldo(String accountNumber, String pin) {
        BigDecimal saldo = null;
        try {
            String sql = "select ENDING_BALANCE from mst_nasabah where "
                    + "account_number='" + accountNumber + "' and pin='" + pin + "' and flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                saldo = rs.getBigDecimal(1);
                return saldo.toString();
            }

            connection.closeConnection();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class
                    .getName()).log(Level.SEVERE, null, ex);
            return "Sory your Account Number or password not match!";
        }
        return "Sory your Account Number or password not match!";
    }

    public boolean validateAccount(String accountNumber) {
        try {
            String sql = "select account_number,pin from mst_nasabah where "
                    + "account_number='" + accountNumber + "' and flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                return true;
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    public BigDecimal getSaldo(String accountNumber) {
        BigDecimal saldo = null;
        try {

            String sql = "select ending_balance from mst_nasabah where "
                    + "account_number='" + accountNumber + "' and flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                saldo = rs.getBigDecimal(1);
                return saldo;
            }
            rs.close();
            connection.closeConnection();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class.getName()).log(Level.SEVERE, null, ex);
            return saldo = null;
        }

        return saldo;
    }

    public BigDecimal getFee(String paymentCode) {
        BigDecimal fee = new BigDecimal(0);
        try {

            String sql = "select fee from mst_payment_type where "
                    + "payment_code='" + paymentCode + "' and flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                fee = rs.getBigDecimal(1);
                return fee;
            }
            rs.close();
            connection.closeConnection();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class.getName()).log(Level.SEVERE, null, ex);
            return fee = new BigDecimal(0);
        }

        return fee;
    }

    public boolean validateToken(String unsername, String token) {
        try {
            String sql = "select username,token from trn_user_login where "
                    + "username='" + unsername + "' and token='" + token + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                return true;
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    
}
