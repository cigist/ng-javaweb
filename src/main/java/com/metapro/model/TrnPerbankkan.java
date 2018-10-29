/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author cigist
 */
public class TrnPerbankkan extends MstCustomer implements Serializable{
    private String trnNumber;
    private String bankCode;
    private String productCode;
    private String userTran;
    private Date dateTran;
    private Time timeTran;
    private String ipAddressTran;
    private String locationTran;

    public String getTrnNumber() {
        return trnNumber;
    }

    public void setTrnNumber(String trnNumber) {
        this.trnNumber = trnNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getUserTran() {
        return userTran;
    }

    public void setUserTran(String userTran) {
        this.userTran = userTran;
    }

    public Date getDateTran() {
        return dateTran;
    }

    public void setDateTran(Date dateTran) {
        this.dateTran = dateTran;
    }

    public Time getTimeTran() {
        return timeTran;
    }

    public void setTimeTran(Time timeTran) {
        this.timeTran = timeTran;
    }

    public String getIpAddressTran() {
        return ipAddressTran;
    }

    public void setIpAddressTran(String ipAddressTran) {
        this.ipAddressTran = ipAddressTran;
    }

    public String getLocationTran() {
        return locationTran;
    }

    public void setLocationTran(String locationTran) {
        this.locationTran = locationTran;
    }
    
}
