/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;


/**
 *
 * @author Admin
 */
public class Apartment {

    private int apartmentID;
    private int buildingID;
    private String apartmentNumber;
    private String apartmentType;
    private BigDecimal price;
    private BigDecimal maintenanceFee;
    private int floor;
    private int area;


    public Apartment() {
    }

    public Apartment(int apartmentID, int buildingID, String apartmentNumber, String apartmentType, BigDecimal price, BigDecimal maintenanceFee, int floor, int area) {
        this.apartmentID = apartmentID;
        this.buildingID = buildingID;
        this.apartmentNumber = apartmentNumber;
        this.apartmentType = apartmentType;
        this.price = price;
        this.maintenanceFee = maintenanceFee;
        this.floor = floor;
        this.area = area;
    }

    public int getApartmentID() {
        return apartmentID;
    }

    public void setApartmentID(int apartmentID) {
        this.apartmentID = apartmentID;
    }

    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getApartmentType() {
        return apartmentType;
    }

    public void setApartmentType(String apartmentType) {
        this.apartmentType = apartmentType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(BigDecimal maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Apartment{" + "apartmentID=" + apartmentID + ", buildingID=" + buildingID + ", apartmentNumber=" + apartmentNumber + ", apartmentType=" + apartmentType + ", price=" + price + ", maintenanceFee=" + maintenanceFee + ", floor=" + floor + ", area=" + area + '}';
    }
}
