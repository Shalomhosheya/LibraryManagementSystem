package lk.IJse.Controller.ADD;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Controller.BookBorrowersFormController;
import lk.IJse.Dto.UserDto;
import lk.IJse.Module.Admin;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import lk.IJse.Module.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;



public class UserLoginController {

    public AnchorPane rootNode;
    public Label Label1;
    public TextField lg_nameTxt;
    public Label Label11;
    public Label label2;
    public Label label21;
    public PasswordField lg_passwordTxt;

    public void Hyperlink_CreateACC(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View2/CreateACCUser.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void login(ActionEvent actionEvent) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        BookBorrowersFormController bookBorrowersFormController = null;
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String name = lg_nameTxt.getText();
            String password = lg_passwordTxt.getText();

            Query<Admin> query = session.createQuery("FROM Admin u WHERE u.name = :name AND u.password = :password", Admin.class);
            query.setParameter("name", name);
            query.setParameter("password", password);


            Admin admin = query.uniqueResult();

            Query<Admin> query2 = session.createQuery("FROM Admin u WHERE u.name = :name", Admin.class);
            query2.setParameter("name", name);

            Admin admin1=query2.uniqueResult() ;

            Query<User> query3 = session.createQuery("select u.id from Admin u where u.name = :name group by u.id", User.class);
            query3.setParameter("name", name);

            String user3 = String.valueOf(query3.uniqueResult());

            System.out.println(admin1);

            if (admin != null) {
                new Alert(Alert.AlertType.INFORMATION,"Login Successfull").show();
                Parent parent = FXMLLoader.load(getClass().getResource("/View2/UserMainForm.fxml"));
                Stage stage = (Stage) rootNode.getScene().getWindow();
                Scene scene = new Scene(parent);
                stage.setTitle("Main Form");
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
                System.out.println(admin.getName());
                UserDto.name = admin1.getName();
                UserDto.userId = String.valueOf(user3);

            } else {
                new Alert(Alert.AlertType.INFORMATION,"Login failed.").show();
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

    public void switchForm(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View2/LoginA&U.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
}
