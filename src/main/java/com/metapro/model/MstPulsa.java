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
public class MstPulsa implements Serializable{
    private String vendorCode;
    private String pulsaCode;
    private String pulsaName;
    private BigDecimal amount;
    private BigDecimal vendorPrice;
    private BigDecimal price;
    private float discount;
    private String flagActive;
    private String userCreate;
    private Date dateCreate;
    private Time timeCreate;
    private String userUpdate;
    private Date dateUpdate;
    private Time timeUpdate;

    public String getPulsaType() {
        return pulsaType;
    }

    public void setPulsaType(String pulsaType) {
        this.pulsaType = pulsaType;
    }
    private String pulsaType;

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getPulsaCode() {
        return pulsaCode;
    }

    public void setPulsaCode(String pulsaCode) {
        this.pulsaCode = pulsaCode;
    }

    public String getPulsaName() {
        return pulsaName;
    }

    public void setPulsaName(String pulsaName) {
        this.pulsaName = pulsaName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getVendorPrice() {
        return vendorPrice;
    }

    public void setVendorPrice(BigDecimal vendorPrice) {
        this.vendorPrice = vendorPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
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
    
    
}
