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
import org.hibernate.query.Query;

import java.io.IOException;

public class loginFormController {
    public AnchorPane rootNode;
    public TextField lg_nameTxt;
    public TextField lg_passwordTxt;

    public void Hyperlink_CreateACC(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/Registerations.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Registeration Form");
        stage.setScene(scene);
        stage.show();

    }

    public void login(ActionEvent actionEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String name = lg_nameTxt.getText();
            String password = lg_passwordTxt.getText();

            Query<User> query = session.createQuery("FROM User WHERE name = :name AND password = :password", User.class);
            query.setParameter("name", name);
            query.setParameter("password", password);

            User user = query.uniqueResult();

            if (user != null) {
                new Alert(Alert.AlertType.INFORMATION,"Login Successfull").show();
                Parent parent = FXMLLoader.load(getClass().getResource("/View/mainform.fxml"));
                Stage stage = (Stage) rootNode.getScene().getWindow();
                Scene scene = new Scene(parent);
                stage.setTitle("Main Form");
                stage.setScene(scene);
                stage.show();

            } else {
                new Alert(Alert.AlertType.INFORMATION,"Login failed. Invalid credentials.").show();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
