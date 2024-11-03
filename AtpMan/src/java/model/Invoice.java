/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
import java.sql.Timestamp;
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
    private Timestamp transactionDate;
    private String invoiceCode;
    private String transactionNo;
    private String bankCode;
    private String orderInfo;
    
    private String apartmentName;
    private List<ServiceContract> serviceContractList;

    public Invoice() {
    }

    public Invoice(int invoiceID, int apartmentID, double amount, Date issueDate, Date dueDate, int status, Timestamp transactionDate, List<ServiceContract> serviceContractList) {
        this.invoiceId = invoiceID;
        this.apartmentId = apartmentID;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date duDate) {
        this.dueDate = duDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public List<ServiceContract> getServiceContractList() {
        return serviceContractList;
    }

    public void setServiceContractList(List<ServiceContract> serviceContractList) {
        this.serviceContractList = serviceContractList;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceId=" + invoiceId + ", apartmentId=" + apartmentId + ", amount=" + amount + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", status=" + status + ", transactionDate=" + transactionDate + ", invoiceCode=" + invoiceCode + ", transactionNo=" + transactionNo + ", bankCode=" + bankCode + ", orderInfo=" + orderInfo + ", serviceContractList=" + serviceContractList + '}';
    }
    
}
