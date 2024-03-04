package lk.IJse.Module;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Branch {
    @Id
    private String branch_ID;
    private String branchADD;
    private int BooksQuantity;
    private String status;

    public Branch() {
    }

    public String getBranch_ID() {
        return branch_ID;
    }

    public void setBranch_ID(String branch_ID) {
        this.branch_ID = branch_ID;
    }

    public String getBranchADD() {
        return branchADD;
    }

    public void setBranchADD(String branchADD) {
        this.branchADD = branchADD;
    }

    public int getBooksQuantity() {
        return BooksQuantity;
    }

    public void setBooksQuantity(int booksQuantity) {
        BooksQuantity = booksQuantity;
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        this.status = status;
        return status;
    }

}