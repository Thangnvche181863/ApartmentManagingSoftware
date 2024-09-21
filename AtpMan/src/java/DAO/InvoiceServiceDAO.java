/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class InvoiceServiceDAO {
    Connection connection = null;
    
    public List<InvoiceService> getInvoiceServicesByAparmentID(int apartmentID){
        return null;
    }
    
    public List<InvoiceService> getInvoiceServicesByAparmentIDandMonth(int apartmentID, int month){
        List<InvoiceService> list = new ArrayList<>();
        return list;
    }
}
