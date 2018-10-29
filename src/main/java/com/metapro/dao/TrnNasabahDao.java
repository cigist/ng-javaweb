/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.TrnNasabah;
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
public class TrnNasabahDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public boolean insert(TrnNasabah obj) {
        PreparedStatement ps = null;
        try {
            Vector column = cigistDao.getColumn("trn_nasabah", connection.getConnection());
            String QueryInsert = cigistDao.cigistQueryInsert("trn_nasabah", column);
            ps = connection.getConnection().prepareStatement(QueryInsert);
            ps.setString(1, obj.getTran_number());
            ps.setString(2, obj.getAccountNumber());
            ps.setString(3, obj.getTrnType());
            ps.setBigDecimal(4, obj.getAmount());
            ps.setBigDecimal(5, obj.getTranFee());
            ps.setString(6, obj.getNote());
            ps.setString(7, obj.getUserTran());
            ps.setDate(8, obj.getTranDate());
            ps.setTime(9, obj.getTranTime());
            ps.setString(10, obj.getIpAddressTran());
            ps.setString(11, obj.getLocationTran());
            ps.setString(12, obj.getTranFrom());
            ps.setString(13, obj.getAccountNumberAgen());
            ps.setString(14, obj.getStatus());
            ps.setDate(15, obj.getTranAgenDate());
            ps.setTime(16, obj.getTranAgenTime());
            ps.setString(17, obj.getLocationAgenTran());
            ps.executeUpdate();

            connection.closeConnection();
            return true;
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean tranNasabah(TrnNasabah obj) {
        PreparedStatement ps = null;
        try {
            String sql = "select tran_number from trn_nasabah where tran_number='" + obj.getTran_number() + "'"
                    + "and status=1";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                return false;
            } else {
                String Query = "update trn_nasabah set account_number_agent=?,status=?,date_agen_tran=?,time_agen_tran=?,location_agen_tran=?"
                        + "where tran_number=?";
                ps = connection.getConnection().prepareStatement(Query);
                ps.setString(6, obj.getTran_number());

                ps.setString(1, obj.getAccountNumberAgen());
                ps.setString(2, obj.getStatus());
                ps.setDate(3, obj.getTranAgenDate());
                ps.setTime(4, obj.getTranAgenTime());
                ps.setString(5, obj.getLocationAgenTran());
                ps.executeUpdate();
            }
            ps.close();
            connection.closeConnection();
            return true;
        } catch (IOException | SQLException ex) {
            Logger.getLogger(MstCustomerDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public Vector<TrnNasabah> getDataTranByType(String type) {
        Vector<TrnNasabah> listTrnNasabah = new Vector<>();
        try {
            String sql = "select tran_number,account_number,amount,date_tran,time_tran,account_number_agent from trn_nasabah where \n"
                    + "trn_type='" + type + "' and status=1 order by date_tran desc";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    TrnNasabah nasabah = new TrnNasabah();
                    nasabah.setTran_number(rs.getString(1));
                    nasabah.setAccountNumber(rs.getString(2));
                    nasabah.setAmount(rs.getBigDecimal(3));
                    nasabah.setTranDate(rs.getDate(4));
                    nasabah.setTranTime(rs.getTime(5));
                    nasabah.setAccountNumberAgen(rs.getString(6));
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

    //FOR MOBILE TRAN
    public Vector<TrnNasabah> getDataTran(TrnNasabah obj) {
        Vector<TrnNasabah> listTrnNasabah = new Vector<>();
        try {
            String sql = "select tran_number,account_number,amount from trn_nasabah where tran_number='" + obj.getTran_number() + "'and "
                    + "account_number='" + obj.getAccountNumber() + "' and status=1 order by date_tran desc";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    TrnNasabah nasabah = new TrnNasabah();
                    nasabah.setTran_number(rs.getString(1));
                    nasabah.setAccountNumber(rs.getString(2));
                    nasabah.setAmount(rs.getBigDecimal(3));
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

    public Vector<TrnNasabah> getDetailTran(String accountNumber) {
        Vector<TrnNasabah> listTrnNasabah = new Vector<>();
        try {
            String sql = "select tran_number,account_number,amount,date_tran,time_tran from trn_nasabah where \n"
                    + "account_number='" + accountNumber + "' and status=1 order by date_tran desc";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    TrnNasabah nasabah = new TrnNasabah();
                    nasabah.setTran_number(rs.getString(1));
                    nasabah.setAccountNumber(rs.getString(2));
                    nasabah.setAmount(rs.getBigDecimal(3));
                    nasabah.setTranDate(rs.getDate(4));
                    nasabah.setTranTime(rs.getTime(5));
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

    public Vector<TrnNasabah> getDataTranBy(TrnNasabah obj) {
        Vector<TrnNasabah> listTrnNasabah = new Vector<>();
        try {
            String sql = "select tran_number,account_number,amount from trn_nasabah where tran_number='" + obj.getTran_number() + "'and "
                    + "account_number='" + obj.getAccountNumber() + "' and status='1' order by date_tran desc";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    TrnNasabah nasabah = new TrnNasabah();
                    nasabah.setTran_number(rs.getString(1));
                    nasabah.setAccountNumber(rs.getString(2));
                    nasabah.setAmount(rs.getBigDecimal(3));
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

    public Vector<TrnNasabah> getHistoryTran(String accountNumber) {
        Vector<TrnNasabah> listTrnNasabah = new Vector<>();
        try {
            String sql = "select a.tran_number,a.amount,a.account_number_agent as agen,account_name as agen_name,a.status,a.date_tran,a.time_tran from trn_nasabah a\n"
                    + "inner join mst_nasabah b on a.account_number=b.account_number\n"
                    + "where a.account_number='" + accountNumber + "' and a.trn_type in('STR','TRK') order by a.date_tran desc";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    TrnNasabah nasabah = new TrnNasabah();
                    nasabah.setTran_number(rs.getString(1));
                    nasabah.setAmount(rs.getBigDecimal(2));
                    nasabah.setAccountNumberAgen(rs.getString(3));
                    nasabah.setAgenName(rs.getString(4));
                    nasabah.setStatus(rs.getString(5));
                    nasabah.setTranDate(rs.getDate(6));
                    nasabah.setTranTime(rs.getTime(7));

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
}
