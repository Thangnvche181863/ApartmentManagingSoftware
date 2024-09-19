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
public class Building {
    private int buildingId;
    private String name;
    private int numFloor;
    private int numApartment;
    private String address;
    
    private List<Apartment> apartments;

    public Building() {
    }

    public Building(int buildingId, String name, int numFloor, int numApartment, String address) {
        this.buildingId = buildingId;
        this.name = name;
        this.numFloor = numFloor;
        this.numApartment = numApartment;
        this.address = address;
    }

    
    public Building(int buildingId, String name, int numFloor, int numApartment, String address, List<Apartment> apartments) {
        this.buildingId = buildingId;
        this.name = name;
        this.numFloor = numFloor;
        this.numApartment = numApartment;
        this.address = address;
        this.apartments = apartments;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
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

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    @Override
    public String toString() {
        return "Building{" + "buildingId=" + buildingId + ", name=" + name + ", numFloor=" + numFloor + ", numApartment=" + numApartment + ", address=" + address + ", apartments=" + apartments + '}';
    }
    
    
}
