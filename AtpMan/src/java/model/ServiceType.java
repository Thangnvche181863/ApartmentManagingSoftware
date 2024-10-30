/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thang
 */
public class ServiceType {
    private String type;
    private int totalService;
    private double totalFee;

    public ServiceType() {
    }

    public ServiceType(String type, int totalService, double totalFee) {
        this.type = type;
        this.totalService = totalService;
        this.totalFee = totalFee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalService() {
        return totalService;
    }

    public void setTotalService(int totalService) {
        this.totalService = totalService;
    }

    public double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    @Override
    public String toString() {
        return "ServiceType{" + "type=" + type + ", totalService=" + totalService + ", totalFee=" + totalFee + '}';
    }
    
    
}
