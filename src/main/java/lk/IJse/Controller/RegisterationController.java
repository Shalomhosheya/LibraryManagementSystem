package lk.IJse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import lk.IJse.Module.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;



public class RegisterationController {
    public TextField Reg_txt_PAssword;
    public TextField Reg_txt_username;
    public TextField Reg_txt_Email;
    public AnchorPane rootNode;
    public TextField Reg_txt_ID;

    public void Set_up(ActionEvent actionEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        User user = new User();
        user.setId(Reg_txt_ID.getText());
        user.setName(Reg_txt_username.getText());
        user.setPassword(Reg_txt_PAssword.getText());
        user.setEmail(Reg_txt_Email.getText());

        session.save(user);


       transaction.commit();
       session.close();

        if (user.getId() != null) {
            try {
                new Alert(Alert.AlertType.INFORMATION,"Registeration SuccessFull").show();
                Parent parent = FXMLLoader.load(getClass().getResource("/View/loginForm.fxml"));
                Stage stage = (Stage) rootNode.getScene().getWindow();
                Scene scene = new Scene(parent);
                stage.setTitle("Login Form");
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            new Alert(Alert.AlertType.ERROR,"Error in entered Data").show();
        }
    }

    public void BacktoLogin(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/loginForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}
