package lk.IJse.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Dto.DataDto;
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
    public DataDto dataDto;

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
        BookBorrowersFormController bookBorrowersFormController = null;
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            String name = lg_nameTxt.getText();
            String password = lg_passwordTxt.getText();

            Query<User> query = session.createQuery("FROM User u WHERE u.name = :name AND u.password = :password", User.class);
            query.setParameter("name", name);
            query.setParameter("password", password);


            User user = query.uniqueResult();

            Query<User> query2 = session.createQuery("FROM User u WHERE u.name = :name", User.class);
            query2.setParameter("name", name);

            User user1=query2.uniqueResult() ;

            Query<User> query3 = session.createQuery("select u.id from User u where u.name = :name group by u.id", User.class);
            query3.setParameter("name", name);

            String user3 = String.valueOf(query3.uniqueResult());

            System.out.println(user3);

            if (user != null) {
                new Alert(Alert.AlertType.INFORMATION,"Login Successfull").show();
                Parent parent = FXMLLoader.load(getClass().getResource("/View/mainform.fxml"));
                Stage stage = (Stage) rootNode.getScene().getWindow();
                Scene scene = new Scene(parent);
                stage.setTitle("Main Form");
                stage.centerOnScreen();
                stage.setScene(scene);
                stage.show();
                System.out.println(user1.getName());
                dataDto.name = user1.getName();
                dataDto.userId = user3;

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
    public void initialize(){

    }

}
