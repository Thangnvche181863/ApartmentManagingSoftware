/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import utils.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.*;

/**
 *
 * @author ADMIN
 */
public class RequestComplaintDAO {
    Connection connection = null;
    /**
     * 
     * @return
     */
    public List<RequestComplaint> getAllRequest(){
        List<RequestComplaint> list = new ArrayList<>();
        String sql = "select * from RequestComplaint";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                RequestComplaint requestComplaint = new RequestComplaint();
                requestComplaint.setRequestID(rs.getInt(1));
                requestComplaint.setCustomerID(rs.getInt(2));
                requestComplaint.setTitle(rs.getString(3));
                requestComplaint.setDescription(rs.getString(4));
                requestComplaint.setStatus(rs.getInt(5));
                requestComplaint.setDateResquested(rs.getDate(6));
                requestComplaint.setType(rs.getString(7));
                list.add(requestComplaint);
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
        return list;
    }
    
    public static void main(String[] args) {
        RequestComplaintDAO dao = new RequestComplaintDAO();
        List<RequestComplaint> list = dao.getAllRequest();
        System.out.println(list.size());
    }
}
