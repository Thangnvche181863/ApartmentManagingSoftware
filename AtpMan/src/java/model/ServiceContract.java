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
    private int apartmentId;
    private int serviceId;
    private Date startDate;
    private Date endDate;
    private int status;

    public ServiceContract() {
    }

    public ServiceContract(int apartmentId, int serviceId, Date startDate, Date endDate, int status) {
        this.apartmentId = apartmentId;
        this.serviceId = serviceId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServiceContract{" + "apartmentId=" + apartmentId + ", serviceId=" + serviceId + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + '}';
    }
    
    
    
}
