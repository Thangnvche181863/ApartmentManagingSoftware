/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
/**
 *
 * @author thang
 */
public class Invoice {
    private int invoiceId;
    private int apartmentId;
    private double amount;
    private Date issueDate;
    private Date duDate;
    private int status;
    private Date transactionDate;

    public Invoice() {
    }

    public Invoice(int invoiceId, int apartmentId, double amount, Date issueDate, Date duDate, int status, Date transactionDate) {
        this.invoiceId = invoiceId;
        this.apartmentId = apartmentId;
        this.amount = amount;
        this.issueDate = issueDate;
        this.duDate = duDate;
        this.status = status;
        this.transactionDate = transactionDate;
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

    @Override
    public String toString() {
        return "Invoice{" + "invoiceId=" + invoiceId + ", apartmentId=" + apartmentId + ", amount=" + amount + ", issueDate=" + issueDate + ", duDate=" + duDate + ", status=" + status + ", transactionDate=" + transactionDate + '}';
    }
    
    
}
