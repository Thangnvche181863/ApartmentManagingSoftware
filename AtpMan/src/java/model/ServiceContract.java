/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.math.BigDecimal;
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
    private BigDecimal amount;
    private Service service;
    private int totalContract;
    private BigDecimal totalAmount;
    
   
    public ServiceContract() {
    }

    public ServiceContract(int apartmentId, int totalContract, BigDecimal totalAmount) {
        this.apartmentId = apartmentId;
        this.totalContract = totalContract;
        this.totalAmount = totalAmount;
    }
    
    

    public ServiceContract(int serviceContractId, int apartmentId, int serviceId, Date startDate, Date endDate, BigDecimal amount, Service service) {
        this.serviceContractId = serviceContractId;
        this.apartmentId = apartmentId;
        this.serviceId = serviceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.service = service;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getTotalContract() {
        return totalContract;
    }

    public void setTotalContract(int totalContract) {
        this.totalContract = totalContract;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "ServiceContract{" + "serviceContractId=" + serviceContractId + ", apartmentId=" + apartmentId + ", serviceId=" + serviceId + ", startDate=" + startDate + ", endDate=" + endDate + ", amount=" + amount + ", service=" + service + ", totalContract=" + totalContract + ", totalAmount=" + totalAmount + '}';
    }
    
    
    
}
