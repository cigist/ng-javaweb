/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstPpob;
import com.metapro.model.MstPulsa;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
public class MstPpobDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstPpob> getAllPpob() {
        Vector<MstPpob> listData = new Vector<>();
        try {
            String sql = "SELECT VENDOR_CODE,PPOB_CODE,PPOB_NAME,VENDOR_PRICE,PRICE,DISCOUNT,VENDOR_FEE,PPOB_TYPE,RESPONSE FROM MST_PPOB";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPpob PPOB = new MstPpob();
                PPOB.setVendorCode(rs.getString(1));
                PPOB.setPpobCode(rs.getString(2));
                PPOB.setPpobName(rs.getString(3));
                PPOB.setVendorPrice(rs.getBigDecimal(4));
                PPOB.setPrice(rs.getBigDecimal(5));
                PPOB.setDiscount(rs.getFloat(6));
                PPOB.setVendorFee(rs.getBigDecimal(7));
                PPOB.setPpobType(rs.getString(8));
                PPOB.setResponse(rs.getString(9));
                listData.add(PPOB);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    public Vector<MstPpob> getPpobByType(String type) {
        Vector<MstPpob> listData = new Vector<>();
        try {
            String sql = "SELECT VENDOR_CODE,PPOB_CODE,PPOB_NAME,VENDOR_PRICE,PRICE,DISCOUNT,VENDOR_FEE,PPOB_TYPE,RESPONSE FROM MST_PPOB WHERE PPOB_TYPE='" + type + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPpob PPOB = new MstPpob();
                 PPOB.setVendorCode(rs.getString(1));
                PPOB.setPpobCode(rs.getString(2));
                PPOB.setPpobName(rs.getString(3));
                PPOB.setVendorPrice(rs.getBigDecimal(4));
                PPOB.setPrice(rs.getBigDecimal(5));
                PPOB.setDiscount(rs.getFloat(6));
                PPOB.setVendorFee(rs.getBigDecimal(7));
                PPOB.setPpobType(rs.getString(8));
                PPOB.setResponse(rs.getString(9));
                listData.add(PPOB);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    public Vector<MstPpob> getPpobByVendor(String vendor, String type) {
        Vector<MstPpob> listData = new Vector<>();
        try {
            String sql = "SELECT VENDOR_CODE,PPOB_CODE,PPOB_NAME,VENDOR_PRICE,PRICE,DISCOUNT,VENDOR_FEE,PPOB_TYPE,RESPONSE FROM MST_PPOB"
                    + " WHERE VENDOR_CODE='" + vendor + "' AND PPOB_TYPE='" + type + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstPpob PPOB = new MstPpob();
                PPOB.setVendorCode(rs.getString(1));
                PPOB.setPpobCode(rs.getString(2));
                PPOB.setPpobName(rs.getString(3));
                PPOB.setVendorPrice(rs.getBigDecimal(4));
                PPOB.setPrice(rs.getBigDecimal(5));
                PPOB.setDiscount(rs.getFloat(6));
                PPOB.setVendorFee(rs.getBigDecimal(7));
                PPOB.setPpobType(rs.getString(8));
                PPOB.setResponse(rs.getString(9));
                listData.add(PPOB);

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

    synchronized public boolean insertUpdate(MstPpob ppob) throws IOException {
        PreparedStatement ps = null;
        try {
            String sql = "select vendor_code,ppob_code from mst_ppob where "
                    + "vendor_code='" + ppob.getVendorCode() + "' and ppob_code='" + ppob.getPpobCode() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "update mst_ppob set ppob_name=?,vendor_price=?,price=?,discount=?,vendor_fee=?,"
                        + "flag_active=?,user_update=?,date_update=?,time_update=?,response=? where vendor_code=? and ppob_code=?";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(1, ppob.getPpobName());
                ps.setBigDecimal(2, ppob.getVendorPrice());
                ps.setBigDecimal(3, ppob.getPrice());
                ps.setFloat(4, ppob.getDiscount());
                ps.setBigDecimal(5, ppob.getVendorFee());
                ps.setString(6, ppob.getFlagActive());
                ps.setString(7, ppob.getUserUpdate());
                ps.setDate(8, ppob.getDateUpdate());
                ps.setTime(9, ppob.getTimeUpdate());
                ps.setString(10, ppob.getVendorCode());
                ps.setString(11, ppob.getPpobCode());
                ps.setString(12, ppob.getResponse());

                ps.executeUpdate();
                connection.closeConnection();
            } else {
                Vector column = cigistDao.getColumn("mst_ppob", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_ppob", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, ppob.getVendorCode());
                ps.setString(2, ppob.getPpobCode());
                ps.setString(3, ppob.getPpobName());
                ps.setBigDecimal(4, ppob.getVendorPrice());
                ps.setBigDecimal(5, ppob.getPrice());
                ps.setFloat(6, ppob.getDiscount());
                ps.setBigDecimal(7, ppob.getVendorFee());
                ps.setString(8, ppob.getPpobType());
                ps.setString(9, ppob.getFlagActive());
                ps.setString(10, ppob.getUserCreate());
                ps.setDate(11, ppob.getDateCreate());
                ps.setTime(12, ppob.getTimeCreate());
                ps.setString(13, ppob.getUserUpdate());
                ps.setDate(14, ppob.getDateUpdate());
                ps.setTime(15, ppob.getTimeUpdate());
                ps.setString(16, ppob.getResponse());
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

    synchronized public boolean changePrice(MstPpob ppob) throws IOException {
        PreparedStatement ps = null;
        try {

            String QueryUpdate = "update mst_ppob set price=?,discount=?,"
                    + "user_update=?,date_update=?,time_update=? where vendor_code=? and ppob_code=?";
            ps = connection.getConnection().prepareStatement(QueryUpdate);
            ps.setBigDecimal(1, ppob.getPrice());
            ps.setFloat(2, ppob.getDiscount());
            ps.setString(3, ppob.getUserUpdate());
            ps.setDate(4, ppob.getDateUpdate());
            ps.setTime(5, ppob.getTimeUpdate());
            ps.setString(6, ppob.getVendorCode());
            ps.setString(7, ppob.getPpobCode());

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
