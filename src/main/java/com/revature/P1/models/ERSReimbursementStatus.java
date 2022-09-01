package com.revature.P1.models;

public class ERSReimbursementStatus {
    private String statusID, status;

    public ERSReimbursementStatus(){

    }

    public ERSReimbursementStatus(String statusID, String status) {
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
