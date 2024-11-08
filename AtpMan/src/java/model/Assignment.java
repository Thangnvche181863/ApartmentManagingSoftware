/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Assignment {
    private int staffID;
    private int taskID;
    private Date startTime;
    private Date endTime;
    private String status;
    private String staffName;
    private String taskName;

    public Assignment() {
    }

    public Assignment(String staffName, String taskName, Date startTime, Date endTime, String status) {
      
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.staffName = staffName;
        this.taskName = taskName;
    }

    public Assignment(int staffID, int taskID, Date startTime, Date endTime, String status) {
        this.staffID = staffID;
        this.taskID = taskID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "Assignment{" + "staffID=" + staffID + ", taskID=" + taskID + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + ", staffName=" + staffName + ", taskName=" + taskName + '}';
    }

    
    

   
    
}
