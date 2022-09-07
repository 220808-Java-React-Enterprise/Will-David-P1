package com.revature.P1.daos;

import com.revature.P1.models.ERSReimbursements;
import com.revature.P1.utils.custom_exceptions.InvalidSQLException;
import com.revature.P1.utils.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements CrudDAO<ERSReimbursements> {
    @Override
    public void save(ERSReimbursements obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO reimbursements (reimb_id, amount, submitted, resolved, description, receipt, payment_id, author_id, resolver_id, status_id, type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getReimID());
            ps.setInt(2, obj.getAmount());
            ps.setDate(3, obj.getSubmitted());
            ps.setDate(4, obj.getResolved());
            ps.setString(5, obj.getDescription());
            ps.setInt(6, obj.getReceipt());
            ps.setString(7, obj.getPaymentID());
            ps.setString(8, obj.getAuthorID());
            ps.setString(9, obj.getResolverID());
            ps.setString(10, obj.getStatusID());
            ps.setString(11, obj.getTypeID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<ERSReimbursements> getAllByReimID(String id) {
        List<ERSReimbursements> reimbursements = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE rid = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERSReimbursements reim = new ERSReimbursements(rs.getString("rID"), rs.getInt("amount"), rs.getDate("submitted"), rs.getDate("resolved"), rs.getString("description"), rs.getInt("receipt"), rs.getString("paymentID"), rs.getString("authorID"), rs.getString("resolverID"), rs.getString("statusID"), rs.getString("typeID"));
                reimbursements.add(reim);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return reimbursements;
    }

    public List<ERSReimbursements> getAllByStatusID(String id) {
        List<ERSReimbursements> reimbursements = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE statusID = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERSReimbursements reim = new ERSReimbursements(rs.getString("rID"), rs.getInt("amount"), rs.getDate("submitted"), rs.getDate("resolved"), rs.getString("description"), rs.getInt("receipt"), rs.getString("paymentID"), rs.getString("authorID"), rs.getString("resolverID"), rs.getString("statusID"), rs.getString("typeID"));
                reimbursements.add(reim);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return reimbursements;
    }

    public List<ERSReimbursements> getAllByTypeID(String id) {
        List<ERSReimbursements> reimbursements = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE typeID = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERSReimbursements reim = new ERSReimbursements(rs.getString("rID"), rs.getInt("amount"), rs.getDate("submitted"), rs.getDate("resolved"), rs.getString("description"), rs.getInt("receipt"), rs.getString("paymentID"), rs.getString("authorID"), rs.getString("resolverID"), rs.getString("statusID"), rs.getString("typeID"));
                reimbursements.add(reim);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return reimbursements;
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
        return null;
    }
}
