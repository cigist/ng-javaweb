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
public class TrnUserLogin implements Serializable{
    private String trnLoginCode;
    private String username;
    private String sessionID;
    private String token;
    private Date dateLogin;
    private Time timeLogin;
    private String location;
    private String ipAddress;
    private String loginFrom;

  
    public String getTrnLoginCode() {
        return trnLoginCode;
    }

    public void setTrnLoginCode(String trnLoginCode) {
        this.trnLoginCode = trnLoginCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateLogin() {
        return dateLogin;
    }

    public void setDateLogin(Date dateLogin) {
        this.dateLogin = dateLogin;
    }

    public Time getTimeLogin() {
        return timeLogin;
    }

    public void setTimeLogin(Time timeLogin) {
        this.timeLogin = timeLogin;
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
     public String getLoginFrom() {
        return loginFrom;
    }

    public void setLoginFrom(String loginFrom) {
        this.loginFrom = loginFrom;
    }
}
