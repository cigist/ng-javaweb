/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstPaketData;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
public class MstPaketDataDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstPaketData> getPaketDataAll() {
        Vector<MstPaketData> listData = new Vector<>();
        try {
            String sql = "select vendor_code,paket_data_code,paket_data_name,kuota,vendor_price,price,flag_active from mst_paket_data\n"
                    + "where flag_Active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPaketData data = new MstPaketData();
                data.setVendorCode(rs.getString(1));
                data.setPaketDataCode(rs.getString(2));
                data.setPaketDataName(rs.getString(3));
                data.setKuota(rs.getString(4));
                data.setVendorPrice(rs.getBigDecimal(5));
                data.setPrice(rs.getBigDecimal(6));
                data.setFlagActive(rs.getString(7));

                listData.add(data);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    public Vector<MstPaketData> getDataByVendor(String vendor) {
        Vector<MstPaketData> listData = new Vector<>();
        try {
            String sql = "select vendor_code,paket_data_code,paket_data_name,kuota,vendor_price,price,flag_active from mst_paket_data\n"
                    + "where flag_Active='Yes' and vendor_code='"+vendor+"'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPaketData data = new MstPaketData();
                data.setVendorCode(rs.getString(1));
                data.setPaketDataCode(rs.getString(2));
                data.setPaketDataName(rs.getString(3));
                data.setKuota(rs.getString(4));
                data.setVendorPrice(rs.getBigDecimal(5));
                data.setPrice(rs.getBigDecimal(6));
                data.setFlagActive(rs.getString(7));

                listData.add(data);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    synchronized public boolean insertUpdate(MstPaketData data) throws IOException {
        PreparedStatement ps = null;
        try {
            String sql = "select vendor_code,paket_data_code from mst_paket_data where "
                    + "vendor_code='" + data.getVendorCode() + "' and paket_data_code='" + data.getPaketDataCode() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "update mst_paket_data set paket_data_name=?,kuota=?,vendor_price=?,price=?,discount=?,"
                        + "flag_active=?,user_update=?,date_update=?,time_update=? where vendor_code=? and paket_data_code=?";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(1, data.getPaketDataName());
                ps.setString(2, data.getKuota());
                ps.setBigDecimal(3, data.getVendorPrice());
                ps.setBigDecimal(4, data.getPrice());
                ps.setFloat(5, data.getDiscount());
                ps.setString(6, data.getFlagActive());
                ps.setString(7, data.getUserUpdate());
                ps.setDate(8, data.getDateUpdate());
                ps.setTime(9, data.getTimeUpdate());
                
                ps.setString(10, data.getVendorCode());
                ps.setString(11, data.getPaketDataCode());

                ps.executeUpdate();
                ps.close();
                connection.closeConnection();
            } else {
                Vector column = cigistDao.getColumn("mst_paket_data", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_paket_data", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, data.getVendorCode());
                ps.setString(2, data.getPaketDataCode());
                ps.setString(3, data.getPaketDataName());
                ps.setString(4, data.getKuota());
                ps.setBigDecimal(5, data.getVendorPrice());
                ps.setBigDecimal(6, data.getPrice());
                ps.setFloat(7, data.getDiscount());
                ps.setString(8, data.getFlagActive());
                ps.setString(9, data.getUserCreate());
                ps.setDate(10, data.getDateCreate());
                ps.setTime(11, data.getTimeCreate());
                ps.setString(12, data.getUserUpdate());
                ps.setDate(13, data.getDateUpdate());
                ps.setTime(14, data.getTimeUpdate());

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

    synchronized public boolean changePrice(MstPaketData data) throws IOException {
        PreparedStatement ps = null;
        try {

            String QueryUpdate = "update mst_paket_data set price=?,discount=?,"
                    + "user_update=?,date_update=?,time_update=? where vendor_code=? and paket_data_code=?";
            ps = connection.getConnection().prepareStatement(QueryUpdate);
            ps.setBigDecimal(1, data.getPrice());
            ps.setFloat(2, data.getDiscount());
            ps.setString(3, data.getUserUpdate());
            ps.setDate(4, data.getDateUpdate());
            ps.setTime(5, data.getTimeUpdate());
            ps.setString(6, data.getVendorCode());
            ps.setString(7, data.getPaketDataCode());

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
