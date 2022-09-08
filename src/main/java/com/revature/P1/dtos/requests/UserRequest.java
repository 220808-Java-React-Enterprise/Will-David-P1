package com.revature.P1.dtos.requests;

public class UserRequest {
    private String uID;
    private String role;

    public UserRequest(){

    }

    public UserRequest(String uID, String role) {
        this.uID = uID;
        this.role = role;
    }

    public String getuID() {

        return uID;
    }

    public void setuID(String uID) {

        this.uID = uID;
    }

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "uID='" + uID + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
