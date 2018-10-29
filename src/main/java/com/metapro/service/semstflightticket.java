/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstFlightTicketDao;
import com.metapro.dao.MstPulsaDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstFlightTicket;
import com.metapro.model.MstPulsa;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
@WebServlet(urlPatterns = "/api/semstflightticket")
public class semstflightticket extends HttpServlet {

    private static final long serialVersionUID = 1L;
    StringFormat st = new StringFormat();
    JSONArray list = new JSONArray();
    JSONObject result = new JSONObject();

    ValidateAccountDao validate = new ValidateAccountDao();
    MstFlightTicketDao flihgtTicketDao = new MstFlightTicketDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Cache-Control", "public");

        String username = request.getParameter("xusername");
        String token = request.getParameter("xtoken");
        String action = request.getParameter("xaction");

        boolean validateAccount = validate.validateToken(username, token);
        if (validateAccount) {
            if (action.equals("insert")) {
                String xprice = request.getParameter("xprice");
                BigDecimal price = new BigDecimal(xprice);

                MstFlightTicket ticket = new MstFlightTicket();
                ticket.setFlightTicketCode(request.getParameter("xflightticketcode").toUpperCase());
                ticket.setDestinationTrom(request.getParameter("xdestinationfrom").toUpperCase());
                ticket.setDestinationTo(request.getParameter("xdestinationto").toUpperCase());
                ticket.setAirlineCode(request.getParameter("xairlinecode").toUpperCase());
                ticket.setDateDeparture(Date.valueOf(request.getParameter("xdatedeparture")));
                ticket.setDateReturn(Date.valueOf(request.getParameter("xdatereturn")));
                ticket.setSeatClass(request.getParameter("xseatclass"));
                ticket.setTicket(Integer.valueOf(request.getParameter("xticket")));
                ticket.setPrice(price);
                ticket.setUserCreate(request.getParameter("xusername"));
                ticket.setFlagActive(request.getParameter("xflagactive"));
                ticket.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                ticket.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
                ticket.setUserUpdate(request.getParameter("xusername"));
                ticket.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                ticket.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = flihgtTicketDao.insertUpdate(ticket);
                if (resultInsert) {
                    result.put("STATUS", "OK");
                    result.put("MESSAGE", "SUCCESS");
                    result.put("DATA", "");
                } else {
                    result.put("STATUS", "ERORR");
                    result.put("MESSAGE", "FAILED PROCESS");
                    result.put("BOOKING_CODE", "");
                }
            } else if (action.equals("ticket")) {
                MstFlightTicket ticket = new MstFlightTicket();
                ticket.setDestinationTrom(request.getParameter("xdestinationfrom"));
                ticket.setDestinationTo(request.getParameter("xdestinationto"));
                ticket.setDateDeparture(Date.valueOf(request.getParameter("xdatedeparture")));
                ticket.setDateReturn(Date.valueOf(request.getParameter("xdatereturn")));
                ticket.setSeatClass(request.getParameter("xseatclass"));
                ticket.setTicket(Integer.valueOf(request.getParameter("xnumber")));

                Vector<MstFlightTicket> listFlihgtTicket = new Vector<>();
                listFlihgtTicket = flihgtTicketDao.getFlightTicket(ticket);
                result.put("STATUS", "OK");
                result.put("MESSAGE", "SUCCESS");
                result.put("DATA", listFlihgtTicket);
            } else if (action.equals("list")) {
                Vector<MstFlightTicket> listFlihgtTicket = new Vector<>();
                listFlihgtTicket = flihgtTicketDao.getFlightTicketAll();
                result.put("STATUS", "OK");
                result.put("MESSAGE", "SUCCESS");
                result.put("DATA", listFlihgtTicket);
            }else if (action.equals("price")) {
                String xprice = request.getParameter("xprice");
                BigDecimal price = new BigDecimal(xprice);
                MstFlightTicket ticket = new MstFlightTicket();
                ticket.setFlightTicketCode(request.getParameter("xflightticketcode"));
                ticket.setDestinationTrom(request.getParameter("xdestinationfrom"));
                ticket.setDestinationTo(request.getParameter("xdestinationto"));
                ticket.setAirlineCode(request.getParameter("xairlinecode"));
                ticket.setDateDeparture(Date.valueOf(request.getParameter("xdatedeparture")));
                ticket.setDateReturn(Date.valueOf(request.getParameter("xdatereturn")));
                ticket.setSeatClass(request.getParameter("xseatclass"));
                ticket.setPrice(price);
                ticket.setUserUpdate(request.getParameter("xusername"));
                ticket.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
                ticket.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));

                boolean resultInsert = flihgtTicketDao.changePrice(ticket);
                if (resultInsert) {
                    result.put("STATUS", "OK");
                    result.put("MESSAGE", "SUCCESS");
                    result.put("DATA", "CHANGE PRICE SUCCESS");
                } else {
                    result.put("STATUS", "ERORR");
                    result.put("MESSAGE", "FAILED PROCESS");
                    result.put("BOOKING_CODE", "");
                }
            } else {
                result.put("STATUS", "ERORR");
                result.put("MESSAGE", "ACCESS DENIED!!");
                result.put("DATA", "NOT FOUND");
            }

        } else {
            result.put("STATUS", "ERORR");
            result.put("MESSAGE", "ACCESS DENIED!!");
            result.put("DATA", "NOT FOUND");
        }

        out.println(result);

    }
}
