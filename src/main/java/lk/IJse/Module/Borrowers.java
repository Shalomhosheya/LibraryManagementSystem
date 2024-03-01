package lk.IJse.Module;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Borrowers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use an appropriate strategy for your database
    private Long borrowingID;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "Bdate")
    private LocalDateTime Bdate;

    @Column(name = "HDate")
    private LocalDateTime HDate;

    // ... other methods

    public LocalDateTime getBdate() {
        return Bdate;
    }

    public void setBdate(LocalDateTime bdate) {
        Bdate = bdate;
    }

    public LocalDateTime getHDate() {
        return HDate;
    }

    public void setHDate(LocalDateTime HDate) {
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

