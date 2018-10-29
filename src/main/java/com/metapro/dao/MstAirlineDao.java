/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstAirline;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cigist
 */
public class MstAirlineDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstAirline> getAirportAll() {
        Vector<MstAirline> listData = new Vector<>();
        try {
            String sql = "select airline_code,airline_name,domestic,flag_active from mst_airline where flag_Active='Yes';";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstAirline airline = new MstAirline();
                airline.setAirlineCode(rs.getString(1));
                airline.setAirlineName(rs.getString(2));
                airline.setDomestic(rs.getInt(3));
                airline.setFlagActive(rs.getString(4));

                listData.add(airline);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    synchronized public boolean insertUpdate(MstAirline airline) throws IOException {
        PreparedStatement ps = null;
        try {
            String sql = "select airline_code from mst_airline where "
                    + "airline_code='" + airline.getAirlineCode() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "update mst_airline set airline_name=?,domestic=?,link_icon=?,"
                        + "flag_active=?,user_update=?,date_update=?,time_update=? where    airline_code=?";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setString(1, airline.getAirlineName());
                ps.setInt(2, airline.getDomestic());
                ps.setString(3, airline.getLinkIcon());
                ps.setString(4, airline.getFlagActive());
                ps.setString(5, airline.getUserUpdate());
                ps.setDate(6, airline.getDateUpdate());
                ps.setTime(7, airline.getTimeUpdate());
                ps.setString(8, airline.getAirlineCode());

                ps.executeUpdate();
                ps.close();
                connection.closeConnection();
            } else {
                Vector column = cigistDao.getColumn("mst_airline", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_airline", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, airline.getAirlineCode());
                ps.setString(2, airline.getAirlineName());
                ps.setInt(3, airline.getDomestic());
                ps.setString(4, airline.getLinkIcon());
                ps.setString(5, airline.getFlagActive());
                ps.setString(6, airline.getUserCreate());
                ps.setDate(7, airline.getDateCreate());
                ps.setTime(8, airline.getTimeCreate());
                ps.setString(9, airline.getUserUpdate());
                ps.setDate(10, airline.getDateUpdate());
                ps.setTime(11, airline.getTimeUpdate());

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

    synchronized public boolean changeAktif(MstAirline airline) throws IOException {
        PreparedStatement ps = null;
        try {
            String QueryUpdate = "update mst_airline set flag_active=?,user_update=?,date_update=?,time_update=? where airline_code=?";
            ps = connection.getConnection().prepareStatement(QueryUpdate);
            ps.setString(1, airline.getFlagActive());
            ps.setString(2, airline.getUserUpdate());
            ps.setDate(3, airline.getDateUpdate());
            ps.setTime(4, airline.getTimeUpdate());
            ps.setString(5, airline.getAirlineCode());
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
