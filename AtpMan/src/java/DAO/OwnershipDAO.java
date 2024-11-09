/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import model.*;

/**
 *
 * @author ADMIN
 */
public class OwnershipDAO {

    public List<Ownership> getAllOwner() {
        Connection connection = null;
        List<Ownership> list = new ArrayList<>();
        String sql = "select * from Ownership";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Ownership ownership = new Ownership();
                ownership.setOwnershipID(rs.getInt(1));
                ownership.setApartmentID(rs.getInt(2));
                ownership.setCustomerID(rs.getInt(3));
                ownership.setContractDate(rs.getDate(4));
                list.add(ownership);
            }
        } catch (SQLException | ClassNotFoundException e) {

        }
        return list;
    }
    
    public void insertResident(int customerID, int apartmentID) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            String sql = "INSERT INTO Ownership (apartmentID, customerID, [contractDate]) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, apartmentID);
                ps.setInt(2, customerID);
                ps.setDate(3, Date.valueOf(LocalDate.now()));
                ps.executeUpdate();
                System.out.println("Inserted into Living: customerID = " + customerID + ", apartmentID = " + apartmentID); // In thông báo thành công
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // In ra lỗi
        } finally {
            DBContext.closeConnection(conn);
        }
    }
    
    public List<String> getAllResidentApartmentOwner(int customerId) {
        Connection connection = null;
        List<String> aptList = new ArrayList<>();
        String sql = """
                     select os.*, a.apartmentNumber, b.name from Ownership os
                     inner join Customer c on c.customerID = os.customerID
                     inner join Apartment a on a.apartmentID = os.apartmentID
                     inner join Building b on b.buildingID = a.buildingID
                     where c.customerID = ?
                     order by endDate desc
                     """;
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            while(rs.next()){
                String apt = rs.getString("apartmentNumber") + "-" + rs.getString("name") + ", thời gian: " 
                        + (rs.getDate("contractDate") != null ? dateFormat.format(rs.getDate("contractDate")) : "") + "-"
                        + (rs.getDate("endDate") != null ? dateFormat.format(rs.getDate("endDate")) : "nay");
                aptList.add(apt);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return aptList;
    }
    
    public static void main(String[] args) {
        OwnershipDAO dao = new OwnershipDAO();
        List<Ownership> list = dao.getAllOwner();
        
        List<String> slist = dao.getAllResidentApartmentOwner(1);
        System.out.println(slist);
    }
}
