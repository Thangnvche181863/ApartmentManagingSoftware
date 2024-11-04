/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thang
 */
public class Discount {

    public int serviceId;
    public int oneMonth;
    public int twoMonth;
    public int threeMonth;

    public Discount() {
    }

    public Discount(int serviceId, int oneMonth, int twoMonth, int threeMonth) {
        this.serviceId = serviceId;
        this.oneMonth = oneMonth;
        this.twoMonth = twoMonth;
        this.threeMonth = threeMonth;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getOneMonth() {
        return oneMonth;
    }

    public void setOneMonth(int oneMonth) {
        this.oneMonth = oneMonth;
    }

    public int getTwoMonth() {
        return twoMonth;
    }

    public void setTwoMonth(int twoMonth) {
        this.twoMonth = twoMonth;
    }

    public int getThreeMonth() {
        return threeMonth;
    }

    public void setThreeMonth(int threeMonth) {
        this.threeMonth = threeMonth;
    }

    @Override
    public String toString() {
        return "Discount{" + "serviceId=" + serviceId + ", oneMonth=" + oneMonth + ", twoMonth=" + twoMonth + ", threeMonth=" + threeMonth + '}';
    }

}
