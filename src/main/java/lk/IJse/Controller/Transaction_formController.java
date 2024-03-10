package lk.IJse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Module.Books;
import lk.IJse.Module.Borrowers;
import lk.IJse.Module.Branch;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import lk.IJse.Module.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.awt.print.Book;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Transaction_formController {
    public TableView<Borrowers> TransTableView;
    public TableColumn<Borrowers, String> B_IDCol;
    public TableColumn<Borrowers, String> U_IDCol;
    public TableColumn<Borrowers, String> B_NameCol;
    public TableColumn<Borrowers, String> BranchNameCol;
    public TableColumn<Borrowers, String> U_nameCol;
    public TableColumn<Borrowers, String> BorrowD_col;
    public TableColumn<Borrowers, String> H_dateCol;
    public List<Borrowers> borrowersList;
    public AnchorPane rootNode;
    private factoryConfiguration factoryConfiguration;

    public void refreshTable() {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        try {
            NativeQuery<Object[]> query = session.createNativeQuery(
                    "SELECT b.bookId, b.Bdate, b.HDate, u.name AS userName, bk.title AS bookTitle, br.branchADD AS branchAddress\n" +
                            "FROM Borrowers b\n" +
                            "JOIN User u ON b.user_id = u.id\n" +
                            "JOIN Books bk ON b.book_id = bk.bookId\n" +
                            "JOIN Branch br ON b.branch_ID = br.branch_ID;\n");
            List<Object[]> resultList = query.list();

            for (Object[] result : resultList) {
                for (Object column : result) {
                    System.out.print(column + " ");
                }
                System.out.println();
            }
            if (borrowersList == null) {
                borrowersList = FXCollections.observableArrayList();
            } else {
                borrowersList.clear();
            }

            for (Object[] result : resultList) {
                Borrowers borrower = new Borrowers();
                borrower.setBookId((String) result[0]); // Corrected data type
                borrower.setBdate(((java.sql.Timestamp) result[1]).toLocalDateTime());
                borrower.setHDate(((java.sql.Timestamp) result[2]).toLocalDateTime());
                borrower.setUserName((String) result[3]);
                borrower.setBookTitle((String) result[4]);
                borrower.setBranchAddress((String) result[5]);

                borrowersList.add(borrower);
            }


            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


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

    public void initialize() {
        borrowersList = FXCollections.observableArrayList();
        refreshTable();

//        B_IDCol.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        U_IDCol.setCellValueFactory(new PropertyValueFactory<>("userName")); // Change the property name
        B_NameCol.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        BranchNameCol.setCellValueFactory(new PropertyValueFactory<>("branchAddress"));
        BorrowD_col.setCellValueFactory(new PropertyValueFactory<>("Bdate"));
        H_dateCol.setCellValueFactory(new PropertyValueFactory<>("HDate"));

        TransTableView.setItems((ObservableList<Borrowers>) borrowersList);
    }

}
