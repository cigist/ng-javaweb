/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstFlightTicket;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cigist
 */
public class MstFlightTicketDao {

    CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstFlightTicket> getFlightTicketAll() {
        Vector<MstFlightTicket> listData = new Vector<>();
        try {
            String sql = "select a.flight_ticket_code,a.destination_from,a.destination_to,a.airline_code,b.airline_name,\n"
                    + "a.date_departure,a.date_return,a.seat_class,a.price,a.ticket,a.flag_Active\n"
                    + "from mst_flight_ticket a\n"
                    + "inner join mst_airline b on a.airline_code=b.airline_code where a.flag_Active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstFlightTicket ticket = new MstFlightTicket();
                ticket.setFlightTicketCode(rs.getString(1));
                ticket.setDestinationTrom(rs.getString(2));
                ticket.setDestinationTo(rs.getString(3));
                ticket.setAirlineCode(rs.getString(4));
                ticket.setAirlineName(rs.getString(5));
                ticket.setDateDeparture(rs.getDate(6));
                ticket.setDateReturn(rs.getDate(7));
                ticket.setSeatClass(rs.getString(8));
                ticket.setPrice(rs.getBigDecimal(9));
                ticket.setTicket(rs.getInt(10));
                ticket.setFlagActive(rs.getString(11));

                listData.add(ticket);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    public Vector<MstFlightTicket> getFlightTicket(MstFlightTicket pTicket) {
        Vector<MstFlightTicket> listData = new Vector<>();
        try {
            String sql = "select a.flight_ticket_code,a.airline_code,b.airline_name,a.price,b.link_icon from mst_flight_ticket a\n"
                    + "inner join mst_airline b on a.airline_code=b.airline_code\n"
                    + "where a.destination_from='" + pTicket.getDestinationTrom() + "' and a.destination_to='" + pTicket.getDestinationTo() + "' "
                    + "and a.date_departure='" + pTicket.getDateDeparture() + "' and \n"
                    + "a.date_return='" + pTicket.getDateReturn() + "' and a.seat_class='" + pTicket.getSeatClass() + "' and ticket >=" + pTicket.getTicket() + " and a.flag_active='Yes';";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstFlightTicket ticket = new MstFlightTicket();
                ticket.setFlightTicketCode(rs.getString(1));
                ticket.setAirlineCode(rs.getString(2));
                ticket.setAirlineName(rs.getString(3));
                ticket.setPrice(rs.getBigDecimal(4));
                ticket.setLinkIcon(rs.getString(5));

                listData.add(ticket);

            }

            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }

    synchronized public boolean insertUpdate(MstFlightTicket ticket) throws IOException {
        PreparedStatement ps = null;
        try {
            String sql = "select flight_ticket_code from mst_flight_ticket where flight_ticket_code='" + ticket.getFlightTicketCode() + "' and \n"
                    + "destination_from='" + ticket.getDestinationTrom() + "' and deatination_to='" + ticket.getDestinationTo() + "' "
                    + "and airline_code='" + ticket.getAirlineCode() + "' and date_departure='" + ticket.getDateDeparture() + "'\n"
                    + "and date_return='" + ticket.getDateReturn() + "' and seat_class='" + ticket.getClass() + "'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            if (rs.next()) {
                String QueryUpdate = "update mst_flight_ticket set price=?,ticket=?,flag_active=?,user_update=?,date_update=?,time_update=? \n"
                        + "	where flight_ticket_code=? and \n"
                        + "	destination_from=? and destination_to=? and airline_code=? and date_departure=?\n"
                        + "	and date_return=? and seat_class=? and flag_active=?";
                ps = connection.getConnection().prepareStatement(QueryUpdate);
                ps.setBigDecimal(1, ticket.getPrice());
                ps.setInt(2, ticket.getTicket());
                ps.setString(3, ticket.getFlagActive());
                ps.setString(4, ticket.getUserUpdate());
                ps.setDate(5, ticket.getDateUpdate());
                ps.setTime(6, ticket.getTimeUpdate());

                ps.setString(7, ticket.getFlightTicketCode());
                ps.setString(8, ticket.getDestinationTrom());
                ps.setString(9, ticket.getDestinationTo());
                ps.setString(10, ticket.getAirlineCode());
                ps.setDate(11, ticket.getDateDeparture());
                ps.setDate(12, ticket.getDateReturn());
                ps.setString(13, ticket.getSeatClass());

                ps.executeUpdate();
                ps.close();
                connection.closeConnection();
            } else {
                Vector column = cigistDao.getColumn("mst_flight_ticket", connection.getConnection());
                String QueryInsert = cigistDao.cigistQueryInsert("mst_flight_ticket", column);
                ps = connection.getConnection().prepareStatement(QueryInsert);
                ps.setString(1, ticket.getFlightTicketCode());
                ps.setString(2, ticket.getDestinationTrom());
                ps.setString(3, ticket.getDestinationTo());
                ps.setString(4, ticket.getAirlineCode());
                ps.setDate(5, ticket.getDateDeparture());
                ps.setDate(6, ticket.getDateReturn());
                ps.setString(7, ticket.getSeatClass());
                ps.setBigDecimal(8, ticket.getPrice());
                ps.setInt(9, ticket.getTicket());
                ps.setString(10, ticket.getFlagActive());
                ps.setString(11, ticket.getUserCreate());
                ps.setDate(12, ticket.getDateCreate());
                ps.setTime(13, ticket.getTimeCreate());
                ps.setString(14, ticket.getUserUpdate());
                ps.setDate(15, ticket.getDateUpdate());
                ps.setTime(16, ticket.getTimeUpdate());

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

    synchronized public boolean changePrice(MstFlightTicket ticket) throws IOException {
        PreparedStatement ps = null;
        try {
            String Query = "update mst_flight_ticket set price=?, user_update=?,date_update=?,time_update=?"
                    + "	where flight_ticket_code=? and"
                    + "	destination_from=? and destination_to=? and airline_code=? and date_departure=?"
                    + "	and date_return=? and seat_class=?";
            ps = connection.getConnection().prepareStatement(Query);
            ps.setBigDecimal(1, ticket.getPrice());
            ps.setString(2, ticket.getUserUpdate());
            ps.setDate(3, ticket.getDateUpdate());
            ps.setTime(4, ticket.getTimeUpdate());

            ps.setString(5, ticket.getFlightTicketCode());
            ps.setString(6, ticket.getDestinationTrom());
            ps.setString(7, ticket.getDestinationTo());
            ps.setString(8, ticket.getAirlineCode());
            ps.setDate(9, ticket.getDateDeparture());
            ps.setDate(10, ticket.getDateReturn());
            ps.setString(11, ticket.getSeatClass());
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
