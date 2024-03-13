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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterationController {
    public TextField Reg_txt_PAssword;
    public TextField Reg_txt_username;
    public TextField Reg_txt_Email;
    public AnchorPane rootNode;
    public TextField Reg_txt_ID;
    public void Set_up(ActionEvent actionEvent) {
        boolean check = Regex();
        if (check){
            Session session = factoryConfiguration.getInstance().getSessionFactory();
            Transaction transaction = session.beginTransaction();
            User user = new User();

            String id = user.setId(Reg_txt_ID.getText());
            String name = user.setName(Reg_txt_username.getText());
            String password = user.setPassword(Reg_txt_PAssword.getText());
            String email = user.setEmail(Reg_txt_Email.getText());

            if (!id.isEmpty() && !name.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
                session.save(user);
            } else {
                new Alert(Alert.AlertType.ERROR, "Fill all the Fields").show();
            }


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
    public boolean Regex(){
        String id = Reg_txt_ID.getText();
        boolean idMatch = Pattern.matches("[A]\\d{3,}",id);
        if (!idMatch) {
            new Alert(Alert.AlertType.ERROR, "Error in id format").show();
            return false;
        }
        String emsil= Reg_txt_Email.getText();
        boolean emailmatch=Pattern.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",emsil);
        if (!emailmatch){
            new Alert(Alert.AlertType.ERROR, "Error in email format...").show();
            return false;
        }
        String name = Reg_txt_username.getText();
        boolean nameMAtch = Pattern.matches("[A-za-z\\s]{4,}",name);
        if (!nameMAtch) {
            new Alert(Alert.AlertType.ERROR,"invalid name").show();
            return false;
        }

        return idMatch;
    }
    public void initialize() {

    }

}
