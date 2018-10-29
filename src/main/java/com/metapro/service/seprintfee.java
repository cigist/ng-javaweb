/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.service;

import com.cigist.framework.conn.CigistConnection;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Irwan Cigist <cigist.developer@gmail.com>
 */
@WebServlet(urlPatterns = "/api/seprintfee")
public class seprintfee extends HttpServlet {

    CigistConnection connection = new CigistConnection();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            response.addHeader("Access-Control-Allow-Origin", "*");
            // set header as pdf
            response.setContentType("application/pdf");
            String xname=request.getParameter("xname");
            System.out.println(xname);
            
            // set input and output stream
            ServletOutputStream servletOutputStream = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis;
            BufferedInputStream bufferedInputStream;
            ServletContext context = getServletContext();
            String reportLocation = context.getRealPath("");
            // get report
            
            fis = new FileInputStream(reportLocation + "/report/TrnFeeRpt.jasper");
            System.out.println(fis.toString());
            bufferedInputStream = new BufferedInputStream(fis);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(), connection.getConnection());
            
            try {
                // export to pdf
                JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            } catch (JRException ex) {
                Logger.getLogger(seprintfee.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            response.setContentLength(baos.size());
            baos.writeTo(servletOutputStream);
            
            // close it
            fis.close();
            bufferedInputStream.close();
            
        } catch (JRException ex) {
            Logger.getLogger(seprintfee.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
