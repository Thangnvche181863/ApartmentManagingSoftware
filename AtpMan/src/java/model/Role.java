/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author WuanTun
 */
public class Role {
    private int roleID;
    private String role_name;
    private String roleAuthority;

    public Role() {
    }

    public Role(int roleID, String role_name, String roleAuthority) {
        this.roleID = roleID;
        this.role_name = role_name;
        this.roleAuthority = roleAuthority;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRoleAuthority() {
        return roleAuthority;
    }

    public void setRoleAuthority(String roleAuthority) {
        this.roleAuthority = roleAuthority;
    }

    @Override
    public String toString() {
        return "Role{" + "roleID=" + roleID + ", role_name=" + role_name + ", roleAuthority=" + roleAuthority + '}';
    }
    
    
}
