package com.revature.P1.models;
import java.util.*;
import java.sql.Blob;
import java.sql.Date;
public class ERSReimbursements {
    private String reimID;
    private int amount;
    private Date submitted;
    private Date resolved;
    private Blob receipt;
    private String paymentID, authorID, resolverID, statusID, typeID;

    public ERSReimbursements(){

    }

    public ERSReimbursements(String reimID, int amount, Date submitted, Date resolved, Blob receipt, String paymentID, String authorID, String resolverID, String statusID, String typeID) {
        this.reimID = reimID;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.receipt = receipt;
        this.paymentID = paymentID;
        this.authorID = authorID;
        this.resolverID = resolverID;
        this.statusID = statusID;
        this.typeID = typeID;
    }

    public String getReimID() {
        return reimID;
    }

    public void setReimID(String reimID) {
        this.reimID = reimID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public Blob getReceipt() {
        return receipt;
    }

    public void setReceipt(Blob receipt) {
        this.receipt = receipt;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getAuthorID() {
        return authorID;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public String getResolverID() {
        return resolverID;
    }

    public void setResolverID(String resolverID) {
        this.resolverID = resolverID;
    }

    public String getStatusID() {
        return statusID;
    }

    public void setStatusID(String statusID) {
        this.statusID = statusID;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    @Override
    public String toString() {
        return "ERSReimbursements{" +
                "reimID='" + reimID + '\'' +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", receipt=" + receipt +
                ", paymentID='" + paymentID + '\'' +
                ", authorID='" + authorID + '\'' +
                ", resolverID='" + resolverID + '\'' +
                ", statusID='" + statusID + '\'' +
                ", typeID='" + typeID + '\'' +
                '}';
    }
}
