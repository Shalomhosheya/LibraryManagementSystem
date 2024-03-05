package lk.IJse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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
    public ObservableList<Books> observableBooksList;
    public String Arr[]={"Available","Not Available"};

    public ObservableList<String>status = FXCollections.observableArrayList(Arr);
    public void EnterData(ActionEvent actionEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        try {
            Books books = new Books();

           String id= books.setBookId(bookid_txt.getText());
           String title= books.setTitle(title_txt.getText());
           String name=  books.setAuthorName(author_txt.getText());
           String genre =books.setGenreType(genretxt.getText());

            String selectedStatus = combo_statusbooks.getValue();
           String statues= books.setStatus(selectedStatus);


            if (!id.isEmpty()&&!title.isEmpty()&&!name.isEmpty()&&!genre.isEmpty()&&!statues.isEmpty()){

                session.save(books);
                transaction.commit();


                // Refresh the TableView after adding a new record
                refreshTableView();
                new Alert(Alert.AlertType.INFORMATION,"Data Added").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Some Fields  are Empty").show();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            new Alert(Alert.AlertType.ERROR,"Duplicate ID was Entered").show();

        } finally {
            session.close();
        }
    }

    // Method to refresh the TableView with updated data from the database


    public void refreshTableView() {
        Session session = factoryConfiguration.getInstance().getSessionFactory();

        try {
            // Fetch all books from the database
            Query<Books> query = session.createQuery("FROM Books", Books.class);
            List<Books> booksList = query.list();

            // Update the existing ObservableList with the fetched data
            observableBooksList.setAll(booksList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public void bsckBtn(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.setScene(scene);
        stage.show();
    }

    public void removebookmangement(ActionEvent actionEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        // Assuming you want to delete a specific book by ID
        String bookIdToDelete = bookid_txt.getText();  // Replace with the actual book ID

        Query query = session.createQuery("delete from Books b where b.bookId = :bookId");
        query.setParameter("bookId", bookIdToDelete);

        int rowCount = query.executeUpdate();  // This returns the number of rows affected
        refreshTableView();
        transaction.commit();
        session.close();
    }

    public void updatebookmangement(ActionEvent actionEvent) {
       Session session = factoryConfiguration.getInstance().getSessionFactory();
       Transaction transaction = session.beginTransaction();
       //Books books =  new Books();

        Books books = session.get(Books.class,bookid_txt.getText());
        String selectedStatus = combo_statusbooks.getValue();
        String statues;

        if (books != null){
            books.setAuthorName(author_txt.getText());
            books.setTitle(title_txt.getText());
            books.setGenreType(genretxt.getText());
            statues= books.setStatus(selectedStatus);

            session.update(books);
            refreshTableView();
        }else {
            new Alert(Alert.AlertType.ERROR,"Update FAIl.....REcheck the Crendentials");
        }

       refreshTableView();
       transaction.commit();
       session.close();


    }


    public void bookdataSelect(MouseEvent mouseEvent) {
        Books selectedBook = (Books) tableBookBrowers.getSelectionModel().getSelectedItem();

        if (selectedBook != null) {
            bookid_txt.setText(selectedBook.getBookId());
            title_txt.setText(selectedBook.getTitle());
            author_txt.setText(selectedBook.getAuthorName());
            genretxt.setText(selectedBook.getGenreType());

            // Select the status in the ComboBox
            combo_statusbooks.getSelectionModel().select(selectedBook.getStatus());
        }
    }

    public void clearbookmangement(ActionEvent actionEvent) {
        bookid_txt.clear();
        title_txt.clear();
        author_txt.clear();
        genretxt.clear();
        combo_statusbooks.setVisibleRowCount(0);
    }
    public void initialize(){
        observableBooksList = FXCollections.observableArrayList();
        combo_statusbooks.setItems(status);

        tableBookBrowers_col_ID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        tableBookBrowers_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        tableBookBrowers_col_Author.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        tableBookBrowers_col_Genre.setCellValueFactory(new PropertyValueFactory<>("genreType"));
        tableBookBrowers_col_AvalaibleStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableBookBrowers.setItems(observableBooksList);
    }

}
