/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.ApartmentDAO;
import DAO.BuildingDAO;
import DAO.CustomerDAO;
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
    private static WebManager instance;
     private WebManager() throws SQLException, ClassNotFoundException {
        
        customerDAO = new CustomerDAO();
        staffDAO = new StaffDAO();
        apartmentDAO = new ApartmentDAO();
        buildingDAO = new BuildingDAO();
    }

    public static WebManager getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new WebManager();
        }

        return instance;
    }
}
