package com.revature.P1.dtos.requests;

public class NewUserRequest {
    private String username;

    private String email;
    private String password1;
    private String password2;

    private String first;

    private String last;

    private boolean isActive;

    private String role;

    public NewUserRequest() {

    }

    public NewUserRequest(String username, String email, String password1, String password2, String first, String last, boolean isActive, String role) {
        this.username = username;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.first = first;
        this.last = last;
        this.isActive = isActive;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password1='" + password1 + '\'' +
                ", password2='" + password2 + '\'' +
                ", first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", isActive=" + isActive +
                ", role='" + role + '\'' +
                '}';
    }
}
