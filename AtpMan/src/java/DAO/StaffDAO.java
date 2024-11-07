/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Staff;
import utils.DBContext;
import utils.UtilHashPass;

/**
 *
 * @author WuanTun
 */
public class StaffDAO {

    private static final Logger LOGGER = Logger.getLogger(StaffDAO.class.getName());

    public boolean checkUsername(String username) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Staff WHERE username = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        return rs.next(); // If a record is found, return true
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error checking username", ex);
        }
        return false; // Return false if connection is null or if an exception occurs
    }

    public boolean checkPassword(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT password FROM Staff WHERE username = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String storedPassword = rs.getString("password");
                            // Mã hóa mật khẩu người dùng nhập trước khi so sánh
                            String hashedInputPassword = UtilHashPass.EncodePassword(password);

                            // So sánh mật khẩu đã mã hóa
                            return storedPassword.equals(hashedInputPassword);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error checking password", e);
        }
        return false;
    }

    public boolean checkAuthenticationUser(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT password FROM Staff WHERE username = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String storedPassword = rs.getString("password");

                            String hashedInputPassword = UtilHashPass.EncodePassword(password);

                            return storedPassword.equals(hashedInputPassword);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error during authentication", e);
        }
        return false;
    }

    public Staff getAllInformationstaff(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Staff WHERE username = ? and password = ?";
                String hashedInputPassword = UtilHashPass.EncodePassword(password);
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, hashedInputPassword);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Staff staff = new Staff();
                            staff.setStaffID(rs.getInt("staffID"));
                            staff.setUsername(rs.getString("username"));
                            staff.setName(rs.getString("name"));
                            staff.setEmail(rs.getString("email"));
                            staff.setPhoneNumber(rs.getString("phoneNumber"));
                            staff.setHireDate(rs.getDate("hireDate"));
                            staff.setStaffImg(rs.getString("staffImg"));
                            return staff;
                        }
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            DBContext.closeConnection(conn);
        }
        return null;
    }

    public List<Staff> getAllStaff() {
        List<Staff> list = new ArrayList<>();
        Connection conn = null;
        String sql = "select * from Staff where status != -1";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int staffID = rs.getInt(1);
                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(6);
                String phoneNumber = rs.getString(7);
                String name = rs.getString(5);
                Date hireDate = rs.getDate(8);
                String staffImg = rs.getString(9);
                int status = rs.getInt(10);
                Staff staff = new Staff(staffID, roleID, username, password, name, email, phoneNumber, hireDate, staffImg, status);
                list.add(staff);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<Staff> getAllStaffHaveRole() {
        List<Staff> list = new ArrayList<>();
        Connection conn = null;
        String sql = "select * from Staff s\n"
                + "Join Role r ON s.roleID = r.roleID\n"
                + "where status != -1";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int staffID = rs.getInt(1);
                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(6);
                String phoneNumber = rs.getString(7);
                String name = rs.getString(5);
                Date hireDate = rs.getDate(8);
                String staffImg = rs.getString(9);
                int status = rs.getInt(10);
                Staff staff = new Staff(staffID, roleID, username, password, name, email, phoneNumber, hireDate, staffImg, status);
                staff.setRoleAuthority(rs.getString("roleAuthority"));
                list.add(staff);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public List<String> getRoleNameByID() {

        String sql = "Select R.role_name from Role R join Staff S on s.roleID = r.roleID ";
        List<String> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                list.add(rs.getString(1));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int dismissStaff(int staffID) {
        int n = 0;
        String sql = "UPDATE Staff\n"
                + "SET status = -1\n"
                + "WHERE staffID = ?";
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, staffID);

            n = pre.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return n;

    }

    public int getAmountOfStaff() {
        StaffDAO dao = new StaffDAO();
        List<Staff> list = dao.getAllStaff();
        int amountStaff = list.size();
        return amountStaff;
    }

    public int UpdateStaffInfo(String name, String phoneNumber, String staffImg, int staffID) {
        int n = 0;
        String sql = "UPDATE Staff SET name = ?, phoneNumber = ?, staffImg = ? WHERE staffID = ?;";

        // Sử dụng try-with-resources để đảm bảo kết nối và preparedStatement được đóng tự động
        try (Connection conn = DBContext.getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {

            // Thiết lập các giá trị cho câu lệnh SQL
            pre.setString(1, name);
            pre.setString(2, phoneNumber);
            pre.setString(3, staffImg);
            pre.setInt(4, staffID);

            // Thực thi câu lệnh SQL và trả về số dòng được cập nhật
            n = pre.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return n; // Trả về số dòng được cập nhật (0 nếu không có dòng nào)
    }

    public Staff getStaffByID(int staffID) {
        Staff staff = new Staff();
        Connection conn = null;
        String sql = "select * from Staff where staffID = ? and status !=-1 ";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, staffID);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {

                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);
                String phoneNumber = rs.getString(6);
                String name = rs.getString(7);
                Date hireDate = rs.getDate(8);
                String staffImg = rs.getString(9);
                int status = rs.getInt(10);
                staff = new Staff(staffID, roleID, username, password, name, email, phoneNumber, hireDate, staffImg, status);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return staff;
    }

    public List<Staff> staffPaging(int page, int recordsPerPage) {
        List<Staff> list = new ArrayList<>();
        Connection conn = null;
        try {
            int offset = (page - 1) * recordsPerPage;
            String sql = "select * from Staff where status!=-1 order by staffID offset ? rows fetch next ? rows only";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int staffID = rs.getInt(1);
                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String name = rs.getString(5);
                String email = rs.getString(6);
                String phoneNumber = rs.getString(7);
                Date hireDate = rs.getDate(8);
                Staff staff = new Staff(staffID, roleID, username, password, name, phoneNumber, email, hireDate);
                list.add(staff);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int count(int recordsPerPage) {
        int totalPages = 0;
        Connection conn = null;
        try {
            String sql = "select count(*) from staff where status!=-1 ";
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int totalRecords = rs.getInt(1);
            totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalPages;
    }

    public List<Staff> getStaffByRole(int roleID, String search, String orderBy, int page, int recordsPerPage) {
        List<Staff> staffList = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            int offset = (page - 1) * recordsPerPage;
            StringBuilder sql = new StringBuilder("SELECT * FROM Staff WHERE 1 = 1 and status!=-1");

            // Nếu roleID không phải là 0, thêm điều kiện lọc theo roleID
            if (roleID > 0) {
                sql.append("AND roleID = ? ");
            }

            // Kiểm tra nếu có từ khóa tìm kiếm
            if (search != null && !search.isEmpty()) {
                sql.append("AND name COLLATE Latin1_General_CI_AI LIKE ? ");
            }

            // Thêm điều kiện sắp xếp
            if ("asc".equalsIgnoreCase(orderBy)) {
                sql.append("ORDER BY name ASC ");
            } else if ("desc".equalsIgnoreCase(orderBy)) {
                sql.append("ORDER BY name DESC ");
            } else {
                sql.append("ORDER BY staffID ");
            }

            // Thêm giới hạn phân trang
            sql.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            // Thiết lập giá trị cho các tham số
            int paramIndex = 1;

            // Nếu có roleID, thiết lập tham số cho nó
            if (roleID > 0) {
                ps.setInt(paramIndex++, roleID);
            }

            // Nếu có từ khóa tìm kiếm, thiết lập tham số cho nó
            if (search != null && !search.isEmpty()) {
                ps.setString(paramIndex++, "%" + search + "%");
            }

            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex, recordsPerPage);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                staffList.add(new Staff(
                        rs.getInt("staffID"),
                        rs.getInt("roleID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        rs.getDate("hireDate")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return staffList;
    }

    public int countActive(int roleID, String search, int recordsPerPage) {
        Connection conn = null;
        int totalPages = 0;
        try {
            conn = DBContext.getConnection();
            // Xây dựng câu lệnh SQL động tùy thuộc vào roleID và name
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Staff WHERE 1=1 and  status!=-1");

            // Nếu roleID không bằng 0, thêm điều kiện lọc theo roleID
            if (roleID > 0) {
                sql.append(" AND roleID = ?");
            }

            // Nếu search không rỗng, thêm điều kiện lọc theo username hoặc name
            if (search != null && !search.isEmpty()) {
                sql.append(" AND (username COLLATE Latin1_General_CI_AI LIKE ? OR name COLLATE Latin1_General_CI_AI LIKE ?)");
            }

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (roleID > 0) {
                ps.setInt(paramIndex++, roleID);
            }
            if (search != null && !search.isEmpty()) {
                ps.setString(paramIndex++, "%" + search + "%");
                ps.setString(paramIndex, "%" + search + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int totalRecords = rs.getInt(1);
                totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalPages;
    }

    public boolean updateStaffRole(int staffID, int roleID) {
        String query = "UPDATE Staff SET roleID = ? WHERE staffID = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, roleID);
            ps.setInt(2, staffID);

            return ps.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Xử lý ngoại lệ một cách hợp lý trong sản xuất
        }
        return false;
    }

    public List<Staff> getALlBan() {
        List<Staff> list = new ArrayList<>();
        Connection conn = null;
        String sql = "select * from Staff where status = -1";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int staffID = rs.getInt(1);
                int roleID = rs.getInt(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(6);
                String phoneNumber = rs.getString(7);
                String name = rs.getString(5);
                Date hireDate = rs.getDate(8);
                String staffImg = rs.getString(9);
                int status = rs.getInt(10);
                Staff staff = new Staff(staffID, roleID, username, password, name, email, phoneNumber, hireDate, staffImg, status);
                list.add(staff);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public boolean unban(int staffID) {
        String query = "UPDATE Staff SET status = 1 WHERE staffID = ?";
        try (Connection conn = DBContext.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, staffID);

            return ps.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Xử lý ngoại lệ một cách hợp lý trong sản xuất
        }
        return false;
    }

    public List<Staff> getStaffByTaskType(String taskType) {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT DISTINCT s.staffID, s.name, s.email, s.phoneNumber, s.hireDate, r.role_name "
                + "FROM Staff s "
                + "JOIN Role r ON s.roleID = r.roleID "
                + "JOIN Task t ON r.roleAuthority = t.taskType "
                + "WHERE t.taskType = ?";

        try (Connection connection = DBContext.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, taskType);  // Thiết lập giá trị cho taskType

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Staff staff = new Staff();
                staff.setStaffID(resultSet.getInt("staffID"));
                staff.setName(resultSet.getString("name"));
                staff.setEmail(resultSet.getString("email"));
                staff.setPhoneNumber(resultSet.getString("phoneNumber"));
                staff.setHireDate(resultSet.getDate("hireDate"));
                // Lưu ý: staffImg không được lấy từ truy vấn, nên bạn có thể bỏ qua hoặc lấy từ một nguồn khác nếu cần
                // staff.setStaffImg(resultSet.getString("staffImg")); // Dòng này có thể bỏ qua

                // Nếu bạn cần thiết lập thêm các thuộc tính khác, bạn có thể thêm ở đây
                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staffList;
    }
    public static void main(String[] args) {

        StaffDAO dao = new StaffDAO();
        List<Staff> n = dao.getAllStaffHaveRole();
        System.out.println("Húp" + n.get(1).getRoleAuthority());
        System.out.println(""+dao.getAllInformationstaff("Nghia", "sRY4rMY8/DtYD2+OQLAkTVClzcY="));

    }
}
