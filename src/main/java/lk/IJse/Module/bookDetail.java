package lk.IJse.Module;

import javafx.beans.property.SimpleStringProperty;

public class bookDetail {
    private final SimpleStringProperty title;
    private final SimpleStringProperty genre;
    private final SimpleStringProperty authorName;

    public bookDetail(String title, String genre, String authorName) {
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.authorName = new SimpleStringProperty(authorName);
    }

    // Getters
    public String getTitle() {
        return title.get();
    }

    public String getGenre() {
        return genre.get();
    }

    public String getAuthorName() {
        return authorName.get();
    }
}
