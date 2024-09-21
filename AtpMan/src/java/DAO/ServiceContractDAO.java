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
public class ServiceContractDAO {
    Connection connection = null;
    
    public List<ServiceContract> getAllServiceByAparmentID(int apartmentID){
        List<ServiceContract> list = new ArrayList<>();
        String sql = "select * from ServiceContract sc, Service s where sc.serviceID = s.serviceID and apartmentID = ?";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                ServiceContract serviceContract = new ServiceContract();
                Service service = new Service();
                
                service.setServiceID(rs.getInt("serviceID"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setDescription(rs.getString("description"));
                service.setFee(rs.getDouble("fee"));
                
                serviceContract.setServiceContractID(rs.getInt("serviceContractID"));
                serviceContract.setApartmentID(rs.getInt("apartmentID"));
                serviceContract.setService(service);
                serviceContract.setStartDate(rs.getDate("startDate"));
                serviceContract.setEndDate(rs.getDate("endDate"));
                serviceContract.setAmount(rs.getDouble("amount"));
                
                list.add(serviceContract);
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
        return list;
    }
    
    public static void main(String[] args) {
        ServiceContractDAO dao = new ServiceContractDAO();
        List<ServiceContract> list = dao.getAllServiceByAparmentID(1);
        System.out.println(list.get(0).getServiceContractID());
    }
    
}
