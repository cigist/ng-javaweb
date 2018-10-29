/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author cigist
 */
public class TnrOrderNasabah extends MstNasabah {
    private String tranNumber;
    private String accountNumber;
    private String trnType;
    private BigDecimal amount;
    private BigDecimal tranFee;
    private String note;
    private String accountNumberAgen;
    private String status; 
    private String userTran;
    private Date tranDate;
    private Time tranTime;
    private String ipAddressTran;
    private String locationTran;

    public String getTranNumber() {
        return tranNumber;
    }

    public void setTranNumber(String tranNumber) {
        this.tranNumber = tranNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getTrnType() {
        return trnType;
    }

    public void setTrnType(String trnType) {
        this.trnType = trnType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTranFee() {
        return tranFee;
    }

    public void setTranFee(BigDecimal tranFee) {
        this.tranFee = tranFee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAccountNumberAgen() {
        return accountNumberAgen;
    }

    public void setAccountNumberAgen(String accountNumberAgen) {
        this.accountNumberAgen = accountNumberAgen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserTran() {
        return userTran;
    }

    public void setUserTran(String userTran) {
        this.userTran = userTran;
    }

    public Date getTranDate() {
        return tranDate;
    }

    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public Time getTranTime() {
        return tranTime;
    }

    public void setTranTime(Time tranTime) {
        this.tranTime = tranTime;
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
