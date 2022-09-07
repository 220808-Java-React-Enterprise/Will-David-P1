package com.revature.P1.daos;

import com.revature.P1.models.*;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.P1.utils.custom_exceptions.InvalidSQLException;
import com.revature.P1.utils.database.ConnectionFactory;

public class ReimbursementDAO implements CrudDAO<ERSReimbursements> {
    @Override
    public void save(ERSReimbursements obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (rID, amount, submitted, resolved, receipt, paymentID, authorID, resolverID, statusID, typeID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getReimID());
            ps.setInt(2, obj.getAmount());
            ps.setDate(3, obj.getSubmitted());
            ps.setDate(4, obj.getResolved());
            ps.setBlob(5, obj.getReceipt());
            ps.setString(6, obj.getPaymentID());
            ps.setString(7, obj.getAuthorID());
            ps.setString(8, obj.getResolverID());
            ps.setString(9, obj.getStatusID());
            ps.setString(10, obj.getTypeID());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }
    }

    @Override
    public void update(ERSReimbursements obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE reimbursements SET statusID = ? WHERE rID = ?");
            ps.setString(1, obj.getStatusID());
            ps.setString(2, obj.getReimID());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred trying to update the table");
        }

    }

    @Override
    public void delete(String id) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE * FROM reimbursements WHERE statusID = ?");
            ps.executeQuery();



        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to delete from the database.");
        }


    }

    @Override
    public ERSReimbursements getById(String id) {
        return null;
    }

    @Override
    public List<ERSReimbursements> getAll() {
        List<ERSReimbursements> reimbursements = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERSReimbursements reim = new ERSReimbursements(rs.getString("rID"), rs.getInt("amount"), rs.getDate("submitted"), rs.getDate("resolved"), rs.getBlob("receipt"), rs.getString("paymentID"), rs.getString("authorID"), rs.getString("resolverID"), rs.getString("statusID"), rs.getString("typeID"));
                reimbursements.add(reim);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when trying to save to the database.");
        }

        return reimbursements;
    }
    public List<ERSReimbursements> getAllByReimID(String id) {
        List<ERSReimbursements> reimbursements = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE rid = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERSReimbursements reim = new ERSReimbursements(rs.getString("rID"), rs.getInt("amount"), rs.getDate("submitted"), rs.getDate("resolved"), rs.getBlob("receipt"), rs.getString("paymentID"), rs.getString("authorID"), rs.getString("resolverID"), rs.getString("statusID"), rs.getString("typeID"));
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
                ERSReimbursements reim = new ERSReimbursements(rs.getString("rID"), rs.getInt("amount"), rs.getDate("submitted"), rs.getDate("resolved"), rs.getBlob("receipt"), rs.getString("paymentID"), rs.getString("authorID"), rs.getString("resolverID"), rs.getString("statusID"), rs.getString("typeID"));
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
                ERSReimbursements reim = new ERSReimbursements(rs.getString("rID"), rs.getInt("amount"), rs.getDate("submitted"), rs.getDate("resolved"), rs.getBlob("receipt"), rs.getString("paymentID"), rs.getString("authorID"), rs.getString("resolverID"), rs.getString("statusID"), rs.getString("typeID"));
                reimbursements.add(reim);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return reimbursements;
    }

    public List<ERSReimbursements> getAllByAuthorID(String id) {
        List<ERSReimbursements> reimbursements = new ArrayList<>();

        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reimbursements WHERE authorID = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ERSReimbursements reim = new ERSReimbursements(rs.getString("rID"), rs.getInt("amount"), rs.getDate("submitted"), rs.getDate("resolved"), rs.getBlob("receipt"), rs.getString("paymentID"), rs.getString("authorID"), rs.getString("resolverID"), rs.getString("statusID"), rs.getString("typeID"));
                reimbursements.add(reim);
            }
        } catch (SQLException e) {
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

        return reimbursements;
    }
}

