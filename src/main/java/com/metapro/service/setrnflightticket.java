/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstFlightTicketDao;
import com.metapro.dao.ValidateAccountDao;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = "/api/setrnflightticket")
public class setrnflightticket extends HttpServlet {

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
            
            } else if (action.equals("change")) {
//                MstFlightTicket ticket = new MstFlightTicket();
//                ticket.setDestinationTrom(request.getParameter("xdestinationfrom"));
//                ticket.setDestinationTo(request.getParameter("xdestinationto"));
//                ticket.setDateDeparture(Date.valueOf(request.getParameter("xdatedeparture")));
//                ticket.setDateReturn(Date.valueOf(request.getParameter("xdatereturn")));
//                ticket.setSeatClass(request.getParameter("xseatclass"));
//                ticket.setTicket(Integer.valueOf(request.getParameter("xnumber")));
//
//                Vector<MstFlightTicket> listFlihgtTicket = new Vector<>();
//                listFlihgtTicket = flihgtTicketDao.getFlightTicket(ticket);
                result.put("STATUS", "OK");
                result.put("MESSAGE", "SUCCESS");
                result.put("BOOKING_CODE", st.autoCode("TKT"));
            }

        } else {
            result.put("STATUS", "ERORR");
            result.put("MESSAGE", "ACCESS DENIED!!");
            result.put("BOOKING_CODE", "");
        }

        out.println(result);

    }
}
