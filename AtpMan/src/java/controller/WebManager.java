/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.CustomerDAO;


import java.sql.SQLException;

/**
 *
 * @author WuanTun
 */
public class WebManager {
    public CustomerDAO customerDAO;
    private static WebManager instance;
     private WebManager() throws SQLException, ClassNotFoundException {
        
        customerDAO = new CustomerDAO();
        
    }

    public static WebManager getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new WebManager();
        }

        return instance;
    }
}