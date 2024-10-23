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
public class Ownership {
    private int ownershipID, apartmentID, customerID;
    private Date contractDate;

    public Ownership() {
    }

    public Ownership(int ownershipID, int apartmentID, int customerID, Date contractDate) {
        this.ownershipID = ownershipID;
        this.apartmentID = apartmentID;
        this.customerID = customerID;
        this.contractDate = contractDate;
    }

    public int getOwnershipID() {
        return ownershipID;
    }

    public void setOwnershipID(int ownershipID) {
        this.ownershipID = ownershipID;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    @Override
    public String toString() {
        return "Ownership{" + "ownershipID=" + ownershipID + ", apartmentID=" + apartmentID + ", customerID=" + customerID + ", contractDate=" + contractDate + '}';
    }
    
    
}
