/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.CheckSaldo;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cigist
 */
public class DasboardDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public BigDecimal totalTransaki() {
        BigDecimal saldo = null;
        CheckSaldo cs = new CheckSaldo();
        try {

            String sql = "SELECT SUM(amount) AS Total_Amount FROM trn_nasabah";
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

    public BigDecimal totalPembelianPulsa() {
        BigDecimal saldo = null;
        CheckSaldo cs = new CheckSaldo();
        try {

            String sql = "SELECT SUM(AMOUNT) FROM trn_pulsa";
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

    public BigDecimal totalPembelianData() {
        BigDecimal saldo = null;
        CheckSaldo cs = new CheckSaldo();
        try {

            String sql = "SELECT SUM(AMOUNT) FROM trn_paket_data";
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

    public BigDecimal totalPembayaran() {
        BigDecimal saldo = null;
        CheckSaldo cs = new CheckSaldo();
        try {

            String sql = "SELECT SUM(amount) FROM metapro.trn_payment";
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

    public int totalNasabah() {
        int saldo = 0;
        try {

            String sql = "SELECT COUNT(account_number) FROM metapro.mst_nasabah";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                saldo = rs.getInt(1);
                return saldo;
            }
            rs.close();
            connection.closeConnection();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class.getName()).log(Level.SEVERE, null, ex);
            return saldo = 0;
        }

        return saldo;
    }

}
