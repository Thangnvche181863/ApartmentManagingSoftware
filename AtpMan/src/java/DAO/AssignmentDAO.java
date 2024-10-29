/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import utils.DBContext;

/**
 *
 * @author Admin
 */
public class AssignmentDAO {

    public int creatAssignment(int staffID, int taskID, Date startTime, Date endTime) {
        Connection conn = null;

        int n = 0;
        String sql = "INSERT INTO Assigment (staffID, taskID, startTime, endTime) VALUES (?, ?, ?, ?)";

        try {
            // Thiết lập kết nối
            conn = DBContext.getConnection();
            PreparedStatement pre = null;
            // Chuẩn bị câu lệnh SQL
            pre = conn.prepareStatement(sql);

            // Gán các giá trị cho các tham số
            pre.setInt(1, staffID);
            pre.setInt(2, taskID);
            pre.setDate(3, startTime);
            pre.setDate(4, endTime);

            // Thực hiện câu lệnh và lấy số hàng ảnh hưởng
            n = pre.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return n;
    }
}
