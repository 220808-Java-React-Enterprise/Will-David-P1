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

    public List<ERSReimbursements> getAllByRId(String id) {
        return rDAO.getAllByReimID(id);
    }

    public void updateStatus(ERSReimbursements p){
        rDAO.update(p);

    }
    public List<ERSReimbursements> listAllByStatus(String p){
        return rDAO.getAllByStatusID(p);
    }
    public List<ERSReimbursements> listAllByType(String p){
        return rDAO.getAllByTypeID(p);
    }
    public List<ERSReimbursements> listAllReimbursements(){
        return rDAO.getAll();
    }
    public void deleteDeniedRequests(String p){
        rDAO.delete(p);
    }
    public List<ERSReimbursements> getAllByUserID(String p){
        return rDAO.getAllByAuthorID(p);
    }
}
