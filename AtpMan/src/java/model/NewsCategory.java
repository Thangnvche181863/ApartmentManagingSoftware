/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class NewsCategory {

    private int newsCategoryID;
    private String name;
    private String description;

    public NewsCategory() {
    }

    public NewsCategory(int newsCategoryID, String name, String description) {
        this.newsCategoryID = newsCategoryID;
        this.name = name;
        this.description = description;
    }

    public int getNewsCategoryID() {
        return newsCategoryID;
    }

    public void setNewsCategoryID(int newsCategoryID) {
        this.newsCategoryID = newsCategoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
