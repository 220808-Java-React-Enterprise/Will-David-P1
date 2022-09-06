package com.revature.P1.services;
import com.revature.P1.daos.*;
import com.revature.P1.models.*;
import java.util.*;
public class ReimbursementService {
    private final ReimbursementDAO rDAO;

    public ReimbursementService(ReimbursementDAO rDAO) {
        this.rDAO = rDAO;
    }
    public void saveReimbursement(ERSReimbursements r) {
        rDAO.save(r);
    }

    public List<ERSReimbursements> getAllReviewsByRestaurantId(String id) {
        return rDAO.getAllByReimID(id);
    }
}
