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
public class MstDaftarNasabah extends MstUtils implements Serializable {

    private String accountNumber;
    private String accountNumberTo;
    private String nasabahName;
    private String bankCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(String accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
    }

    public String getNasabahName() {
        return nasabahName;
    }

    public void setNasabahName(String nasabahName) {
        this.nasabahName = nasabahName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

};
