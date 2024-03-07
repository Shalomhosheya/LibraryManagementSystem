package lk.IJse.Controller.ADD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Module.Books;
import lk.IJse.Module.Borrowers;
import lk.IJse.Module.Branch;
import lk.IJse.Module.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class transactionUserFormController {
    public Borrowers borrowers = new Borrowers();
    public Branch branch = new Branch();
    public Books books = new Books();
    public User user = new User();
    public TableView<Borrowers> TransTableView;
    public TableColumn<Borrowers,Books> B_IDCol;
    public TableColumn <Borrowers,User>U_IDCol;
    public TableColumn <Borrowers,Books>B_NameCol;
    public TableColumn <Borrowers,Branch>BranchNameCol;
    public TableColumn U_nameCol;
    public TableColumn <Borrowers, Date> BorrowD_col;
    public TableColumn <Borrowers,Date>H_dateCol;
    public List<Borrowers> borrowersList;
    public AnchorPane rootNode;
    private lk.IJse.Module.FactoryConfig.factoryConfiguration factoryConfiguration;

    public void refreshTable() {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        try {
            Query<Borrowers> query = session.createQuery("from Borrowers", Borrowers.class);
            List<Borrowers> resultList = query.list();

            // Ensure that the list is not null before clearing and updating it
            if (borrowersList == null) {
                borrowersList = new ArrayList<>();
            } else {
                borrowersList.clear();
            }

            // Update the list with the new results
            borrowersList.addAll(resultList);

            System.out.println(borrowersList);

            transaction.commit();
        } catch (Exception e) {
            // Handle any exceptions appropriately
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Log or handle the exception as needed
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void BacktoMenu(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View2/UserMainForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void Refresh(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View2/transaction_formUser.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    public void initialize() {
        borrowersList = FXCollections.observableArrayList();
        refreshTable();

        B_IDCol.setCellValueFactory(new PropertyValueFactory<>("id")); // Assuming "id" is the property in Borrowers for book_id
        U_IDCol.setCellValueFactory(new PropertyValueFactory<>("user")); // Assuming "user" is the property in Borrowers for user_id
        B_NameCol.setCellValueFactory(new PropertyValueFactory<>("book")); // Assuming "book" is the property in Borrowers for book_id
        BranchNameCol.setCellValueFactory(new PropertyValueFactory<>("branch")); // Assuming "branch" is the property in Borrowers for branch_ID
        BorrowD_col.setCellValueFactory(new PropertyValueFactory<>("Bdate"));
        H_dateCol.setCellValueFactory(new PropertyValueFactory<>("HDate"));

        TransTableView.setItems((ObservableList<Borrowers>) borrowersList);
    }
}
