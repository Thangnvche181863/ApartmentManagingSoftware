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
public class ServiceDAO {
    Connection connection = null;
    
    public List<Service> getAllService(){
        List<Service> list = new ArrayList<>();
        String sql = "select * from Service";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Service service = new Service();
                service.setServiceID(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setType(rs.getString(3));
                service.setDescription(rs.getString(4));
                service.setFee(rs.getDouble(5));
                list.add(service);
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
        return list;
    }
    
    public static void main(String[] args) {
        ServiceDAO dao = new ServiceDAO();
        List<Service> list = dao.getAllService();
        System.out.println(list.size());
    }
}
