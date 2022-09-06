package com.revature.P1.daos;
import com.revature.P1.models.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ReimbursementDAO implements CrudDAO<ERSReimbursements> {
    @Override
    public void save(ERSReimbursements obj) {
    }
    @Override
    public void update(ERSReimbursements obj) {

    }
    @Override
    public void delete(String id) {

    }
    @Override
    public ERSReimbursements getById(String id) {
        return null;
    }
    @Override
    public List<ERSReimbursements> getAll() {
        List<ERSReimbursements> restaurants = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM restaurants");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERSReimbursements reim = new ERSReimbursements(rs.getString("id"), rs.getString("name"), rs.getString("street"), rs.getString("city"), rs.getString("state"), rs.getString("zipcode"));
                restaurants.add(reim);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return restaurants;
    }
}
