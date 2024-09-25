/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.ServiceContract;
/**
 *
 * @author thang
 */
public class ServiceContractDAO extends DBContext{
    public List<ServiceContract> getAll(){
        List<ServiceContract> list = new ArrayList<>();
        
        try {
            String sql = "Select * from ServiceContract";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new ServiceContract(rs.getInt("serviceContractID"),rs.getInt("apartmentID"), rs.getInt("serviceID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getDouble("amount")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void insertServiceContract(int apartmentId, int serviceId, Date startDate, Date endDate, double amount){
        try {
            String sql = "Insert into ServiceContract values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            ps.setInt(2, serviceId);
            ps.setDate(3, startDate);
            ps.setDate(4, endDate);
            ps.setDouble(5, amount);
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteServiceContract(int apartmentId, int serviceId){
        try {
            String sql = "Delete * from ServiceContract where apartmentId = ? AND serviceId = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, apartmentId);
            ps.setInt(2, serviceId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
       ServiceContractDAO sdao = new ServiceContractDAO();
//       sdao.insertServiceContract(7, 1, Date.valueOf("2004-07-08"), Date.valueOf("2004-03-12"), 1);
        System.out.println(sdao.getAll());
    }
}
