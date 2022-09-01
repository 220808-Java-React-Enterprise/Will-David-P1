package com.revature.rs.models;

public class User {
    private String uID;
    private String uName, email, password, first, last;
    private boolean isActive;
    private String role;

    public User(){

    }

    public User(String uID, String uName, String email, String password, String first, String last, boolean isActive, String role) {
        this.uID = uID;
        this.uName = uName;
        this.email = email;
        this.password = password;
        this.first = first;
        this.last = last;
        this.isActive = isActive;
        this.role = role;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uID='" + uID + '\'' +
                ", uName='" + uName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", isActive=" + isActive +
                ", role='" + role + '\'' +
                '}';
    }
}


