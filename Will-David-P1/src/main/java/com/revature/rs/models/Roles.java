package com.revature.rs.models;

public class Roles {
    private String roleID, role;

    public Roles(){

    }

    public Roles(String roleID, String role) {
        this.roleID = roleID;
        this.role = role;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleID='" + roleID + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
