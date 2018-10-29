/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.TrnAgenLocation;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cigist
 */
public class TrnAgenLocationDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    synchronized public Boolean updateLocation(TrnAgenLocation obj) {
        PreparedStatement ps = null;
        try {
            String sql = "select account_agen_number from trn_agen_location where account_agen_number='" + obj.getAgenAccountNumber() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                Vector column = cigistDao.getColumn("trn_agen_location", connection.getConnection());
                String QueryUpdate = cigistDao.cigistQueryUpdate("trn_agen_location", column,"ACCOUNT_AGEN_NUMBER");
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(6, obj.getAgenAccountNumber());
                
                ps.setString(1, obj.getLat());
                ps.setString(2, obj.getLng());
                ps.setString(3, obj.getImei());
                ps.setDate(4, obj.getLocationDate());
                ps.setTime(5, obj.getLocationTime());

                ps.executeUpdate();

                ps.close();
                connection.closeConnection();
                return true;

            } else {
                Vector column = cigistDao.getColumn("trn_agen_location", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("trn_agen_location", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, obj.getAgenAccountNumber());
                ps.setString(2, obj.getLat());
                ps.setString(3, obj.getLng());
                ps.setString(4, obj.getImei());
                ps.setDate(5, obj.getLocationDate());
                ps.setTime(6, obj.getLocationTime());
                
                System.out.println(QueryInsert);
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
    public Vector<TrnAgenLocation> getAgenLocation() {
        Vector<TrnAgenLocation> listData = new Vector<>();
        try {
            String sql = "select account_agen_number,imei,lat,lng from trn_agen_location";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                TrnAgenLocation agen = new TrnAgenLocation();
                agen.setAgenAccountNumber(rs.getString(1));
                agen.setImei(rs.getString(2));
                agen.setLat(rs.getString(3));
                agen.setLng(rs.getString(4));
  
                listData.add(agen);
                
                System.out.println(rs.getString(1));

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }
}
