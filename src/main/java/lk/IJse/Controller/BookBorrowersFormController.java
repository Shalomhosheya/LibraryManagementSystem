package lk.IJse.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Dto.BookDto;
import lk.IJse.Dto.DataDto;
import lk.IJse.Module.Books;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import lk.IJse.Module.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
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
    public ObservableList<String> observableList;
    BookManagementFormController bookManagementFormController = new BookManagementFormController();


    public void bookIDSender(){
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery("SELECT b.bookId FROM Books b", String.class);
        List<String> bookIds = query.list();

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

    public void initialize(){
     userIDSetup();
     bookIDSender();
     
    }


}
