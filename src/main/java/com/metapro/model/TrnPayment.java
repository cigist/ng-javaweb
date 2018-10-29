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
public class TrnPayment implements Serializable{
    private String trnNumber;
    private String accountNumberFrom;
    private String accountNumberTo;
    private BigDecimal beginingBalance;
    private String paymentType;
    private String trnType;
    private BigDecimal amount;
    private BigDecimal transfee;
    private BigDecimal endingBalance;
    private String userCreate;
    private Date tranDate;
    private Time tranTime;
    private String locationtTran;
    private String ipAddressTran;

    public String getTrnNumber() {
        return trnNumber;
    }

    public void setTrnNumber(String trnNumber) {
        this.trnNumber = trnNumber;
    }

    public String getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public void setAccountNumberFrom(String accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
    }

    public String getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(String accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
    }

    public BigDecimal getBeginingBalance() {
        return beginingBalance;
    }

    public void setBeginingBalance(BigDecimal beginingBalance) {
        this.beginingBalance = beginingBalance;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
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

    public BigDecimal getTransfee() {
        return transfee;
    }

    public void setTransfee(BigDecimal transfee) {
        this.transfee = transfee;
    }

    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(BigDecimal endingBalance) {
        this.endingBalance = endingBalance;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
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

    public String getLocationtTran() {
        return locationtTran;
    }

    public void setLocationtTran(String locationtTran) {
        this.locationtTran = locationtTran;
    }

    public String getIpAddressTran() {
        return ipAddressTran;
    }

    public void setIpAddressTran(String ipAddressTran) {
        this.ipAddressTran = ipAddressTran;
    }

   
}
