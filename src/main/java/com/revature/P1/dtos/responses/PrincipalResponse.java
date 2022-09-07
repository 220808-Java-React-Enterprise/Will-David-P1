package com.revature.P1.dtos.responses;

public class PrincipalResponse {
    private String uID;
    private String uName, email, first, last;
    private boolean isActive;
    private String role;

    public PrincipalResponse() {

    }

    public PrincipalResponse(String uID, String uName, String email, String first, String last, boolean isActive, String role) {
        this.uID = uID;
        this.uName = uName;
        this.email = email;
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
        return "PrincipalResponse{" +
                "uID='" + uID + '\'' +
                ", uName='" + uName + '\'' +
                ", email='" + email + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", isActive=" + isActive +
                ", role='" + role + '\'' +
                '}';
    }
}
