package lk.IJse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Transaction_formController {
    public TableView TransTableView;
    public TableColumn B_IDCol;
    public TableColumn U_IDCol;
    public TableColumn B_NameCol;
    public TableColumn BranchNameCol;
    public TableColumn U_nameCol;
    public TableColumn BorrowD_col;
    public TableColumn H_dateCol;
    public AnchorPane rootNode;

   

    public void BacktoMenu(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainform.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void Refresh(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/transaction_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}
