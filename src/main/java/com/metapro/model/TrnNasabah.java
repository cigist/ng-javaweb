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
 * @author irwan cigist /irwancigist@gmail.com
 */
public class TrnNasabah extends MstAgen implements Serializable {

    private String tranNumber;
    private String accountNumber;
    private String trnType;
    private BigDecimal amount;
    private BigDecimal tranFee;
    private String note;
    private String userTran;
    private Date tranDate;
    private Time tranTime;
    private String ipAddressTran;
    private String locationTran;
    private String tranFrom;
    private String accountNumberAgen;
    private String status;
    private Date tranAgenDate;
    private Time tranAgenTime;
    private String locationAgenTran;

    public String getTran_number() {
        return tranNumber;
    }

    public void setTran_number(String tran_number) {
        this.tranNumber = tran_number;
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

    public String getTranFrom() {
        return tranFrom;
    }

    public void setTranFrom(String tranFrom) {
        this.tranFrom = tranFrom;
    }

    public String getAccountNumberAgen() {
        return accountNumberAgen;
    }

    public void setAccountNumberAgen(String account_number_agen) {
        this.accountNumberAgen = account_number_agen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTranAgenDate() {
        return tranAgenDate;
    }

    public void setTranAgenDate(Date tranAgenDate) {
        this.tranAgenDate = tranAgenDate;
    }

    public Time getTranAgenTime() {
        return tranAgenTime;
    }

    public void setTranAgenTime(Time tranAgenTime) {
        this.tranAgenTime = tranAgenTime;
    }

    public String getLocationAgenTran() {
        return locationAgenTran;
    }

    public void setLocationAgenTran(String locationAgenTran) {
        this.locationAgenTran = locationAgenTran;
    }

   

}
