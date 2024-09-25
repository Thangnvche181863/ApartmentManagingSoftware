/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author thang
 */
public class Invoice {
    private int invoiceId;
    private int apartmentId;
    private double amount;
    private Date issueDate;
    private Date dueDate;
    private int status;
    private Date transactionDate;
    private List<ServiceContract> serviceContractList;

    public Invoice() {
    }

    public Invoice(int invoiceID, int apartmentID, double amount, Date issueDate, Date dueDate, int status, Date transactionDate, List<ServiceContract> serviceContractList) {
        this.invoiceID = invoiceID;
        this.apartmentID = apartmentID;
        this.amount = amount;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
        this.transactionDate = transactionDate;
        this.serviceContractList = serviceContractList;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDuDate() {
        return duDate;
    }

    public void setDuDate(Date duDate) {
        this.duDate = duDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public List<ServiceContract> getServiceContractList() {
        return serviceContractList;
    }

    public void setServiceContractList(List<ServiceContract> serviceContractList) {
        this.serviceContractList = serviceContractList;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceID=" + invoiceID + ", apartmentID=" + apartmentID + ", amount=" + amount + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", status=" + status + ", transactionDate=" + transactionDate + ", serviceContractList=" + serviceContractList + '}';

    }
    
}
