package lk.IJse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import lk.IJse.Module.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;

public class mainformController {
    public AnchorPane rootNode;
    public User user = new User();
    public BookBorrowersFormController bookBorrowersFormController = new BookBorrowersFormController();
    public BookManagementFormController bookManagementFormController = new BookManagementFormController();
    public loginFormController loginFormController;

    public void signout(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/loginForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void barrowersSec(ActionEvent actionEvent) throws IOException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/.fxml"));
        Parent parent = FXMLLoader.load(getClass().getResource("/View/BookBorrowersform.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("bookBarrow Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void transactionSec(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/transaction_form.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void branchSec(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/BranchForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void bookMangement(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/BookManagement.fxml"));
        Parent parent = loader.load();
        BookManagementFormController bookManagementController = loader.getController();
        bookManagementController.initialize();
        bookManagementController.refreshTableView(); // Refresh the TableView

        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.centerOnScreen();
        stage.maxHeightProperty();
        stage.setScene(scene);
        stage.show();
    }

}
