/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstPulsa;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
public class MstPulsaDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstPulsa> getPulsaAll(String type) {
        Vector<MstPulsa> listData = new Vector<>();
        try {
            String sql = "select vendor_code,pulsa_code,pulsa_name,amount,vendor_price,price,flag_active,pulsa_type from mst_pulsa "
                    + "where flag_Active='Yes' and pulsa_type='" + type + "'";
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
                pulsa.setPulsaType(rs.getString(8));

                listData.add(pulsa);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    public Vector<MstPulsa> getPulsaByVendor(String vendor, String type) {
        Vector<MstPulsa> listData = new Vector<>();
        try {
            String sql = "select vendor_code,pulsa_code,pulsa_name,amount,vendor_price,price,flag_active,pulsa_type from mst_pulsa "
                    + "where flag_Active='Yes' and vendor_code='" + vendor + " and pulsa_type='" + type + "'";
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
                pulsa.setPulsaType(rs.getString(8));

                listData.add(pulsa);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    synchronized public boolean insertUpdate(MstPulsa pulsa) throws IOException {
        PreparedStatement ps = null;
        try {
            String sql = "select vendor_code,pulsa_code from mst_pulsa where "
                    + "vendor_code='" + pulsa.getVendorCode() + "' and pulsa_code='" + pulsa.getPulsaCode() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "update mst_pulsa set pulsa_name=?,amount=?,vendor_price=?,price=?,discount=?,"
                        + "flag_active=?,user_update=?,date_update=?,time_update=? where vendor_code=? and pulsa_code=?";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(1, pulsa.getPulsaName());
                ps.setBigDecimal(2, pulsa.getAmount());
                ps.setBigDecimal(3, pulsa.getVendorPrice());
                ps.setBigDecimal(4, pulsa.getPrice());
                ps.setFloat(5, pulsa.getDiscount());
                ps.setString(6, pulsa.getFlagActive());
                ps.setString(7, pulsa.getUserUpdate());
                ps.setDate(8, pulsa.getDateUpdate());
                ps.setTime(9, pulsa.getTimeUpdate());
                ps.setString(10, pulsa.getVendorCode());
                ps.setString(11, pulsa.getPulsaCode());

                ps.executeUpdate();
                connection.closeConnection();
            } else {
                Vector column = cigistDao.getColumn("mst_pulsa", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_pulsa", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, pulsa.getVendorCode());
                ps.setString(2, pulsa.getPulsaCode());
                ps.setString(3, pulsa.getPulsaName());
                ps.setBigDecimal(4, pulsa.getAmount());
                ps.setBigDecimal(5, pulsa.getVendorPrice());
                ps.setBigDecimal(6, pulsa.getPrice());
                ps.setFloat(7, pulsa.getDiscount());
                ps.setString(8, pulsa.getFlagActive());
                ps.setString(9, pulsa.getUserCreate());
                ps.setDate(10, pulsa.getDateCreate());
                ps.setTime(11, pulsa.getTimeCreate());
                ps.setString(12, pulsa.getUserUpdate());
                ps.setDate(13, pulsa.getDateUpdate());
                ps.setTime(14, pulsa.getTimeUpdate());
                ps.setString(15, pulsa.getPulsaType());
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

    synchronized public boolean changePrice(MstPulsa pulsa) throws IOException {
        PreparedStatement ps = null;
        try {

            String QueryUpdate = "update mst_pulsa set price=?,discount=?,"
                    + "user_update=?,date_update=?,time_update=? where vendor_code=? and pulsa_code=?";
            ps = connection.getConnection().prepareStatement(QueryUpdate);
            ps.setBigDecimal(1, pulsa.getPrice());
            ps.setFloat(2, pulsa.getDiscount());
            ps.setString(3, pulsa.getUserUpdate());
            ps.setDate(4, pulsa.getDateUpdate());
            ps.setTime(5, pulsa.getTimeUpdate());
            ps.setString(6, pulsa.getVendorCode());
            ps.setString(7, pulsa.getPulsaCode());

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
