package lk.IJse.Module;

import jakarta.persistence.*;

@Entity
public class Books {

    @Id
    private String bookId;
    private String title;
    private String authorName;
    private String genreType;
    private String status;



    public Books() {

    }

    public String getBookId() {
        return bookId;
    }

    public String setBookId(String bookId) {
        this.bookId = bookId;
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String setTitle(String title) {
        this.title = title;
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String setAuthorName(String authorName) {
        this.authorName = authorName;
        return authorName;
    }

    public String getGenreType() {
        return genreType;
    }

    public String setGenreType(String genreType) {
        this.genreType = genreType;
        return genreType;
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        this.status = status;
        return status;
    }
}
