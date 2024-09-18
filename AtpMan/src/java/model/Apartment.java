/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author thang
 */
public class Apartment {
    private int apartmentId;
    private int buildingId;
    private String departmentType;
    private double price;
    private int floor;
    
    private List<Invoice> invoices;

    public Apartment() {
    }

    public Apartment(int apartmentId, int buildingId, String departmentType, double price, int floor) {
        this.apartmentId = apartmentId;
        this.buildingId = buildingId;
        this.departmentType = departmentType;
        this.price = price;
        this.floor = floor;
    }
    

    public Apartment(int apartmentId, int buildingId, String departmentType, double price, int floor, List<Invoice> invoices) {
        this.apartmentId = apartmentId;
        this.buildingId = buildingId;
        this.departmentType = departmentType;
        this.price = price;
        this.floor = floor;
        this.invoices = invoices;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "Apartment{" + "apartmentId=" + apartmentId + ", buildingId=" + buildingId + ", departmentType=" + departmentType + ", price=" + price + ", floor=" + floor + ", invoices=" + invoices + '}';
    }
    
    
}
