/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author cigist
 */
public class TrnPaketData extends MstPaketData implements Serializable{
    private String trnNumber;
    private String accountNumber;
    private String paketDataCode;
    private String phoneNumber;
    private BigDecimal amount;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPaketDataCode() {
        return paketDataCode;
    }

    public void setPaketDataCode(String paketDataCode) {
        this.paketDataCode = paketDataCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
