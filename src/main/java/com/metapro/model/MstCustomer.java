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
 * @author irwan cigist /irwancigist@gmail.com
 */
public class MstCustomer implements Serializable {

    private String cusCode;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String activationCode;
    private String flagActive;
    private String userCreate;
    private Date dateCreate;
    private Time timeCreate;
    private String userUpdate;
    private Date dateUpdate;
    private Time timeUpdate;
    private String location;
    private String ipAddress;
    private String imgUrl;
    private String address;

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
