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
 * @author Irwan Cigist <cigist.developer@gmail.com>
 */
public class MstPpob implements Serializable {

    private String vendorCode;
    private String ppobCode;
    private String ppobName;
    private BigDecimal vendorPrice;
    private BigDecimal price;
    private float discount;
    private BigDecimal vendorFee;
    private String ppobType;
    private String flagActive;
    private String userCreate;
    private Date dateCreate;
    private Time timeCreate;
    private String userUpdate;
    private Date dateUpdate;
    private Time timeUpdate;
    private String response;

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getPpobCode() {
        return ppobCode;
    }

    public void setPpobCode(String ppobCode) {
        this.ppobCode = ppobCode;
    }

    public String getPpobName() {
        return ppobName;
    }

    public void setPpobName(String ppobName) {
        this.ppobName = ppobName;
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

    public BigDecimal getVendorFee() {
        return vendorFee;
    }

    public void setVendorFee(BigDecimal vendorFee) {
        this.vendorFee = vendorFee;
    }

    public String getPpobType() {
        return ppobType;
    }

    public void setPpobType(String ppobType) {
        this.ppobType = ppobType;
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

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

   
}
