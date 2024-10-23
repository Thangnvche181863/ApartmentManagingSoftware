/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ApartmentDAO;
import DAO.BuildingDAO;
import DAO.CustomerDAO;
import DAO.FinanceDAO;
import DAO.FinanceTypeDAO;
import DAO.InvoiceDAO;
import DAO.InvoiceServiceDAO;
import DAO.LivingDAO;
import DAO.OwnershipDAO;
import DAO.RequestComplaintDAO;
import DAO.ServiceContractDAO;
import DAO.ServiceDAO;
import DAO.StaffDAO;


import java.sql.SQLException;

/**
 *
 * @author WuanTun
 */
public class WebManager {
    public CustomerDAO customerDAO;
    public StaffDAO staffDAO;
    public ApartmentDAO apartmentDAO;
    public BuildingDAO buildingDAO;
    public LivingDAO livingDAO;
    public ServiceDAO serviceDAO;
    public ServiceContractDAO serviceContractDAO;
    public InvoiceDAO invoiceDAO;
    public InvoiceServiceDAO invoiceServiceDAO;
    public FinanceDAO financeDAO;
    public FinanceTypeDAO financeTypeDAO;
    public OwnershipDAO ownershipDAO;
    public RequestComplaintDAO requestComplaintDAO;
    
    private static WebManager instance;
     private WebManager() throws SQLException, ClassNotFoundException {
        
        customerDAO = new CustomerDAO();
        staffDAO = new StaffDAO();
        apartmentDAO = new ApartmentDAO();
        buildingDAO = new BuildingDAO();
        livingDAO = new LivingDAO();
        serviceDAO = new ServiceDAO();
        serviceContractDAO = new ServiceContractDAO();
        invoiceDAO = new InvoiceDAO();
        invoiceServiceDAO = new InvoiceServiceDAO();
        financeDAO = new FinanceDAO();
        financeTypeDAO = new FinanceTypeDAO();
        ownershipDAO = new OwnershipDAO();
        requestComplaintDAO = new RequestComplaintDAO();
    }

    public static WebManager getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new WebManager();
        }

        return instance;
    }
}
