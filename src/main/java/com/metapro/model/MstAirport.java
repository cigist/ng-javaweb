/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.model;

import java.io.Serializable;

/**
 *
 * @author cigist
 */
public class MstAirport extends MstUser implements Serializable{
    private String airportCode;
    private String airportName;
    private int domestic;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public int getDomestic() {
        return domestic;
    }

    public void setDomestic(int domestic) {
        this.domestic = domestic;
    }
    
}
