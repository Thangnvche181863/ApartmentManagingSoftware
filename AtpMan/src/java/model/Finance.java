/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author thang
 */
public class Finance {
    private int financeId;
    private int buildingId;
    private String building;
    private String name;
    private int financeTypeId;
    private BigDecimal amount;
    private Date date;
    private String description;
    

    public Finance() {
    }

    public Finance(int financeId, int buildingId, String building, String name, int financeTypeId, BigDecimal amount, Date date, String description) {
        this.financeId = financeId;
        this.buildingId = buildingId;
        this.building = building;
        this.name = name;
        this.financeTypeId = financeTypeId;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

  
    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getFinanceTypeId() {
        return financeTypeId;
    }

    public void setFinanceTypeId(int financeTypeId) {
        this.financeTypeId = financeTypeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Finance{" + "financeId=" + financeId + ", buildingId=" + buildingId + ", building=" + building + ", name=" + name + ", financeTypeId=" + financeTypeId + ", amount=" + amount + ", date=" + date + ", description=" + description + '}';
    }
        
}
