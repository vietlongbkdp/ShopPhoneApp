package services;

import daos.BranchDAO;
import models.Branch;


import java.util.List;

public class BranchService {
    private final BranchDAO branchDAO;

    public BranchService( ) {
        branchDAO = new BranchDAO();
    }
    public List<Branch> getBranchs(){
        return branchDAO.fillAll();
    }
    public Branch getBranch(int id){
        return branchDAO.findById(id);
    }
}
