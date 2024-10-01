/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author PC
 */
public class News {

    private int newsID;
    private int staffID;
    private int taskID;
    private int newsCategoryID;
    private String newsTitle;
    private String newsContent;
    private Date postDate;
    private String newsImg;
    private String newsCategoryName;
    private String staffName;

    public News() {
    }

    public News(int newsID, int staffID, int taskID, int newsCategoryID, String newsTitle, String newsContent, Date postDate, String newsImg) {
        this.newsID = newsID;
        this.staffID = staffID;
        this.taskID = taskID;
        this.newsCategoryID = newsCategoryID;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.postDate = postDate;
        this.newsImg = newsImg;
    }

    public News(int newsID, String newsTitle, String newsContent, Date postDate, String newsImg, String newsCategoryName) {
        this.newsID = newsID;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.postDate = postDate;
        this.newsImg = newsImg;
        this.newsCategoryName = newsCategoryName;
    }

    public News(int staffID, int taskID, int newsCategoryID, String newsTitle, String newsContent, Date postDate, String newsImg, String newsCategoryName, String staffName) {
        this.staffID = staffID;
        this.taskID = taskID;
        this.newsCategoryID = newsCategoryID;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.postDate = postDate;
        this.newsImg = newsImg;
        this.newsCategoryName = newsCategoryName;
        this.staffName = staffName;
    }
    
    

    public News(int newsID, int staffID, int taskID, int newsCategoryID, String newsTitle, String newsContent, Date postDate, String newsImg, String newsCategoryName) {
        this.newsID = newsID;
        this.staffID = staffID;
        this.taskID = taskID;
        this.newsCategoryID = newsCategoryID;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.postDate = postDate;
        this.newsImg = newsImg;
        this.newsCategoryName = newsCategoryName;
    }

    public News(int newsID, int staffID, int taskID, int newsCategoryID, String newsTitle, String newsContent, Date postDate, String newsImg, String newsCategoryName, String staffName) {
        this.newsID = newsID;
        this.staffID = staffID;
        this.taskID = taskID;
        this.newsCategoryID = newsCategoryID;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.postDate = postDate;
        this.newsImg = newsImg;
        this.newsCategoryName = newsCategoryName;
        this.staffName = staffName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getNewsCategoryName() {
        return newsCategoryName;
    }

    public void setNewsCategoryName(String newsCategoryName) {
        this.newsCategoryName = newsCategoryName;
    }

    public String getNewsImg() {
        return newsImg;
    }

    public void setNewsImg(String newsImg) {
        this.newsImg = newsImg;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getNewsCategoryID() {
        return newsCategoryID;
    }

    public void setNewsCategoryID(int newsCategoryID) {
        this.newsCategoryID = newsCategoryID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

}