/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author thang
 */
public class Finance {
    private int financeId;
    private int buildingId;
    private int financeTypeId;
    private double amount;
    private int month;
    

    public Finance() {
    }

    public Finance(int financeId, int buildingId, int financeTypeId, double amount, int month) {
        this.financeId = financeId;
        this.buildingId = buildingId;
        this.financeTypeId = financeTypeId;
        this.amount = amount;
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    

    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getFinanceTypeId() {
        return financeTypeId;
    }

    public void setFinanceTypeId(int financeTypeId) {
        this.financeTypeId = financeTypeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Finance{" + "financeId=" + financeId + ", buildingId=" + buildingId + ", financeTypeId=" + financeTypeId + ", amount=" + amount + ", month=" + month + '}';
    }

   
    
}
