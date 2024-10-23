/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thang
 */
public class InvoiceService {
    private int invoiceId;
    private int serviceContractId;

    public InvoiceService() {
    }

    public InvoiceService(int invoiceId, int serviceContractId) {
        this.invoiceId = invoiceId;
        this.serviceContractId = serviceContractId;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getServiceContractId() {
        return serviceContractId;
    }

    public void setServiceContractId(int serviceContractId) {
        this.serviceContractId = serviceContractId;
    }

    @Override
    public String toString() {
        return "InvoiceService{" + "invoiceId=" + invoiceId + ", serviceContractId=" + serviceContractId + '}';
    }
    
    
}
