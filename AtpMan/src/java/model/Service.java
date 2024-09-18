/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thang
 */
public class Service {
    private int serviceId;
    private String name;
    private String type;
    private double fee;

    public Service() {
    }

    public Service(int serviceId, String name, String type, double fee) {
        this.serviceId = serviceId;
        this.name = name;
        this.type = type;
        this.fee = fee;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "Service{" + "serviceId=" + serviceId + ", name=" + name + ", type=" + type + ", fee=" + fee + '}';
    }
    
    
}
