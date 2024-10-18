/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author Admin
 */
public class Apartment {

    private int apartmentID;
    private int buildingID;
    private String apartmentNumber;
    private String departmentType;
    private BigDecimal price;
    private BigDecimal maintenanceFee;
    private int floor;
    private int area;
    private BigDecimal totalAmount;

    private List<Service> services;
    private String name;
	
    
    public Apartment() {
    }

    public Apartment(String departmentType) {
        this.departmentType = departmentType;
    }

    public Apartment(int apartmentID, String apartmentNumber, String departmentType, int floor, BigDecimal totalAmount, String name) {
        this.apartmentID = apartmentID;
        this.apartmentNumber = apartmentNumber;
        this.departmentType = departmentType;
        this.floor = floor;
        this.totalAmount = totalAmount;
        this.name = name;
    }

    
    
    public Apartment(int apartmentID, int buildingID, String apartmentNumber, String departmentType, BigDecimal totalAmount) {
        this.apartmentID = apartmentID;
        this.buildingID = buildingID;
        this.apartmentNumber = apartmentNumber;
        this.departmentType = departmentType;
        this.totalAmount = totalAmount;
    }
    
    

    public Apartment(int apartmentID, int buildingID, String apartmentNumber, String departmentType, BigDecimal price, BigDecimal maintenanceFee, int floor, int area, List<Service> services) {
        this.apartmentID = apartmentID;
        this.buildingID = buildingID;
        this.apartmentNumber = apartmentNumber;
        this.departmentType = departmentType;
        this.price = price;
        this.maintenanceFee = maintenanceFee;
        this.floor = floor;
        this.area = area;
        this.services = services;
    }
    

    public Apartment(int apartmentID, int buildingID, String apartmentNumber, String departmentType, BigDecimal price, BigDecimal maintenanceFee, int floor, int area) {
        this.apartmentID = apartmentID;
        this.buildingID = buildingID;
        this.apartmentNumber = apartmentNumber;
        this.departmentType = departmentType;
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

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 
    
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Apartment{" + "apartmentID=" + apartmentID + ", buildingID=" + buildingID + ", apartmentNumber=" + apartmentNumber + ", departmentType=" + departmentType + ", price=" + price + ", maintenanceFee=" + maintenanceFee + ", floor=" + floor + ", area=" + area + ", totalAmount=" + totalAmount + ", services=" + services + '}';
    }

    
    

}