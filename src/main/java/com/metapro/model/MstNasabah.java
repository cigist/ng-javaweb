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
public class MstNasabah extends MstCustomer implements Serializable{

    private String accountNumber;
    private String pin;
    private String accountName;
    private String accountType;
    private BigDecimal biginingBalance;
    private BigDecimal endingBalance;
    private String flagActive;
    private String userCreate;
    private Date dateCreate;
    private Time timeCreate;
    private String ipAddressCreate;
    private String locationCreate;
    private String userUpdate;
    private Date dateUpdate;
    private Time timeUpdate;
    private String locationUpdate;
    private String ipAddressUpdate;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBiginingBalance() {
        return biginingBalance;
    }

    public void setBiginingBalance(BigDecimal biginingBalance) {
        this.biginingBalance = biginingBalance;
    }

    public BigDecimal getEndingBalance() {
        return endingBalance;
    }

    public void setEndingBalance(BigDecimal endingBalance) {
        this.endingBalance = endingBalance;
    }

    public String getFlagActive() {
        return flagActive;
    }

    public void setFlagActive(String flagActive) {
        this.flagActive = flagActive;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Time getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Time timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getIpAddressCreate() {
        return ipAddressCreate;
    }

    public void setIpAddressCreate(String ipAddressCreate) {
        this.ipAddressCreate = ipAddressCreate;
    }

    public String getLocationCreate() {
        return locationCreate;
    }

    public void setLocationCreate(String locationCreate) {
        this.locationCreate = locationCreate;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Time getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Time timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    public String getLocationUpdate() {
        return locationUpdate;
    }

    public void setLocationUpdate(String locationUpdate) {
        this.locationUpdate = locationUpdate;
    }

    public String getIpAddressUpdate() {
        return ipAddressUpdate;
    }

    public void setIpAddressUpdate(String ipAddressUpdate) {
        this.ipAddressUpdate = ipAddressUpdate;
    }

  
}
