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
    private String invoiceCode;
    private String transactionNo;
    private String bankCode;
    private String orderInfo;
    private List<ServiceContract> serviceContractList;

    public Invoice() {
    }

    public Invoice(int invoiceId, int apartmentId, double amount, Date issueDate, Date dueDate, int status, Date transactionDate, String invoiceCode, String transactionNo, String bankCode, String orderInfo, List<ServiceContract> serviceContractList) {
        this.invoiceId = invoiceId;
        this.apartmentId = apartmentId;
        this.amount = amount;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
        this.transactionDate = transactionDate;
        this.invoiceCode = invoiceCode;
        this.transactionNo = transactionNo;
        this.bankCode = bankCode;
        this.orderInfo = orderInfo;
        this.serviceContractList = serviceContractList;
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
        return "Invoice{" + "invoiceID=" + invoiceId + ", apartmentID=" + apartmentId + ", amount=" + amount + ", issueDate=" + issueDate + ", dueDate=" + dueDate + ", status=" + status + ", transactionDate=" + transactionDate + ", serviceContractList=" + serviceContractList + '}';

    }
    
}
