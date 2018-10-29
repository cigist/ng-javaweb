/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.util.CigistDateTimeNow;
import com.cigist.framework.util.StringFormat;
import com.metapro.dao.MstUserDao;
import com.metapro.dao.ValidateAccountDao;
import com.metapro.model.MstUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
@WebServlet(urlPatterns = "/api/semstuser")
public class semstuser extends HttpServlet {

    private String message;
    StringFormat st = new StringFormat();
    private static final String CUSTOMER_CODE = "USR";
    private static final long serialVersionUID = 1L;

    JSONObject obj = new JSONObject();
    JSONObject status = new JSONObject();
    MstUserDao service = new MstUserDao();
    ValidateAccountDao valiDao = new ValidateAccountDao();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        response.addHeader("Access-Control-Allow-Origin", "*");
        String action = request.getParameter("xaction");
        String username = request.getParameter("xusername");

        if (action.equals("list")) {
            Vector<MstUser> dataUser = new Vector<>();
            dataUser = service.getAllData();
            obj.put("LIST_USER", dataUser);
            obj.put("STATUS", "OK");
        } else {
            String insert = service.delete(username);
            if (insert.equals("OK")) {
                status.put("STATUS", "OK");
            } else {
                status.put("STATUS", "ERORR");
            }
        }

        out.println(obj);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();

        String token = request.getParameter("xtoken");
        String username = request.getParameter("xusername");
        String xaction = request.getParameter("xaction");
        String oldpassword = request.getParameter("xoldpassword");
        String xuser=request.getParameter("xuser");

        MstUser user = new MstUser();
        user.setUserCode(st.autoCode(CUSTOMER_CODE));
        user.setUsername(request.getParameter("xusername").trim());
        user.setPassword(request.getParameter("xpassword").trim());
        user.setFlagActive(request.getParameter("xflagactive"));
        user.setUserCreate(xuser);
        user.setDateCreate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        user.setTimeCreate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        user.setUserUpdate(xuser);
        user.setDateUpdate(Date.valueOf(CigistDateTimeNow.getDateTimeNow("yyyy-MM-dd")));
        user.setTimeUpdate(Time.valueOf(CigistDateTimeNow.getDateTimeNow("hh:mm:ss")));
        user.setLocation(request.getParameter("xlocation"));
        user.setIpAddress(request.getRemoteAddr());

        boolean validate = valiDao.validateToken(xuser, token);
        if (validate && !token.isEmpty() && !xuser.isEmpty()) {
            if (xaction.equals("insert")) {
                String insert = service.isertUpdate(user);
                if (insert.equals("OK")) {
                    status.put("STATUS",insert);
                } else {
                    status.put("STATUS", insert);
                }

            } else if (xaction.equals("update")) {
                boolean passValidate = service.checkPassword(username,oldpassword);
                if (passValidate) {
                    String insert = service.changePassword(user);
                    if (insert.equals("OK")) {
                        status.put("STATUS", insert);
                    } else {
                        status.put("STATUS", insert);
                    }
                } else {
                    status.put("STATUS","PASSWORD LAMA ANDA SALAH");
                }

            }
        } else {
            status.put("STATUS", "FAILED");
        }

        out.println(status);
    }

}
