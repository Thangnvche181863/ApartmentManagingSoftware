/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import model.Apartment;

/**
 *
 * @author Admin
 */
public class Building {

    private int buildingID;
    private String name;
    private int numFloor;
    private int numApartment;
    private String address;
    private List<Apartment> apartmentList;

    public Building() {
    }

    public Building(int buildingID, String name, int numFloor, int numApartment, String address) {
        this.buildingID = buildingID;

        this.name = name;
        this.numFloor = numFloor;
        this.numApartment = numApartment;
        this.address = address;
    }


    public int getBuildingID() {
        return buildingID;
    }

    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumFloor() {
        return numFloor;
    }

    public void setNumFloor(int numFloor) {
        this.numFloor = numFloor;
    }

    public int getNumApartment() {
        return numApartment;
    }

    public void setNumApartment(int numApartment) {
        this.numApartment = numApartment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Apartment> getApartmentList() {
        return apartmentList;
    }

    public void setApartmentList(List<Apartment> apartmentList) {
        this.apartmentList = apartmentList;
    }

    @Override
    public String toString() {
        return "Building{" + "buildingID=" + buildingID + ", name=" + name + ", numFloor=" + numFloor + ", numApartment=" + numApartment + ", address=" + address + '}';
    }
    
}
