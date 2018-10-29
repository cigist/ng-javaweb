/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.dao;

import com.cigist.framework.conn.CigistConnection;
import com.cigist.framework.core.CigistDao;
import com.metapro.model.MstBank;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author cigist
 */
public class MstBankDao {
     CigistConnection connection = new CigistConnection();
    CigistDao cigistDao = new CigistDao();

    public Vector<MstBank> getDataBankAll() {
        Vector<MstBank> listData = new Vector<>();
        try {
            String sql = "select bank_code,bank_name from mst_bank where flag_active='Yes'";
            ResultSet rs = connection.getConnection().createStatement().executeQuery(sql);
            while (rs.next()) {
                MstBank nasabah = new MstBank();
                nasabah.setBankCode(rs.getString(1));
                nasabah.setBankName(rs.getString(2));

                listData.add(nasabah);

            }
            rs.close();
            connection.closeConnection();
        } catch (IOException | SQLException ex) {
            System.out.println(ex);
        }
        return listData;
    }
}
