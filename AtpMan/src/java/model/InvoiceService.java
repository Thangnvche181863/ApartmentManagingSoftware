/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class InvoiceService {
    private Invoice invoice;
    private ServiceContract serviceContract;

    public InvoiceService() {
    }

    public InvoiceService(Invoice invoice, ServiceContract serviceContract) {
        this.invoice = invoice;
        this.serviceContract = serviceContract;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public ServiceContract getServiceContract() {
        return serviceContract;
    }

    public void setServiceContract(ServiceContract serviceContract) {
        this.serviceContract = serviceContract;
    }

    @Override
    public String toString() {
        return "InvoiceService{" + "invoice=" + invoice + ", serviceContract=" + serviceContract + '}';
    }
    
}
