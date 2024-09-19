/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class RequestComplaint {
    private int requestID, customerID;
    private String title, description;
    private int status;
    private Date dateResquested;
    private String type;

    public RequestComplaint() {
    }

    public RequestComplaint(int requestID, int customerID, String title, String description, int status, Date dateResquested, String type) {
        this.requestID = requestID;
        this.customerID = customerID;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dateResquested = dateResquested;
        this.type = type;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDateResquested() {
        return dateResquested;
    }

    public void setDateResquested(Date dateResquested) {
        this.dateResquested = dateResquested;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RequestComplaint{" + "requestID=" + requestID + ", customerID=" + customerID + ", title=" + title + ", description=" + description + ", status=" + status + ", dateResquested=" + dateResquested + ", type=" + type + '}';
    }
    
    
}
