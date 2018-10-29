/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metapro.model;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 *
 * @author irwan cigist /irwancigist@gmail.com
 */
public class MstPaymentType extends MstUtils implements Serializable{
    private String paymentCode;
    private String paymnetDesc;
    private BigDecimal fee;

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymnetDesc() {
        return paymnetDesc;
    }

    public void setPaymnetDesc(String paymnetDesc) {
        this.paymnetDesc = paymnetDesc;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

  

    
}
