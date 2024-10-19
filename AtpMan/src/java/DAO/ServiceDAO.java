/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.*;
import utils.DBContext;

/**
 *
 * @author thang
 */
public class ServiceDAO {

    Connection connection = null;

    public List<Service> getAllService() throws ClassNotFoundException {
        List<Service> list = new ArrayList<>();
        String sql = "select * from Service";
        try {
            connection = DBContext.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setServiceId(rs.getInt(1));
                service.setName(rs.getString(2));
                service.setType(rs.getString(3));
                service.setDescription(rs.getString(4));
                service.setFee(rs.getBigDecimal(5));
                list.add(service);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Service> getAll() {
        List<Service> list = new ArrayList<>();
        try {

            String sql = "Select * from Service";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Service(rs.getInt("serviceID"), rs.getString("name"), rs.getString("type"), rs.getBigDecimal("fee"), rs.getString("description"), rs.getString("img"), rs.getString("icon")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Service findById(int id) {
        List<Service> list = getAll();
        for (Service service : list) {
            if (service.getServiceId() == id) {
                return service;
            }
        }
        return null;
    }

    public List<ServiceType> getAllType() {
        List<ServiceType> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    type, \n"
                + "    COUNT(*) AS totalServices, \n"
                + "    SUM(fee) AS totalFee\n"
                + "FROM \n"
                + "    Service\n"
                + "GROUP BY \n"
                + "    type";

        try {
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ServiceType(rs.getString(1), rs.getInt(2), rs.getInt(3)));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Service> getServiceByType(String type, String search, String orderBy, int page, int recordsPerPage) {
        List<Service> listType = new ArrayList<>();
        try {
            int offset = (page - 1) * recordsPerPage;
            StringBuilder sql = new StringBuilder("SELECT * FROM Service WHERE 1 = 1 ");

            // Nếu type không rỗng và không phải là "All", thêm điều kiện lọc theo type
            if (type != null && !type.isEmpty() && !"All".equals(type)) {
                sql.append("AND type = ? ");
            }

            // Kiểm tra nếu có từ khóa tìm kiếm
            if (search != null && !search.isEmpty()) {
                sql.append("AND name COLLATE Latin1_General_CI_AI LIKE ? ");
            }

            // Thêm điều kiện sắp xếp
            if ("asc".equalsIgnoreCase(orderBy)) {
                sql.append("ORDER BY fee ASC ");
            } else if ("desc".equalsIgnoreCase(orderBy)) {
                sql.append("ORDER BY fee DESC ");
            } else {
                sql.append("ORDER BY serviceID ");
            }
            // Thêm giới hạn phân trang
            sql.append("OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql.toString());

            // Thiết lập giá trị cho các tham số
            int paramIndex = 1;

            if (type != null && !type.isEmpty() && !"".equals(type)) {
                ps.setString(paramIndex++, type);
            }

            // Nếu có từ khóa tìm kiếm, thiết lập tham số cho nó
            if (search != null && !search.isEmpty()) {
                ps.setString(paramIndex++, "%" + search + "%");
            }

            ps.setInt(paramIndex++, offset);
            ps.setInt(paramIndex, recordsPerPage);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listType.add(new Service(
                        rs.getInt("serviceID"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getBigDecimal("fee"),
                        rs.getString("description"),
                        rs.getString("img"),
                        rs.getString("icon")
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listType;
    }

    public void insertService(String name, String type, BigDecimal fee, String description, String img, String icon) {
        try {
            String sql = "Insert into Service(name,type,fee,description,img,icon) values(?,?,?,?,?,?)";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setBigDecimal(3, fee);
            ps.setString(4, description);
            ps.setString(5, img);
            ps.setString(6, icon);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateService(int serviceId, String name, String type, BigDecimal fee, String description, String img, String icon) {
        try {
            String sql = "UPDATE [dbo].[Service]\n"
                    + "   SET [name] = ?\n"
                    + "      ,[type] = ?\n"
                    + "      ,[fee] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[img] = ?\n"
                    + "      ,[icon] = ?\n"
                    + " WHERE serviceID = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setBigDecimal(3, fee);
            ps.setString(4, description);
            ps.setString(5, img);
            ps.setString(6, icon);
            ps.setInt(7, serviceId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteService(int serviceId) {
        try {
            String sql = "DELETE FROM [dbo].[Service]\n"
                    + "      WHERE serviceID = ?";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, serviceId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Service> servicePaging(int page, int recordsPerPage) {
        List<Service> list = new ArrayList<>();
        try {
            int offset = (page - 1) * recordsPerPage;
            String sql = "select * from Service order by serviceID offset ? rows fetch next ? rows only";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, offset);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service();
                service.setServiceId(rs.getInt("serviceID"));
                service.setName(rs.getString("name"));
                service.setType(rs.getString("type"));
                service.setDescription(rs.getString("description"));
                service.setImg(rs.getString("img"));
                service.setIcon(rs.getString("icon"));
                service.setFee(rs.getBigDecimal("fee"));
                list.add(service);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int count(int recordsPerPage) {
        int totalPages = 0;
        try {
            String sql = "select count(*) from service";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int totalRecords = rs.getInt(1);
            totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        } catch (Exception e) {
            System.out.println(e);
        }
        return totalPages;
    }

    public int countActive(String type, String search, int recordsPerPage) {
        int totalPages = 0;
        try {
            // Xây dựng câu lệnh SQL động tùy thuộc vào type và name
            StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM Service WHERE 1=1");

            // Nếu type không phải "All", thêm điều kiện lọc theo type
            if (type != null && !type.equals("")) {
                sql.append(" AND type = ?");
            }

            // Nếu name không rỗng, thêm điều kiện lọc theo name
            if (search != null && !search.isEmpty()) {
                sql.append(" AND name COLLATE Latin1_General_CI_AI LIKE ?");
            }

            PreparedStatement ps = connection.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (type != null && !type.equals("")) {
                ps.setString(paramIndex++, type);
            }
            if (search != null && !search.isEmpty()) {
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

    public int totalService() {
        int total = 0;
        try {
            String sql = "select count(*) from service";
            connection = DBContext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }
    
    public int getTotalService(String type, String search, String orderBy) {
        ServiceDAO sdao = new ServiceDAO();
        List<Service> list = sdao.getServiceByType(type, search, orderBy, 1, sdao.totalService());
        return list.size();
    }

    public static String stripHtml(String input) {
        return input.replaceAll("\\<.*?\\>", ""); // Xóa tất cả các thẻ HTML
    }

    public static void main(String[] args) {
        ServiceDAO sdao = new ServiceDAO();


        List<Service> list = sdao.servicePaging(1, 25);
        for (Service elem : list) {
            System.out.println(elem);
        }
//        System.out.println(sdao.totalService());
    }
}
