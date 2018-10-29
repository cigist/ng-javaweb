/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstPaketData;
import com.metapro.model.MstPulsa;
import com.metapro.model.MstVendor;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
public class MstVendorDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstVendor> getAllDataVendor() {
        Vector<MstVendor> listData = new Vector<>();
        try {
            String sql = "select vendor_code,vendor_name,vendor_type,flag_active from mst_vendor where flag_Active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstVendor vendor = new MstVendor();
                vendor.setVendorCode(rs.getString(1));
                vendor.setVendorName(rs.getString(2));
                vendor.setVendorType(rs.getString(3));
                vendor.setFlagActive(rs.getString(4));

                listData.add(vendor);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    public Vector<MstVendor> getByVendorType(String type) {
        Vector<MstVendor> listData = new Vector<>();
        try {
            String sql = "select vendor_code,vendor_name,vendor_type,flag_active from mst_vendor where flag_Active='Yes' and vendor_type='" + type + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstVendor vendor = new MstVendor();
                vendor.setVendorCode(rs.getString(1));
                vendor.setVendorName(rs.getString(2));
                vendor.setVendorType(rs.getString(3));
                vendor.setFlagActive(rs.getString(4));

                listData.add(vendor);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    public Vector<MstPulsa> getAllDataPulsa(String vendor) {
        Vector<MstPulsa> listData = new Vector<>();
        try {
            String sql = "select vendor_code,pulsa_code,pulsa_name,amount,vendor_price,price,flag_active from mst_pulsa "
                    + "where flag_Active='Yes' and vendor_code='" + vendor + "' and pulsa_type='P'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPulsa pulsa = new MstPulsa();
                pulsa.setVendorCode(rs.getString(1));
                pulsa.setPulsaCode(rs.getString(2));
                pulsa.setPulsaName(rs.getString(3));
                pulsa.setAmount(rs.getBigDecimal(4));
                pulsa.setVendorPrice(rs.getBigDecimal(5));
                pulsa.setPrice(rs.getBigDecimal(6));
                pulsa.setFlagActive(rs.getString(7));

                listData.add(pulsa);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }
    public Vector<MstPulsa> getAllPaketData(String vendor) {
        Vector<MstPulsa> listData = new Vector<>();
        try {
            String sql = "select vendor_code,pulsa_code,pulsa_name,amount,vendor_price,price,flag_active from mst_pulsa "
                    + "where flag_Active='Yes' and vendor_code='" + vendor + "' and pulsa_type='D'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPulsa pulsa = new MstPulsa();
                pulsa.setVendorCode(rs.getString(1));
                pulsa.setPulsaCode(rs.getString(2));
                pulsa.setPulsaName(rs.getString(3));
                pulsa.setAmount(rs.getBigDecimal(4));
                pulsa.setVendorPrice(rs.getBigDecimal(5));
                pulsa.setPrice(rs.getBigDecimal(6));
                pulsa.setFlagActive(rs.getString(7));

                listData.add(pulsa);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }
    public Vector<MstPulsa> getAllGame(String vendor) {
        Vector<MstPulsa> listData = new Vector<>();
        try {
            String sql = "select vendor_code,pulsa_code,pulsa_name,amount,vendor_price,price,flag_active from mst_pulsa "
                    + "where flag_Active='Yes' and vendor_code='" + vendor + "' and pulsa_type='G'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPulsa pulsa = new MstPulsa();
                pulsa.setVendorCode(rs.getString(1));
                pulsa.setPulsaCode(rs.getString(2));
                pulsa.setPulsaName(rs.getString(3));
                pulsa.setAmount(rs.getBigDecimal(4));
                pulsa.setVendorPrice(rs.getBigDecimal(5));
                pulsa.setPrice(rs.getBigDecimal(6));
                pulsa.setFlagActive(rs.getString(7));

                listData.add(pulsa);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }
    public Vector<MstPulsa> getAllDataPayment(String vendor) {
        Vector<MstPulsa> listData = new Vector<>();
        try {
            String sql = "select vendor_code,pulsa_code,pulsa_name,amount,vendor_price,price,flag_active from mst_pulsa "
                    + "where flag_Active='Yes' and vendor_code='" + vendor + "' and pulsa_type='F'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPulsa pulsa = new MstPulsa();
                pulsa.setVendorCode(rs.getString(1));
                pulsa.setPulsaCode(rs.getString(2));
                pulsa.setPulsaName(rs.getString(3));
                pulsa.setAmount(rs.getBigDecimal(4));
                pulsa.setVendorPrice(rs.getBigDecimal(5));
                pulsa.setPrice(rs.getBigDecimal(6));
                pulsa.setFlagActive(rs.getString(7));

                listData.add(pulsa);

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
                    + "where flag_Active='Yes' and vendor_code='" + vendor + "' and pulsa_type='D'";
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

    synchronized public boolean insertUpdate(MstVendor vendor) throws IOException {
        PreparedStatement ps = null;
        try {
            String sql = "select vendor_code from mst_vendor where vendor_code='" + vendor.getVendorCode() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "update mst_vendor set vendor_name=?,flag_active=?,user_update=?,date_update=?,time_update=? where vendor_code=?";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(1, vendor.getVendorName());
                ps.setString(2, vendor.getFlagActive());
                ps.setString(3, vendor.getUserUpdate());
                ps.setDate(4, vendor.getDateUpdate());
                ps.setTime(5, vendor.getTimeUpdate());
                ps.setString(6, vendor.getVendorCode());
                ps.executeUpdate();
                connection.closeConnection();
            } else {
                Vector column = cigistDao.getColumn("mst_vendor", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_vendor", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, vendor.getVendorCode());
                ps.setString(2, vendor.getVendorName());
                ps.setString(3, vendor.getVendorType());
                ps.setString(4, vendor.getFlagActive());
                ps.setString(5, vendor.getUserCreate());
                ps.setDate(6, vendor.getDateCreate());
                ps.setTime(7, vendor.getTimeCreate());
                ps.setString(8, vendor.getUserUpdate());
                ps.setDate(9, vendor.getDateUpdate());
                ps.setTime(10, vendor.getTimeUpdate());
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
