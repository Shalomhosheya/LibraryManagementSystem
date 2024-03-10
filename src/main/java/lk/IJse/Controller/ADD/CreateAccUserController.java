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
import java.util.regex.Pattern;

public class CreateAccUserController {

    public TextField userForm_id;
    public TextField userForm_name;
    public TextField userForm_password;
    public TextField userForm_email;
    public AnchorPane rootNode;

    public void addBn(ActionEvent actionEvent) {
        boolean check = Regex();
        if (check){
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
                    Parent parent = FXMLLoader.load(getClass().getResource("/View2/LoginFormUser.fxml"));
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
    }
    public boolean Regex(){
        String id = userForm_id.getText();
        boolean idMatch = Pattern.matches("[U]\\d{3,}",id);
        if (!idMatch) {
            new Alert(Alert.AlertType.ERROR, "Error in id format").show();
            return false;
        }
        String emsil= userForm_email.getText();
        boolean emailmatch=Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",emsil);
        if (!emailmatch){
            new Alert(Alert.AlertType.ERROR, "Error in email format...").show();
            return false;
        }
        String name = userForm_name.getText();
        boolean nameMAtch = Pattern.matches("[A-za-z\\s]{4,}",name);
        if (!nameMAtch) {
            new Alert(Alert.AlertType.ERROR,"invalid name").show();
            return false;
        }
        return idMatch;
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
