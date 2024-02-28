package lk.IJse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Module.User;
import org.hibernate.query.Query;

import java.io.IOException;

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

    public void book_Take(javafx.event.ActionEvent actionEvent) {

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
     
    }

    public void USerIDSettingup(InputMethodEvent inputMethodEvent) {
    }
}
