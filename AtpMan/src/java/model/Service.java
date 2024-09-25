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
    private String description;
    private String img;
    private String icon;

    public Service() {
    }

    public Service(int serviceId, String name, String type, double fee, String description, String img, String icon) {
        this.serviceId = serviceId;
        this.name = name;
        this.type = type;
        this.fee = fee;
        this.description = description;
        this.img = img;
        this.icon = icon;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Service{" + "serviceId=" + serviceId + ", name=" + name + ", type=" + type + ", fee=" + fee + ", description=" + description + ", img=" + img + ", icon=" + icon + '}';
    }

    
}
