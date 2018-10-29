/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.CheckSaldo;
import com.metapro.model.MstNasabah;
import com.metapro.model.TrnNasabah;
import java.io.IOException;
import java.math.BigDecimal;
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
public class MstNasabahDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public String isertUpdate(MstNasabah obj) {
        PreparedStatement ps = null;
        try {
            String sql = "select account_number from mst_nasabah where account_number='" + obj.getAccountNumber() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryInsert = "update mst_nasabah set account_name=? where account_number=?";
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(2, obj.getAccountNumber());
                ps.setString(1, obj.getAccountName());

                ps.executeUpdate();

                ps.close();
                connection.closeConnection();
                return "OK";
            } else {

                Vector column = cigistDao.getColumn("mst_nasabah", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_nasabah", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, obj.getAccountNumber());
                ps.setString(2, obj.getPin());
                ps.setString(3, obj.getAccountName());
                ps.setString(4, obj.getAccountType());
                ps.setBigDecimal(5, obj.getBiginingBalance());
                ps.setBigDecimal(6, obj.getEndingBalance());
                ps.setString(7, obj.getFlagActive());
                ps.setString(8, obj.getUserCreate());
                ps.setDate(9, obj.getDateCreate());
                ps.setTime(10, obj.getTimeCreate());
                ps.setString(11, obj.getLocationCreate());
                ps.setString(12, obj.getIpAddressCreate());
                ps.setString(13, obj.getUserUpdate());
                ps.setDate(14, obj.getDateUpdate());
                ps.setTime(15, obj.getTimeUpdate());
                ps.setString(16, obj.getLocationUpdate());
                ps.setString(17, obj.getIpAddressCreate());
                ps.executeUpdate();

                ps.close();
                connection.closeConnection();
                return "OK";
            }
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed Operation";
        }
    }

    public String changePin(MstNasabah obj) {
        PreparedStatement ps = null;
        try {
            String Query = "update mst_nasabah set pin=?,user_update=?,date_update=?,time_update=?,"
                    + "location_update=?, ip_address_update=? where account_number=?";
            ps = connection.getConnection().prepareStatement(Query);
            ps.setString(7, obj.getAccountNumber());

            ps.setString(1, obj.getPin());
            ps.setString(2, obj.getUserUpdate());
            ps.setDate(3, obj.getDateUpdate());
            ps.setTime(4, obj.getTimeUpdate());
            ps.setString(5, obj.getLocationUpdate());
            ps.setString(6, obj.getIpAddressUpdate());
            ps.executeUpdate();

            ps.close();
            connection.closeConnection();
            return "OK";

        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return "Failed";
        }
    }

    public Vector<MstNasabah> getAllData() {
        Vector<MstNasabah> listNasabah = new Vector<>();
        try {
            String sql = "select account_number,account_name,account_type,ending_balance,flag_active from mst_nasabah";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    MstNasabah nasabah = new MstNasabah();
                    nasabah.setAccountNumber(rs.getString(1));
                    nasabah.setAccountName(rs.getString(2));
                    nasabah.setAccountType(rs.getString(3));
                    nasabah.setEndingBalance(rs.getBigDecimal(4));
                    nasabah.setFlagActive(rs.getString(5));

                    listNasabah.add(nasabah);
                }
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listNasabah;
    }

    public Vector<CheckSaldo> checkSaldo(String accountNumber, String pin) {
        Vector<CheckSaldo> listCheckSaldo = new Vector<>();
        CheckSaldo cs = new CheckSaldo();
        try {

            String sql = "select account_number,account_name,ending_balance from mst_nasabah where "
                    + "account_number='" + accountNumber + "' and pin='" + pin + "' and flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                cs.setAccountNumber(rs.getString(1));
                cs.setAccountName(rs.getString(2));
                cs.setSaldo(rs.getBigDecimal(3));
            }
            listCheckSaldo.add(cs);

            rs.close();
            connection.closeConnection();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class.getName()).log(Level.SEVERE, null, ex);

        }

        return listCheckSaldo;
    }

    public BigDecimal getSaldo(String accountNumber, String pin) {
        BigDecimal saldo = new BigDecimal(0);
        CheckSaldo cs = new CheckSaldo();
        try {

            String sql = "select ending_balance from mst_nasabah where "
                    + "account_number='" + accountNumber + "' and pin='" + pin + "' and flag_active='Yes'";
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

    public boolean updateSaldo(String accountNumber, BigDecimal nominal) {
        PreparedStatement ps = null;
        try {
            String Query = "update mst_nasabah set ending_balance =? where account_number=?";
            ps = connection.getConnection().prepareStatement(Query);
            ps.setBigDecimal(1, nominal);
            ps.setString(2, accountNumber);
            ps.executeUpdate();

            ps.close();
            connection.closeConnection();
            return true;
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Vector<TrnNasabah> cekRekening(String accountNumber) {
        Vector<TrnNasabah> listTrnNasabah = new Vector<>();
        try {
            String sql = "select tran_number,amount,account_number_agent as agen,status,date_tran,time_tran,trn_type,note from trn_nasabah\n"
                    + "where account_number='" + accountNumber + "' order by date_tran desc limit 5";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    TrnNasabah nasabah = new TrnNasabah();
                    nasabah.setTran_number(rs.getString(1));
                    nasabah.setAmount(rs.getBigDecimal(2));
                    nasabah.setAccountNumberAgen(rs.getString(3));
                    nasabah.setStatus(rs.getString(4));
                    nasabah.setTranDate(rs.getDate(5));
                    nasabah.setTranTime(rs.getTime(6));
                    String type = rs.getString(7);
                    if (type.equals("STR")) {
                        nasabah.setTrnType("KREDIT");
                    } else {
                        nasabah.setTrnType("DEBIT");
                    }
                    nasabah.setNote(rs.getString(8));

                    listTrnNasabah.add(nasabah);
                }
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTrnNasabah;
    }

    public Vector<TrnNasabah> mutasiRekening(String accountNumber, String datefrom, String dateto) {
        Vector<TrnNasabah> listTrnNasabah = new Vector<>();
        try {
            String sql = "select tran_number,amount,account_number_agent as agen,status,date_tran,time_tran,trn_type,note from trn_nasabah\n"
                    + "where account_number='" + accountNumber + "' and date_tran between '" + datefrom + "' and '" + dateto + "' order by date_tran desc";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    TrnNasabah nasabah = new TrnNasabah();
                    nasabah.setTran_number(rs.getString(1));
                    nasabah.setAmount(rs.getBigDecimal(2));
                    nasabah.setAccountNumberAgen(rs.getString(3));
                    nasabah.setStatus(rs.getString(4));
                    nasabah.setTranDate(rs.getDate(5));
                    nasabah.setTranTime(rs.getTime(6));
                    String type = rs.getString(7);
                    if (type.equals("STR")) {
                        nasabah.setTrnType("KREDIT");
                    } else {
                        nasabah.setTrnType("DEBIT");
                    }
                    nasabah.setNote(rs.getString(8));

                    listTrnNasabah.add(nasabah);
                }
            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listTrnNasabah;
    }

    public BigDecimal totalTransaki(String accountNumber) {
        BigDecimal saldo = new BigDecimal(0);
        CheckSaldo cs = new CheckSaldo();
        try {

            String sql = "SELECT SUM(amount) AS Total_Amount FROM metapro.trn_nasabah WHERE account_number='" + accountNumber + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                saldo = rs.getBigDecimal(1);
                return saldo;
            }
            rs.close();
            connection.closeConnection();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstNasabahDao.class.getName()).log(Level.SEVERE, null, ex);
            return saldo = new BigDecimal(0);
        }

        return saldo;
    }
}
