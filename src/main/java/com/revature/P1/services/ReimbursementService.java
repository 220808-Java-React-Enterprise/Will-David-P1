package com.revature.P1.services;

import com.revature.P1.daos.ReimbursementDAO;
import com.revature.P1.models.ERSReimbursements;

import java.util.List;

public class ReimbursementService {

    private final ReimbursementDAO rDAO;

    public ReimbursementService(ReimbursementDAO rDAO) {
        this.rDAO = rDAO;
    }

    public void updateStatus(String reimID, String resolver, String status){
        rDAO.updateStatus(reimID, resolver, status);

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
    public List<ERSReimbursements> getAllByUserID(String id){
        return rDAO.getAllByUserID(id);
    }
    public void reimSave(ERSReimbursements p) {
        rDAO.save(p);
    }
}
