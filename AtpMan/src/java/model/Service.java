/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Service {
    private int serviceID;
    private String name;
    private String type;
    private String description;
    private double fee;

    public Service() {
    }

    public Service(int serviceID, String name, String type, String description, double fee) {
        this.serviceID = serviceID;
        this.name = name;
        this.type = type;
        this.description = description;
        this.fee = fee;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "ServiceContract{" + "serviceID=" + serviceID + ", name=" + name + ", type=" + type + ", fee=" + fee + '}';
    }
    
    
}
