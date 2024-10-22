/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author WuanTun
 */
public class Staff {

    private int staffID;
    private int roleID;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    
    private Date hireDate;
    private String staffImg;
    public Staff() {
    }

    public Staff(int staffID, int roleID, String username, String password, String name, String email, String phoneNumber, Date hireDate) {
        this.staffID = staffID;
        this.roleID = roleID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
    }

    public Staff(int staffID, int roleID, String username, String password, String name, String email, String phoneNumber, Date hireDate, String staffImg) {
        this.staffID = staffID;
        this.roleID = roleID;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hireDate = hireDate;
        this.staffImg = staffImg;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getStaffImg() {
        return staffImg;
    }

    public void setStaffImg(String staffImg) {
        this.staffImg = staffImg;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Staff{" + "staffID=" + staffID + ", roleID=" + roleID + ", username=" + username + ", password=" + password + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", staffImg=" + staffImg + '}';
    }
   

    
}
