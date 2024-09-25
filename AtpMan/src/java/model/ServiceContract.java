/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class ServiceContract {
    private int serviceContractID;
    private int apartmentID;
    private int serviceID;
    private Date startDate;
    private Date endDate;
    private double amount;
    private Service service;

    public ServiceContract() {
    }

    public ServiceContract(int serviceContractID, int apartmentID, int serviceID, Date startDate, Date endDate, double amount, Service service) {
        this.serviceContractID = serviceContractID;
        this.apartmentID = apartmentID;
        this.serviceID = serviceID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.service = service;
    }

    public int getServiceContractID() {
        return serviceContractID;
    }

    public void setServiceContractID(int serviceContractID) {
        this.serviceContractID = serviceContractID;
    }    

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
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
        return "ServiceContract{" + "apartmentID=" + apartmentID + ", service=" + service + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + '}';
    }

    
    
}
