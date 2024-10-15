/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Admin
 */
public class Apartment {

    private int apartmentID;
    private int buildingID;
    private int apartmentNumber;
    private String departmentType;
    private double price;
    private double maintenanceFee;
    private int floor;
    private int area;


    public Apartment() {
    }

    public Apartment(int apartmentID, int buildingID, int apartmentNumber, String departmentType, double price, double maintenanceFee, int floor, int area) {
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

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
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

    public double getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(double maintenanceFee) {
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
        return "Apartment{" + "apartmentID=" + apartmentID + ", buildingID=" + buildingID + ", apartmentNumber=" + apartmentNumber + ", departmentType=" + departmentType + ", price=" + price + ", maintenanceFee=" + maintenanceFee + ", floor=" + floor + ", area=" + area + '}';
    }
    

}
