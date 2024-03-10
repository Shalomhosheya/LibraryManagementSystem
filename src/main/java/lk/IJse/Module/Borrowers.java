package lk.IJse.Module;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Borrowers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use an appropriate strategy for your database
    @Column(name = "borrowingID")
    private Long borrowingID;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "branch_ID")
    private Branch branch;


    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    @Column(name = "Bdate")
    private LocalDateTime Bdate;

    @Column(name = "HDate")
    private LocalDateTime HDate;
    private String bookId;
    private String userName;
    private String bookTitle;
    private String branchAddress;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Long getBorrowingID() {
        return borrowingID;
    }

    public void setBorrowingID(Long borrowingID) {
        this.borrowingID = borrowingID;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public LocalDateTime getBdate() {
        return Bdate;
    }

    public void setBdate(LocalDateTime Bdate) {
        this.Bdate = Bdate;
    }

    public LocalDateTime getHDate() {
        return HDate;
    }

    public void setHDate(LocalDateTime HDate) {
        this.HDate = HDate;
    }


    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchAddress() {
        return branchAddress;
    }
}
