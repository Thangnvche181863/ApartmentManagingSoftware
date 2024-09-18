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
    private String financeType;
    private double amount;
    private double incidentalCharges;
    private double serviceFee;
    private String providerName;

    public Finance() {
    }

    public Finance(String financeType, double amount, double incidentalCharges, double serviceFee, String providerName) {
        this.financeType = financeType;
        this.amount = amount;
        this.incidentalCharges = incidentalCharges;
        this.serviceFee = serviceFee;
        this.providerName = providerName;
    }

    
    public Finance(int financeId, String financeType, double amount, double incidentalCharges, double serviceFee, String providerName) {
        this.financeId = financeId;
        this.financeType = financeType;
        this.amount = amount;
        this.incidentalCharges = incidentalCharges;
        this.serviceFee = serviceFee;
        this.providerName = providerName;
    }

    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
    }

    public String getFinanceType() {
        return financeType;
    }

    public void setFinanceType(String financeType) {
        this.financeType = financeType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getIncidentalCharges() {
        return incidentalCharges;
    }

    public void setIncidentalCharges(double incidentalCharges) {
        this.incidentalCharges = incidentalCharges;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    @Override
    public String toString() {
        return "Finance{" + "financeId=" + financeId + ", financeType=" + financeType + ", amount=" + amount + ", incidentalCharges=" + incidentalCharges + ", serviceFee=" + serviceFee + ", providerName=" + providerName + '}';
    }
    
    
}
