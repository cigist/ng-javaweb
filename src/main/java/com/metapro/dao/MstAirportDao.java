/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstAirport;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cigist
 */
public class MstAirportDao {
    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstAirport> getAirportAll() {
        Vector<MstAirport> listData = new Vector<>();
        try {
            String sql = "select airport_code,airport_name,domestic,flag_active from mst_airport where flag_Active='Yes' order by airport_name asc;";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstAirport airport = new MstAirport();
                airport.setAirportCode(rs.getString(1));
                airport.setAirportName(rs.getString(2));
                airport.setDomestic(rs.getInt(3));
                airport.setFlagActive(rs.getString(4));

                listData.add(airport);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }
      synchronized public boolean insertUpdate(MstAirport airport) throws IOException {
        PreparedStatement ps = null;
        try {
            String sql = "select airport_code from mst_airport where "
                    + "airport_code='" + airport.getAirportCode()    + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "update mst_airport set airport_name=?,domestic=?,"
                        + "flag_active=?,user_update=?,date_update=?,time_update=? where airport_code=?";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(1, airport.getAirportName());
                ps.setInt(2, airport.getDomestic());
                ps.setString(3, airport.getFlagActive());
                ps.setString(4, airport.getUserUpdate());
                ps.setDate(5, airport.getDateUpdate());
                ps.setTime(6, airport.getTimeUpdate());
                ps.setString(7, airport.getAirportCode());

                ps.executeUpdate();
                ps.close();
                connection.closeConnection();
            } else {
                Vector column = cigistDao.getColumn("mst_airport", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_airport", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, airport.getAirportCode());
                ps.setString(2, airport.getAirportName());
                ps.setInt(3, airport.getDomestic());
                ps.setString(4, airport.getFlagActive());
                ps.setString(5, airport.getUserCreate());
                ps.setDate(6, airport.getDateCreate());
                ps.setTime(7, airport.getTimeCreate());
                ps.setString(8, airport.getUserUpdate());
                ps.setDate(9, airport.getDateUpdate());
                ps.setTime(10, airport.getTimeUpdate());

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

    synchronized public boolean changeAktif(MstAirport airport) throws IOException {
        PreparedStatement ps = null;
        try {
            String QueryUpdate = "update mst_airport set flag_active=?,user_update=?,date_update=?,time_update=? where airport_code=?";
            ps = connection.getConnection().prepareStatement(QueryUpdate);
            ps.setString(1, airport.getFlagActive());
            ps.setString(2, airport.getUserUpdate());
            ps.setDate(3, airport.getDateUpdate());
            ps.setTime(4, airport.getTimeUpdate());
            ps.setString(5, airport.getAirportCode());
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
