/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.FinanceBuilding;

/**
 *
 * @author thang
 */
public class FinanceBuildingDAO extends DBContext {

    public List<FinanceBuilding> getAll() {
        List<FinanceBuilding> list = new ArrayList<>();

        try {
            String sql = "Select * from FinanceBuilding";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new FinanceBuilding(rs.getInt("buildingID"), rs.getInt("financeID")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertFinanceBuilding(int buildingId, int financeId) {
        try {
            String sql = "Insert into FinanceBuilding values(?,?)";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, buildingId);
            ps.setInt(2, financeId);

            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteFinanceBuilding(int buildingId, int financeId) {
        try {
            String sql = "DELETE FROM [dbo].[FinanceBuilding]\n"
                    + "      WHERE buildingId = ? AND financeId = ?";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, buildingId);
            ps.setInt(2, financeId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        FinanceBuildingDAO fdao = new FinanceBuildingDAO();
//        fdao.insertFinanceBuilding(1, 2);
//        fdao.deleteFinanceBuilding(1, 1);
        System.out.println(fdao.getAll());
    }
}
