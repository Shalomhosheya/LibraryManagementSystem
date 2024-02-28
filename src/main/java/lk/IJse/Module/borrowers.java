package lk.IJse.Module;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class borrowers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use an appropriate strategy for your database
    private Long borrowingID;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date Bdate;

    private Date HDate;

    public borrowers() {

    }

    public Date getBdate() {
        return Bdate;
    }

    public void setBdate(Date bdate) {
        Bdate = bdate;
    }

    public Date getHDate() {
        return HDate;
    }

    public void setHDate(Date HDate) {
        this.HDate = HDate;
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

}

