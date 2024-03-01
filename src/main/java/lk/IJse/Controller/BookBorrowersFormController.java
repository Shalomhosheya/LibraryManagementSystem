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
import lk.IJse.Dto.DataDto;
import lk.IJse.Module.Books;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import lk.IJse.Module.User;
import lk.IJse.Module.bookDetail;
import lk.IJse.Module.Borrowers;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class BookBorrowersFormController {

    public TableColumn book_Date_table_BID;
    public TableColumn book_Date_table_Handover;
    public TableColumn book_Date_table_borrowDate;
    public TableView book_Date_tablr;
    public TableColumn book_Detail_Table_Name;
    public TableColumn book_Detail_Table_Genre;
    public TableColumn book_Detail_Table_title;
    public TableView book_Detail_Table;
    public DatePicker book_HandDate;
    public DatePicker book_borrowDate;
    public ComboBox book_id_comboBox;
    public AnchorPane rootNode;
    public Label book_UserID;

    public User user = new User();
    public loginFormController loginFormController;
    public Query<Object> query2;

    public DataDto dataDto = new DataDto();
    public Books books = new Books();
    public TextField book_borrowID_txt;
    private ObservableList<Object[]> bookDetailData = FXCollections.observableArrayList();
    private ObservableList<bookDetail> bookDetailData1 = FXCollections.observableArrayList();



    List<String> bookIds;
    String title;
    String genre;
    String authorName;


    BookManagementFormController bookManagementFormController = new BookManagementFormController();


    public void bookIDSender(){
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery("SELECT b.bookId FROM Books b", String.class);
        bookIds = query.list();

        System.out.println(bookIds);

        book_id_comboBox.getItems().clear();
        book_id_comboBox.getItems().addAll(bookIds);


        transaction.commit();
        session.close();
    }


    public void backToMenu(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.setScene(scene);
        stage.show();
    }


    public void userIDSetup() {
        String name1 = dataDto.name;
        name1=name1.substring(0,1).toUpperCase()+name1.substring(1);
        book_UserID.setText(name1);
    }




    public void bookIDRecongnosied(MouseEvent mouseEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery("SELECT b.bookId FROM Books b", String.class);
         bookIds = query.list();

        // Check if an item is selected in the ComboBox
        if (book_id_comboBox.getSelectionModel().getSelectedIndex() != -1) {
            // Get the selected index from the ComboBox
            int selectedIndex = book_id_comboBox.getSelectionModel().getSelectedIndex();

            // Check if the selected index is within the bounds of the bookIds list
            if (selectedIndex >= 0 && selectedIndex < bookIds.size()) {
                // Get the corresponding book ID
                String selectedBookId = bookIds.get(selectedIndex);

                // Set the text of book_borrowID_txt to the selected book ID
                book_borrowID_txt.setText(selectedBookId);
            } else {
                System.out.println("Invalid index selected.");
            }
        } else {
            System.out.println("No item selected in the ComboBox.");
        }

        transaction.commit();
        session.close();
    }



    public void searchProceed(ActionEvent actionEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        // Check if an item is selected in the ComboBox
        if (book_id_comboBox.getSelectionModel().getSelectedIndex() != -1) {
            // Get the selected index from the ComboBox
            int selectedIndex = book_id_comboBox.getSelectionModel().getSelectedIndex();

            // Check if the selected index is within the bounds of the bookIds list
            if (selectedIndex >= 0 && selectedIndex < bookIds.size()) {
                // Get the corresponding book ID
                String selectedBookId = bookIds.get(selectedIndex);

                // Execute the query using the selected book ID
                Query<Object[]> query = session.createQuery("SELECT b.title, b.genreType, b.authorName FROM Books b WHERE b.bookId = :selectedBookId", Object[].class);
                query.setParameter("selectedBookId", selectedBookId);

                List<Object[]> bookCol = query.list();

                for (Object[] result : bookCol) {
                     title = (String) result[0];
                     genre = (String) result[1];
                     authorName = (String) result[2];

                    bookDetail bookDetail = new bookDetail(title, genre, authorName);
                    bookDetailData1.add(bookDetail);


                    // Process title, genre, and authorName as needed
                    System.out.println("Title: " + title + ", Genre: " + genre + ", Author: " + authorName);


                    // Clear existing data
                    bookDetailData.clear();

                    bookDetailData.add(result);
                }
            } else {
                System.out.println("Invalid index selected.");
            }
        } else {
            System.out.println("No item selected in the ComboBox.");
        }

        transaction.commit();
        session.close();
    }
    public void Borrow(ActionEvent actionEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        Borrowers borrowers = new Borrowers();
        User user1 = new User();
        String UserID = dataDto.userId;

        user1.setId(UserID);

        // Assuming book_borrowID_txt contains the book ID, extract its text
        String bookId = book_borrowID_txt.getText();

        // Retrieve the Books entity using the extracted book ID
        Books book = session.get(Books.class, bookId);

        borrowers.setBook(book);
        borrowers.setUser(user1);

        // Inserting current date and time for borrow date
        LocalDateTime borrowDate = LocalDateTime.now();
        borrowers.setBdate(borrowDate);

        // Inserting current date and time for hand date
        LocalDateTime handDate = LocalDateTime.now().plusDays(7); // assuming you want to add 7 days
        borrowers.setHDate(handDate);

        // Check if book_borrowDate and book_HandDate are not null
        if (book_borrowDate.getValue() != null) {
            borrowDate = book_borrowDate.getValue().atStartOfDay();
            borrowers.setBdate(borrowDate);
        }

        if (book_HandDate.getValue() != null) {
            handDate = book_HandDate.getValue().atStartOfDay();
            borrowers.setHDate(handDate);
        }

        // Handle other validations if needed

        if (!UserID.isEmpty() && !bookId.isEmpty() && borrowDate != null && handDate != null) {
            new Alert(Alert.AlertType.INFORMATION,"THANK YOU FOR BORROWING BOOKS..❌PLEASE RETURN BOOKS ON TIME❌").show();
            

            session.save(borrowers);
            transaction.commit();
            session.close();

        } else {
            System.out.println("Please fill in all the required fields.");
        }
    }





    public void initialize() {
        userIDSetup();
        bookIDSender();
        book_Detail_Table.setItems(bookDetailData1);

        book_Detail_Table_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        book_Detail_Table_Genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        book_Detail_Table_Name.setCellValueFactory(new PropertyValueFactory<>("authorName"));
    }

}
