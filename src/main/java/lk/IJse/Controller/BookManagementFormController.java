package lk.IJse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.IJse.Module.Books;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class BookManagementFormController {

    public AnchorPane rootNode;
    public AnchorPane Anchor1;
    public TableView tableBookBrowers;
    public TableColumn tableBookBrowers_col_ID;
    public TableColumn tableBookBrowers_col_title;
    public TableColumn tableBookBrowers_col_Author;
    public TableColumn tableBookBrowers_col_Genre;
    public TableColumn tableBookBrowers_col_AvalaibleStatus;
    public AnchorPane Anchor11;
    public TextField bookid_txt;
    public TextField title_txt;
    public TextField author_txt;
    public TextField genretxt;
    public ComboBox<String> combo_statusbooks;

    public String Arr[]={"Available","Not Available"};

    public void EnterData(ActionEvent actionEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        try {
            Books books = new Books();

            books.setBookId(bookid_txt.getText());
            books.setTitle(title_txt.getText());
            books.setAuthorName(author_txt.getText());
            books.setGenreType(genretxt.getText());

            String selectedStatus = combo_statusbooks.getValue();
            books.setStatus(selectedStatus);

            session.save(books);
            transaction.commit();

            // Refresh the TableView after adding a new record
            refreshTableView();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // Method to refresh the TableView with updated data from the database
    private void refreshTableView() {
        Session session = factoryConfiguration.getInstance().getSessionFactory();

        try {
            // Fetch all books from the database
            Query<Books> query = session.createQuery("FROM Books", Books.class);
            List<Books> booksList = query.list();

            // Create an ObservableList from the fetched data
            ObservableList<Books> observableBooksList = FXCollections.observableArrayList(booksList);

            // Set the items of the TableView
            tableBookBrowers.setItems(observableBooksList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    public void initialize(){
        ObservableList<String>status = FXCollections.observableArrayList(Arr);
        combo_statusbooks.setItems(status);

        tableBookBrowers_col_ID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tableBookBrowers_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableBookBrowers_col_Author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        tableBookBrowers_col_Genre.setCellValueFactory(new PropertyValueFactory<>("genreType"));
        tableBookBrowers_col_AvalaibleStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    }

    public void bsckBtn(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.setScene(scene);
        stage.show();
    }
}
