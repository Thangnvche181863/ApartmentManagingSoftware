/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author PC
 */
public class NewsComment {

    private int commentID;
    private int newsID;
    private Integer customerID;
    private Integer staffID;
    private String commentText;
    private Timestamp commentDate;
    private String customerName;
    private String staffName;

    public NewsComment() {
    }

    public NewsComment(int commentID, int newsID, Integer customerID, Integer staffID, String commentText, Timestamp commentDate) {
        this.commentID = commentID;
        this.newsID = newsID;
        this.customerID = customerID;
        this.staffID = staffID;
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public NewsComment(int commentID, int newsID, Integer customerID, Integer staffID, String commentText, Timestamp commentDate, String customerName, String staffName) {
        this.commentID = commentID;
        this.newsID = newsID;
        this.customerID = customerID;
        this.staffID = staffID;
        this.commentText = commentText;
        this.commentDate = commentDate;
        this.customerName = customerName;
        this.staffName = staffName;
    }
    
    

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

}
