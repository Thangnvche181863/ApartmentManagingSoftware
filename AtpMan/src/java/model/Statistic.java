/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author thang
 */
public class Statistic {
    private int totalBill;
    private BigDecimal totalAmount;
    private int totalUnBill;
    private BigDecimal totalUnPay;

    public Statistic() {
    }

    public Statistic(int totalBill, BigDecimal totalAmount, int totalUnBill, BigDecimal totalUnPay) {
        this.totalBill = totalBill;
        this.totalAmount = totalAmount;
        this.totalUnBill = totalUnBill;
        this.totalUnPay = totalUnPay;
    }

    public int getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(int totalBill) {
        this.totalBill = totalBill;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalUnBill() {
        return totalUnBill;
    }

    public void setTotalUnBill(int totalUnBill) {
        this.totalUnBill = totalUnBill;
    }

    public BigDecimal getTotalUnPay() {
        return totalUnPay;
    }

    public void setTotalUnPay(BigDecimal totalUnPay) {
        this.totalUnPay = totalUnPay;
    }

    @Override
    public String toString() {
        return "Statistic{" + "totalBill=" + totalBill + ", totalAmount=" + totalAmount + ", totalUnBill=" + totalUnBill + ", totalUnPay=" + totalUnPay + '}';
    }
    
    
}
