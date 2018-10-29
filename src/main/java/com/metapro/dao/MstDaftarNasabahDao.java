/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstDaftarNasabah;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cigist
 */
public class MstDaftarNasabahDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstDaftarNasabah> getDataNasabahAll(String accountNumber) {
        Vector<MstDaftarNasabah> listData = new Vector<>();
        try {
            String sql = "select account_number_to,nasabah_name from mst_daftar_nasabah where account_number='" + accountNumber + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstDaftarNasabah nasabah = new MstDaftarNasabah();
                nasabah.setAccountNumberTo(rs.getString(1));
                nasabah.setNasabahName(rs.getString(2));

                listData.add(nasabah);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    synchronized public boolean insertUpdate(MstDaftarNasabah nasabah) throws IOException {
        PreparedStatement ps = null;
        try {
            String sql = "SELECT account_number_to,nasabah_name from mst_daftar_nasabah WHERE "
                    + "account_number ='" + nasabah.getAccountNumber() + "' AND account_number_to='" + nasabah.getAccountNumberTo() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "UPDATE mst_daftar_nasabah set nasabah_name=?,bank_code=? WHERE "
                        + "account_number ='" + nasabah.getAccountNumber() + "' AND account_number_to='" + nasabah.getAccountNumberTo() + "'";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(1, nasabah.getNasabahName());
                ps.setString(2, nasabah.getBankCode());
 
                ps.executeUpdate();
                ps.close();
                connection.closeConnection();
            } else {
                Vector column = cigistDao.getColumn("mst_daftar_nasabah", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_daftar_nasabah", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, nasabah.getAccountNumber());
                ps.setString(2, nasabah.getAccountNumberTo());
                ps.setString(3, nasabah.getNasabahName());
                ps.setString(4, nasabah.getBankCode());
                ps.setString(5, nasabah.getUserCreate());
                ps.setDate(6, nasabah.getDateCreate());
                ps.setTime(7, nasabah.getTimeCreate());

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
}
