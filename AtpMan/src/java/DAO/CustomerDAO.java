/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author WuanTun
 */
import utils.DBContext;
import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import utils.UtilHashPass;

public class CustomerDAO {

    private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());

    // QUAN
    public boolean checkUsername(String username) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Customer WHERE username = ?";
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

    // QUAN
    public boolean checkPassword(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT password FROM Customer WHERE username = ?";
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

    // QUAN
    public boolean checkAuthenticationUser(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT password FROM Customer WHERE username = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String storedPassword = rs.getString("password"); // da ma hoa

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

    // QUAN
    public Customer getAllInformationCustomer(String username, String password) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String hashedInputPassword = UtilHashPass.EncodePassword(password);
                String sql = "SELECT * FROM Customer WHERE username = ? and password = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, hashedInputPassword);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            Customer customer = new Customer();
                            customer.setCustomerID(rs.getInt("customerID")); // Lấy customerID từ kết quả
                            customer.setUsername(rs.getString("username"));
                            customer.setName(rs.getString("name"));
                            customer.setEmail(rs.getString("email"));
                            customer.setPhoneNumber(rs.getString("phoneNumber"));
                            customer.setDob(rs.getDate("dob"));
                            customer.setRegistrationDate(rs.getDate("registrationDate"));
                            customer.setIsOwner(rs.getInt("isOwner"));
                            customer.setAvatar(rs.getString("cusImage"));
                            customer.setStatus(rs.getInt("status"));
                            return customer;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, "Error retrieving customer information", e);
        }
        return null; // Trả về null nếu không tìm thấy hoặc có lỗi xảy ra
    }

    public String getEmailByCustomerID(int customerID) throws SQLException {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT email FROM Customer WHERE customerID = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, customerID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString("email");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding email by customerID", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return null; // Trả về null nếu không tìm thấy email
    }

    // QUAN
    public boolean existsByUsernameOrGmail(String username, String email) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Customer WHERE username = ? OR email = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, email);
                    try (ResultSet rs = ps.executeQuery()) {
                        return rs.next();
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error checking existence by username or email", e);
        }
        return false;
    }

    // QUAN
    public void createNewCustomer(String username, String password, String name, String email, String phoneNumber,
            String isOwner, int status) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String hashedInputPassword = UtilHashPass.EncodePassword(password);
                String sql = "INSERT INTO Customer (username, password, name, email, phoneNumber, isOwner, status) VALUES (?, ?, ?, ?, ?, ?,1)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, username);
                    ps.setString(2, hashedInputPassword); // Save plain password, or hash it if needed
                    ps.setString(3, name);
                    ps.setString(4, email);
                    ps.setString(5, phoneNumber);
                    ps.setString(6, isOwner); // 1 for Resident, 0 for Owner
//                    ps.setInt(7, status); // 1 for active, 0 for inactive
                    ps.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new customer", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }

    // QUAN
    public int getCustomerIDByUsername(String username) {
        Connection conn = null;
        int customerID = -1;
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT customerID FROM Customer WHERE username = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        customerID = rs.getInt("customerID");
                        System.out.println("Retrieved customerID: " + customerID);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting customerID", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return customerID;
    }

    public int getLatestCustomerID() throws SQLException, ClassNotFoundException {
        int latestCustomerID = -1;  // Mặc định là -1 nếu không tìm thấy
        String sql = "SELECT TOP 1 customerID FROM Customer ORDER BY customerID DESC";  // Lấy customerID mới nhất (sắp xếp giảm dần)

        try (Connection conn = DBContext.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                latestCustomerID = rs.getInt("customerID");
            }
        }
        return latestCustomerID;
    }

    // QUAN
    public String getPasswordByID(int customerID) {
        Connection conn = null;
        String password = null;
        try {
            conn = DBContext.getConnection();
            String sql = "SELECT password FROM Customer WHERE customerID = ? ";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, customerID);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        password = rs.getString("password");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding password by ID", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return password;
    }

    // QUAN
    public boolean updatePassword(int customerID, String newPassword) {

        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE Customer SET password = ? WHERE customerID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    String hashedPassword = UtilHashPass.EncodePassword(newPassword); // Hash the new password
                    ps.setString(1, hashedPassword);
                    ps.setInt(2, customerID);
                    int rowsUpdated = ps.executeUpdate();
                    System.out.println("Rows Updated: " + rowsUpdated);

                    return rowsUpdated > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error updating password", ex);
        } finally {
            DBContext.closeConnection(conn); // Ensure connection is closed
        }
        return false; // Return false if connection is null or if an exception occurs
    }

    // QUAN
    public boolean updateCustomerEmail(int customerID, String newemail) throws SQLException {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE Customer SET email = ? WHERE customerID = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, newemail);
                    ps.setInt(2, customerID);
                    int rowsUpdate = ps.executeUpdate();
                    System.out.println("Rows update: " + rowsUpdate);
                    return rowsUpdate > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, "Error updating password", ex);
        } finally {
            DBContext.closeConnection(conn);
        }
        return false;
    }

    // QUAN
    public Customer findCustomerByGmail(String gmail) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT customerID FROM Customer WHERE email = ?";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, gmail);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            return getCustomer(rs.getInt("customerID"));
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error finding customer by gmail", e);
        } finally {
            DBContext.closeConnection(conn);
        }
        return null;
    }

    public String getApartmentNumberByCustomerID(int customerID) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                // Sửa lại truy vấn SQL để lấy apartmentNumber theo customerID
                String sql = """
                         SELECT a.apartmentNumber
                         FROM Customer cus
                         INNER JOIN Living lv ON lv.customerID = cus.customerID
                         INNER JOIN Apartment a ON a.apartmentID = lv.apartmentID
                         WHERE cus.customerID = ? AND lv.endDate IS NULL""";

                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, customerID); // Sử dụng customerID làm tham số
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String apartmentNumber = rs.getString("apartmentNumber");
                            System.out.println("Query result: Apartment number for customerID " + customerID + " is " + apartmentNumber);
                            return apartmentNumber;
                        } else {
                            System.out.println("No apartment found for customerID " + customerID);
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error getting apartment number for customerID " + customerID);
            e.printStackTrace(); // In ra thông báo lỗi chi tiết
        } finally {
            DBContext.closeConnection(conn);
        }
        return null;
    }

//    public int getCustomerIDByApartmentNumber(String apartmentNumber) throws SQLException, ClassNotFoundException {
//        Connection conn = null;
//        try {
//            conn = DBContext.getConnection();
//            if (conn != null) {
//                String sql = """
//                         SELECT cus.customerID, a.apartmentNumber
//                         FROM Task t
//                         INNER JOIN HandleRequest hr ON hr.taskID = t.taskID
//                         INNER JOIN RequestComplaint rc ON rc.requestID = hr.requestID
//                         INNER JOIN Customer cus ON cus.customerID = rc.customerID
//                         INNER JOIN Living lv ON lv.customerID = cus.customerID
//                         INNER JOIN Apartment a ON a.apartmentID = lv.apartmentID
//                         WHERE lv.endDate IS NULL
//                         AND a.apartmentNumber = ?""";
//                System.out.println("Executing query: " + sql); // In câu truy vấn
//
//                try (PreparedStatement ps = conn.prepareStatement(sql)) {
//                    ps.setString(1, apartmentNumber);
//                    try (ResultSet rs = ps.executeQuery()) {
//                        if (rs.next()) {
//                            int customerID = rs.getInt("customerID");
//                            System.out.println("Query result: CustomerID for apartment " + apartmentNumber + " is " + customerID);
//                            return customerID; // Trả về customerID
//                        } else {
//                            System.out.println("No customer found for apartment " + apartmentNumber);
//                        }
//                    }
//                }
//            }
//        } catch (SQLException | ClassNotFoundException e) {
//            System.out.println("Error getting customerID for apartment " + apartmentNumber);
//            e.printStackTrace(); // In lỗi chi tiết
//        } finally {
//            DBContext.closeConnection(conn);
//        }
//        return -1; // Trả về -1 nếu không tìm thấy customerID
//    }
    public Vector<Customer> getAllCustomer() {
        Connection conn = null;
        Vector<Customer> vector = new Vector<>();
        String sql = "select * from Customer";
        try {
            conn = DBContext.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                int customerID = rs.getInt(1);
                String username = rs.getString(2);
                String name = rs.getString(3);
                String email = rs.getString(4);
                String phoneNumber = rs.getString(5);
                Date age = rs.getDate(6);
                Date registrationDate = rs.getDate(7);
                int isOwner = rs.getInt(8);
                Customer customer = new Customer(customerID, username, name, email, phoneNumber, age, registrationDate,
                        isOwner);
                vector.add(customer);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int getAmountOfCustomer() {
        CustomerDAO dao = new CustomerDAO();
        Vector<Customer> vector = dao.getAllCustomer();

        return vector.size();
    }

    // QUAN
    public Customer getCustomer(int id) {
        Connection conn = null;
        Customer customer = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT * FROM Customer WHERE customerID = ?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setInt(1, id);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            customer = new Customer(id,
                                    resultSet.getString("username"),
                                    resultSet.getString("name"),
                                    resultSet.getString("email"),
                                    resultSet.getString("phoneNumber"),
                                    resultSet.getDate("dob"),
                                    resultSet.getDate("registrationDate"),
                                    resultSet.getInt("isOwner"),
                                    resultSet.getInt("status"));
                        }
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error getting customer", e);
        }
        return customer;
    }

    // KhangPM
    public List<Customer> getLivingInApartment(int apartmentID, int currentPage, int rowsPerPage,
            List<String> searchTermList) {
        List<Customer> list = new ArrayList<>();
        Connection connection = null;
        String sql = "select c.customerID, c.name, c.email, c.phoneNumber, c.dob, c.isOwner, c.status, l.startDate from Customer c\n"
                + "inner join Living l on l.customerID = c.customerID\n"
                + "where l.apartmentID = ? \n"
                + "and endDate is null";
        int count = 0;
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like ? ";
                count++;
            } else {
                for (int i = 0; i < searchTermList.size(); i++) {
                    sql += " and c.name like ? ";
                    count++;
                }
            }
        }
        sql += " order by c.customerID asc\n"
                + "offset ? rows fetch next ? rows only";

        int fetchStart = (currentPage - 1) * rowsPerPage;

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            if (count > 0) {
                int index = 2;
                for (int i = 0; i < count; i++) {
                    statement.setString(index, "%" + searchTermList.get(i) + "%");
                    index++;
                }
            }
            statement.setInt(count + 2, fetchStart);
            statement.setInt(count + 3, rowsPerPage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setEmail(rs.getString(3));
                customer.setPhoneNumber(rs.getString(4));
                customer.setDob(rs.getDate("dob"));
                customer.setIsOwner(rs.getInt(6));
                customer.setStatus(rs.getInt(7));
                customer.setLivingDate(rs.getDate(8));
                list.add(customer);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return list;
    }
//Nghia

    public void addResident(String name, String email, String phoneNumber, String dob, Date registrationDate, String isOwner) {
        Connection conn = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO Customer (name, email, phoneNumber, dob, registrationDate, isOwner, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, name);
                    ps.setString(2, email); // Save plain password, or hash it if needed
                    ps.setString(3, phoneNumber);
                    ps.setString(4, dob);
                    ps.setDate(5, registrationDate);
                    ps.setString(6, isOwner); // 1 for Resident, 0 for Owner
                    ps.setInt(7, 1); // 1 for Resident, 0 for Owner
                    ps.executeUpdate();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error creating new customer", e);
        } finally {
            DBContext.closeConnection(conn);
        }
    }

    public int updateCustomerInfo(String name, String phoneNumber, String cusImg, int customerID) {
        int n = 0;
        String sql = "UPDATE Customer SET name = ?, phoneNumber = ?, cusImg = ? WHERE customerID = ?;";

        // Sử dụng try-with-resources để đảm bảo kết nối và preparedStatement được đóng tự động
        try (Connection conn = DBContext.getConnection(); PreparedStatement pre = conn.prepareStatement(sql)) {

            // Thiết lập các giá trị cho câu lệnh SQL
            pre.setString(1, name);
            pre.setString(2, phoneNumber);
            pre.setString(3, cusImg);
            pre.setInt(4, customerID);

            // Thực thi câu lệnh SQL và trả về số dòng được cập nhật
            n = pre.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return n; // Trả về số dòng được cập nhật (0 nếu không có dòng nào)
    }

    // KhangPM
    public int countLivingInApartment(int apartmentID, List<String> searchTermList) {
        int result = 0;
        Connection connection = null;
        String sql = "select count(*) from Customer c\n"
                + "inner join Living l on l.customerID = c.customerID\n"
                + "where l.apartmentID = ?\n";

        int count = 0;
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like ? ";
                count++;
            } else {
                for (int i = 0; i < searchTermList.size(); i++) {
                    sql += " and c.name like ? ";
                    count++;
                }
            }
        }

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, apartmentID);
            if (count > 0) {
                int index = 2;
                for (int i = 0; i < count; i++) {
                    statement.setString(index, "%" + searchTermList.get(i) + "%");
                    index++;
                }
            }
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return result;
    }

    // KhangPM
    /**
     *
     * @param currentPage current page for paging
     * @param rowsPerPage rows per page
     * @param buildingId id of building
     * @param apartmentNumber
     * @param statusLiving status in living table. 1 for living (endDate not
     * null) 0 for not living (endDate = null), 2 for both
     * @param isOwner 1 for owner, 0 for tenant, 2 for both
     * @param status 1 for active, 0 for inactive
     * @param searchTermList
     * @return
     */
    // not using
    public List<Customer> getResidentForManage(int currentPage, int rowsPerPage, int buildingId, String apartmentNumber,
            int statusLiving, int isOwner, List<String> searchTermList) {
        List<Customer> list = new ArrayList<>();
        Connection connection = null;
        String sql = "select distinct c.* from Customer c\n"
                + "inner join Living lv on lv.customerID = c.customerID\n";
        if (statusLiving == 0) {
            sql += " and( lv.endDate is not null) \n";
        } else if (statusLiving == 1) {
            sql += " and( lv.endDate is null) \n";
        }
        sql += " inner join Apartment a on a.apartmentID = lv.apartmentID\n"
                + "inner join Building b on b.buildingID = a.buildingID\n";
        sql += " where 1=1 \n";
        if (buildingId != 0) {
            sql += " and b.buildingID = ? \n";
        }
        if (apartmentNumber != null && !apartmentNumber.isBlank()) {
            sql += " and a.apartmentNumber like '%" + apartmentNumber + "%' \n";
        }
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like N'%" + searchTermList + "%' \n";
            } else {
                sql += " and ( c.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " or c.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " or c.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') \n";
            }
        }
        if (statusLiving == 0) {
            sql += " and c.customerID not in (select c.customerID from Customer c\n"
                    + "inner join Living lv on lv.customerID = c.customerID and( lv.endDate is null))\n";
        }
        if (isOwner == 1) {
            sql += " and c.isOwner = 1\n";
        } else if (isOwner == 0) {
            sql += " and c.isOwner = 0\n";
        }
        sql += " order by c.customerID\n"
                + "offset ? rows fetch next ? rows only";

        int fetchStart = (currentPage - 1) * rowsPerPage;

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;
            if (buildingId != 0) {
                statement.setInt(index++, buildingId);
            }
            statement.setInt(index++, fetchStart);
            statement.setInt(index, rowsPerPage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt(1));
                customer.setName(rs.getString(4));
                customer.setEmail(rs.getString(5));
                customer.setPhoneNumber(rs.getString(6));
                customer.setDob(rs.getDate("dob"));
                customer.setIsOwner(rs.getInt(9));
                customer.setStatus(rs.getInt(11));
                list.add(customer);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return list;
    }

    // KhangPM
    public List<Customer> getActiveResidentForManage(int currentPage, int rowsPerPage, int buildingId,
            String apartmentNumber, int isOwner, int status, List<String> searchTermList) {
        List<Customer> list = new ArrayList<>();
        Connection connection = null;
        String sql = """
                select distinct c.*, a.apartmentNumber, lv.startDate, lv.endDate from Customer c
                left join Living lv on lv.customerID = c.customerID
                left join Apartment a on a.apartmentID = lv.apartmentID
                left join Building b on b.buildingID = a.buildingID
                where (c.isOwner = 0 and (NOT EXISTS (SELECT 1 FROM Living l2 WHERE l2.customerID = c.customerID AND l2.endDate IS NOT NULL) or lv.endDate is null ) or (c.isOwner = 1 ))
                and c.status = 1""";

        if (isOwner == 1) {
            sql += " and c.isOwner = 1\n";
        } else if (isOwner == 0) {
            sql += " and c.isOwner = 0\n";
        }
        if (buildingId != 0) {
            sql += " and b.buildingID = ? \n";
        }
        if (apartmentNumber != null && !apartmentNumber.isBlank()) {
            sql += " and a.apartmentNumber like '%" + apartmentNumber.trim() + "%' \n";
        }
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like N'%" + searchTermList.get(0) + "%' \n";
            } else {
                sql += " and ( c.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " or c.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " or c.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') \n";
            }
        }

        sql += " order by c.customerID\n"
                + "offset ? rows fetch next ? rows only";

        int fetchStart = (currentPage - 1) * rowsPerPage;

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;
            if (buildingId != 0) {
                statement.setInt(index++, buildingId);
            }
            statement.setInt(index++, fetchStart);
            statement.setInt(index, rowsPerPage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setDob(rs.getDate("dob"));
                customer.setIsOwner(rs.getInt("isOwner"));
                customer.setStatus(rs.getInt("status"));
                customer.setApartmentNumber(rs.getString("apartmentNumber"));
                list.add(customer);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err" + e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return list;
    }

    // KhangPM
    public int countActiveResidentForManage(int buildingId, String apartmentNumber, int isOwner, int status,
            List<String> searchTermList) {
        int count = 0;
        Connection connection = null;
        String sql = """
                select distinct count(*) from Customer c
                left join Living lv on lv.customerID = c.customerID
                left join Apartment a on a.apartmentID = lv.apartmentID
                left join Building b on b.buildingID = a.buildingID
                where (c.isOwner = 0 and (NOT EXISTS (SELECT 1 FROM Living l2 WHERE l2.customerID = c.customerID AND l2.endDate IS NOT NULL) or lv.endDate is null ) or (c.isOwner = 1 ))
                and c.status = 1""";

        if (isOwner == 1) {
            sql += " and c.isOwner = 1\n";
        } else if (isOwner == 0) {
            sql += " and c.isOwner = 0\n";
        }
        if (buildingId != 0) {
            sql += " and b.buildingID = ? \n";
        }
        if (apartmentNumber != null && !apartmentNumber.isBlank()) {
            sql += " and a.apartmentNumber like '%" + apartmentNumber.trim() + "%' \n";
        }
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like N'%" + searchTermList.get(0) + "%' \n";
            } else {
                sql += " and ( c.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " or c.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " or c.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') \n";
            }
        }
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;
            if (buildingId != 0) {
                statement.setInt(index++, buildingId);
            }
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err" + e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return count;
    }

    // KhangPM
    public List<Customer> getRegistResidentForManage(int currentPage, int rowsPerPage, int buildingId,
            String apartmentNumber, List<String> searchTermList) {
        List<Customer> list = new ArrayList<>();
        Connection connection = null;
        String sql = """
                select distinct c.*, a.apartmentNumber, lv.startDate, lv.endDate from Customer c
                left join Living lv on lv.customerID = c.customerID
                left join Apartment a on a.apartmentID = lv.apartmentID
                left join Building b on b.buildingID = a.buildingID
                where c.status = 3""";

        if (buildingId != 0) {
            sql += " and b.buildingID = ? \n";
        }
        if (apartmentNumber != null && !apartmentNumber.isBlank()) {
            sql += " and a.apartmentNumber like '%" + apartmentNumber.trim() + "%' \n";
        }
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like N'%" + searchTermList.get(0) + "%' \n";
            } else {
                sql += " and ( c.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " or c.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " or c.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') \n";
            }
        }

        sql += " order by c.customerID\n"
                + "offset ? rows fetch next ? rows only";

        int fetchStart = (currentPage - 1) * rowsPerPage;

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;
            if (buildingId != 0) {
                statement.setInt(index++, buildingId);
            }
            statement.setInt(index++, fetchStart);
            statement.setInt(index, rowsPerPage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setDob(rs.getDate("dob"));
                customer.setIsOwner(rs.getInt("isOwner"));
                customer.setStatus(rs.getInt("status"));
                customer.setApartmentNumber(rs.getString("apartmentNumber"));
                list.add(customer);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err" + e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return list;
    }

    // KhangPM
    public int countRegistResidentForManage(int buildingId, String apartmentNumber, List<String> searchTermList) {
        int count = 0;
        Connection connection = null;
        String sql = """
                select distinct count(*) from Customer c
                left join Living lv on lv.customerID = c.customerID
                left join Apartment a on a.apartmentID = lv.apartmentID
                left join Building b on b.buildingID = a.buildingID
                where c.status = 3""";

        if (buildingId != 0) {
            sql += " and b.buildingID = ? \n";
        }
        if (apartmentNumber != null && !apartmentNumber.isBlank()) {
            sql += " and a.apartmentNumber like '%" + apartmentNumber.trim() + "%' \n";
        }
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like N'%" + searchTermList.get(0) + "%' \n";
            } else {
                sql += " and ( c.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " or c.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " or c.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') \n";
            }
        }

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;
            if (buildingId != 0) {
                statement.setInt(index++, buildingId);
            }
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err" + e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return count;
    }

    // KhangPM
    public List<Customer> getInActiveResidentForManage(int currentPage, int rowsPerPage, int buildingId,
            String apartmentNumber, List<String> searchTermList) {
        List<Customer> list = new ArrayList<>();
        Connection connection = null;
        String sql = """
                     select distinct c.*, a.apartmentNumber, lv.startDate, lv.endDate from Customer c
                     left join Living lv on lv.customerID = c.customerID
                     left join Apartment a on a.apartmentID = lv.apartmentID
                     left join Building b on b.buildingID = a.buildingID
                     where c.status = 0""";

        if (buildingId != 0) {
            sql += " and b.buildingID = ? \n";
        }
        if (apartmentNumber != null && !apartmentNumber.isBlank()) {
            sql += " and a.apartmentNumber like '%" + apartmentNumber.trim() + "%' \n";
        }
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like N'%" + searchTermList.get(0) + "%' \n";
            } else {
                sql += " and ( c.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " or c.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " or c.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') \n";
            }
        }

        sql += " order by c.customerID\n"
                + "offset ? rows fetch next ? rows only";

        int fetchStart = (currentPage - 1) * rowsPerPage;

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;
            if (buildingId != 0) {
                statement.setInt(index++, buildingId);
            }
            statement.setInt(index++, fetchStart);
            statement.setInt(index, rowsPerPage);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(rs.getInt("customerID"));
                customer.setName(rs.getString("name"));
                customer.setEmail(rs.getString("email"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setDob(rs.getDate("dob"));
                customer.setIsOwner(rs.getInt("isOwner"));
                customer.setStatus(rs.getInt("status"));
                customer.setApartmentNumber(rs.getString("apartmentNumber"));
                list.add(customer);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err" + e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return list;
    }

    // KhangPM
    public int countInActiveResidentForManage(int buildingId, String apartmentNumber, List<String> searchTermList) {
        int count = 0;
        Connection connection = null;
        String sql = """
                     select distinct count(*) from Customer c
                     left join Living lv on lv.customerID = c.customerID
                     left join Apartment a on a.apartmentID = lv.apartmentID
                     left join Building b on b.buildingID = a.buildingID
                     where c.status = 0""";

        if (buildingId != 0) {
            sql += " and b.buildingID = ? \n";
        }
        if (apartmentNumber != null && !apartmentNumber.isBlank()) {
            sql += " and a.apartmentNumber like '%" + apartmentNumber.trim() + "%' \n";
        }
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like N'%" + searchTermList.get(0) + "%' \n";
            } else {
                sql += " and ( c.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " or c.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " or c.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') \n";
            }
        }

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;
            if (buildingId != 0) {
                statement.setInt(index++, buildingId);
            }
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err" + e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return count;
    }

    public void updateStatusResident(int status, int customerId) {
        Connection connection = null;
        String sql = """
                update Customer
                set status = ?
                where customerID = ?
                """;
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            statement.setInt(2, customerId);
            int i = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err: " + e.getMessage());
        }
    }

    public void updateLivingResident(int customerId) {
        Connection connection = null;
        String sql = """
                update Living
                set endDate = ?
                where customerID = ? and endDate is null
                """;
        try {
            LocalDate currentDate = LocalDate.now();
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(currentDate));
            statement.setInt(2, customerId);
            int i = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err: " + e.getMessage());
        }
    }

    public void removeAccount(int customerId) {
        Connection connection = null;
        String sql = """
                     update Customer
                     set username = NULL, password = NULL, email = NULL
                     where customerID = ?
                     """;
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            int i = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("err: " + e.getMessage());
        }
    }

    // KhangPM - not use
    public int countResidentSearch(int buildingId, String apartmentNumber, int statusLiving, int isOwner,
            List<String> searchTermList) {
        int count = 0;
        Connection connection = null;
        String sql = "select count(*) from Customer c\n"
                + "inner join Living lv on lv.customerID = c.customerID\n";
        if (statusLiving == 0) {
            sql += " and( lv.endDate is not null) \n";
        } else if (statusLiving == 1) {
            sql += " and( lv.endDate is null) \n";
        }
        sql += " inner join Apartment a on a.apartmentID = lv.apartmentID\n"
                + "inner join Building b on b.buildingID = a.buildingID\n";
        sql += " where 1=1 \n";
        if (buildingId != 0) {
            sql += " and b.buildingID = ? \n";
        }
        if (apartmentNumber != null && !apartmentNumber.isBlank()) {
            sql += " and a.apartmentNumber like '%" + apartmentNumber + "%' \n";
        }
        if (searchTermList != null && !searchTermList.isEmpty()) {
            if (searchTermList.size() == 1) {
                sql += " and c.name like N'%" + searchTermList + "%' \n";
            } else {
                sql += " and ( c.name like N'%" + searchTermList.get(0) + "%' ";
                for (int i = 1; i < searchTermList.size() - 1; i++) {
                    sql += " or c.name like N'%" + searchTermList.get(i) + "%' ";
                }
                sql += " or c.name like N'%" + searchTermList.get(searchTermList.size() - 1) + "%') \n";
            }
        }
        if (statusLiving == 0) {
            sql += " and c.customerID not in (select c.customerID from Customer c\n"
                    + "inner join Living lv on lv.customerID = c.customerID and( lv.endDate is null))\n";
        }
        if (isOwner == 1) {
            sql += " and c.isOwner = 1\n";
        } else if (isOwner == 0) {
            sql += " and c.isOwner = 0\n";
        }

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;
            if (buildingId != 0) {
                statement.setInt(index++, buildingId);
            }
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return count;
    }

    // KhangPM
    public int countResident() {
        int count = 0;
        Connection connection = null;
        String sql = "select count(*) from Customer where status = 1 or status = 3";

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return count;
    }

    // KhangPM
    public int countResidentByStatus(int status) {
        int count = 0;
        Connection connection = null;
        String sql = "select count(*) from Customer where status = ?";

        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            DBContext.closeConnection(connection);
        }
        return count;
    }

    public boolean addCustomerToApartment(Customer customer, int apartmentID, Date startDate) {
        String customerSql = "INSERT INTO Customer (name, email, phoneNumber, dob, registrationDate, isOwner, status) VALUES (?, ?, ?, ?, ?, 0, 3)";
        String livingSql = "INSERT INTO Living (customerID, apartmentID, startDate) VALUES (?, ?, ?)";
        boolean isAdded = false;
        Connection conn = null;

        try {
            conn = DBContext.getConnection();
            if (conn == null || conn.isClosed()) {
                LOGGER.log(Level.SEVERE, "Failed to establish a database connection.");
                return false;
            }

            // Disable auto-commit for transaction management
            conn.setAutoCommit(false);

            // Insert customer into Customer table
            try (PreparedStatement customerStmt = conn.prepareStatement(customerSql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                customerStmt.setString(1, customer.getName());
                customerStmt.setString(2, customer.getEmail());
                customerStmt.setString(3, customer.getPhoneNumber());
                customerStmt.setDate(4,
                        customer.getDob() != null ? new java.sql.Date(customer.getDob().getTime()) : null);
                customerStmt.setDate(5,
                        customer.getRegistrationDate() != null
                        ? new java.sql.Date(customer.getRegistrationDate().getTime())
                        : null);

                int customerRows = customerStmt.executeUpdate();
                if (customerRows > 0) {
                    try (ResultSet generatedKeys = customerStmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int newCustomerID = generatedKeys.getInt(1);
                            customer.setCustomerID(newCustomerID);
                            LOGGER.log(Level.INFO, "Added Customer with ID: {0}", newCustomerID);

                            // Insert record into Living table
                            try (PreparedStatement livingStmt = conn.prepareStatement(livingSql)) {
                                livingStmt.setInt(1, newCustomerID);
                                livingStmt.setInt(2, apartmentID);
                                livingStmt.setDate(3,
                                        startDate != null ? new java.sql.Date(startDate.getTime()) : null);

                                int livingRows = livingStmt.executeUpdate();
                                if (livingRows > 0) {
                                    LOGGER.log(Level.INFO, "Living record added for Customer ID: {0}", newCustomerID);
                                    isAdded = true;
                                } else {
                                    throw new SQLException("Adding Living record failed.");
                                }
                            }
                        } else {
                            throw new SQLException("Adding customer failed, no ID obtained.");
                        }
                    }
                } else {
                    LOGGER.log(Level.WARNING, "No customer was added.");
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error adding customer and living record.", e);
        } finally {
            DBContext.closeConnection(conn);
        }

        return isAdded;
    }

    public static void main(String[] args) {
        CustomerDAO dao = new CustomerDAO();
        Customer testCustomer = dao.getAllInformationCustomer("khang123", "123");
        System.out.println(testCustomer);

        String pass = UtilHashPass.EncodePassword("bb588SXf");
        System.out.println("pass hashed: " + pass);

    }
}
