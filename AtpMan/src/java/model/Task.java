/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Task {

    private int taskID;
    private String taskName;
    private String description;
    private String taskType;

    public Task(int taskID, String taskName, String description, String taskType) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.description = description;
        this.taskType = taskType;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return "Task{" + "taskID=" + taskID + ", taskName=" + taskName + ", description=" + description + ", taskType=" + taskType + '}';
    }
    
}
