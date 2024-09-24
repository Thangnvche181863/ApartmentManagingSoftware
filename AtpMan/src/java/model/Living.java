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
public class Living {
    private int livingID;
    private int customerID;
    private int apartmentID;
    private Date startDate, endDate;

    public Living() {
    }

    public Living(int livingID, int customerID, int apartmentID, Date startDate, Date endDate) {
        this.livingID = livingID;
        this.customerID = customerID;
        this.apartmentID = apartmentID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getLivingID() {
        return livingID;
    }

    public void setLivingID(int livingID) {
        this.livingID = livingID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
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

    @Override
    public String toString() {
        return "Living{" + "livingID=" + livingID + ", customerID=" + customerID + ", apartmentID=" + apartmentID + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
}
