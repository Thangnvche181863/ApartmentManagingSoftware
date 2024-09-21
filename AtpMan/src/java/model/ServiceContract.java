/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
/**
 *
 * @author thang
 */
public class ServiceContract {
    private int serviceContractId;
    private int apartmentId;
    private int serviceId;
    private Date startDate;
    private Date endDate;
    private double amount;

    public ServiceContract() {
    }

    public ServiceContract(int serviceContractId, int apartmentId, int serviceId, Date startDate, Date endDate, double amount) {
        this.serviceContractId = serviceContractId;
        this.apartmentId = apartmentId;
        this.serviceId = serviceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public int getServiceContractId() {
        return serviceContractId;
    }

    public void setServiceContractId(int serviceContractId) {
        this.serviceContractId = serviceContractId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ServiceContract{" + "serviceContractId=" + serviceContractId + ", apartmentId=" + apartmentId + ", serviceId=" + serviceId + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + '}';
    }

    
    
    
}
