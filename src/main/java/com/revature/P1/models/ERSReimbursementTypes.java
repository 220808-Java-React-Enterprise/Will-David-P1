package com.revature.P1.models;

public class ERSReimbursementTypes {
    private String typeID, type;

    public ERSReimbursementTypes(){

    }

    public ERSReimbursementTypes(String typeID, String type) {
        this.typeID = typeID;
        this.type = type;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ReimType{" +
                "typeID='" + typeID + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
