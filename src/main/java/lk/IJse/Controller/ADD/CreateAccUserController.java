package lk.IJse.Controller.ADD;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Module.Admin;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class CreateAccUserController {

    public TextField userForm_id;
    public TextField userForm_name;
    public TextField userForm_password;
    public TextField userForm_email;
    public AnchorPane rootNode;

    public void addBn(ActionEvent actionEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();
        Admin admin = new Admin();

        String id= admin.setId(userForm_id.getText());
        String name= admin.setName(userForm_name.getText());
        String password = admin.setPassword(userForm_password.getText());
        String email =admin.setEmail(userForm_email.getText());

        if (!id.isEmpty()&&!name.isEmpty()&&!password.isEmpty()&&!email.isEmpty()){
            session.save(admin);
        }else {
            new Alert(Alert.AlertType.ERROR,"Fill all the Fields").show();
        }

        transaction.commit();
        session.close();

        if (admin.getId() != null) {
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

    public void backBtn(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View2/LoginFormUser.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}
