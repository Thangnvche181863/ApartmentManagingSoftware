/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thang
 */
public class FinanceBuilding {
    private int buildingId;
    private int financeId;

    public FinanceBuilding() {
    }

    public FinanceBuilding(int buildingId, int financeId) {
        this.buildingId = buildingId;
        this.financeId = financeId;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getFinanceId() {
        return financeId;
    }

    public void setFinanceId(int financeId) {
        this.financeId = financeId;
    }

    @Override
    public String toString() {
        return "FinanceBuilding{" + "buildingId=" + buildingId + ", financeId=" + financeId + '}';
    }
    
    
}
