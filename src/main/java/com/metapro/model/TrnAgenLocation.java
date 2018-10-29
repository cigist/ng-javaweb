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
 * @author cigist
 */
public class TrnAgenLocation implements Serializable{
    private String agenAccountNumber;
    private String lat;
    private String lng;
    private String imei;
    private Date locationDate;
    private Time locationTime;

    public String getAgenAccountNumber() {
        return agenAccountNumber;
    }

    public void setAgenAccountNumber(String agenAccountNumber) {
        this.agenAccountNumber = agenAccountNumber;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getLocationDate() {
        return locationDate;
    }

    public void setLocationDate(Date locationDate) {
        this.locationDate = locationDate;
    }

    public Time getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(Time locationTime) {
        this.locationTime = locationTime;
    }

  
   
}
