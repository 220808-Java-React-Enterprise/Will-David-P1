package com.revature.rs.models;

public class ReimStatus {
    private String statusID, status;

    public ReimStatus(){

    }

    public ReimStatus(String statusID, String status) {
        this.statusID = statusID;
        this.status = status;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReimStatus{" +
                "statusID='" + statusID + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
