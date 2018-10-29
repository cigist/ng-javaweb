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
public class MstLogin implements Serializable {

    private String userCode;
    private String username;
    private String password;
    private String flagActive;
    private String userCreate;
    private Date dateCreate;
    private Time timeCreate;
    private Date dateUpdate;
    private Time timeUpdate;
    private String location;
    private String ipAddress;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
