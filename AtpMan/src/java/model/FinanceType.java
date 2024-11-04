/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thang
 */
public class FinanceType {
    private int financeTypeId;
    private String name;

    public FinanceType() {
    }

    public FinanceType(int financeTypeId, String name) {
        this.financeTypeId = financeTypeId;
        this.name = name;
    }

    public int getFinanceTypeId() {
        return financeTypeId;
    }

    public void setFinanceTypeId(int financeTypeId) {
        this.financeTypeId = financeTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FinanceType{" + "financeTypeId=" + financeTypeId + ", name=" + name + '}';
    }
    
    
}
