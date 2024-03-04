package lk.IJse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Module.Branch;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;

public class BranchFormController {
    @FXML
    private TableColumn<?, ?> bookdqtycol;

    @FXML
    private TextField branchADDTXt;

    @FXML
    private TableColumn<?, ?> branchIDCol;

    @FXML
    private TextField branchIDtxt;

    @FXML
    private TableColumn<?, ?> branchNamecol;

    @FXML
    private TableView<?> branch_TableView;

    @FXML
    private TextField qtytxt;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableColumn<?, ?> statuscol;

    public String arr[]={"Open","Close"};
    @FXML
    private ComboBox<String> statustCombobox;
    public ObservableList<String> status = FXCollections.observableArrayList(arr);
    Branch branch = new Branch();

    @FXML
    void Add(ActionEvent event) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        String id = branchIDtxt.getText();
        String brancADD = branchADDTXt.getText();
        int qty = Integer.parseInt(qtytxt.getText());

        String statusBox = statustCombobox.getValue();
        String status = branch.setStatus(statusBox);

        branch.setBranch_ID(id);
        branch.setBranchADD(brancADD);
        branch.setBooksQuantity(qty);
        branch.setStatus(status);

        session.save(branch);

        transaction.commit();
        session.close();

    }

    @FXML
    void Update(ActionEvent event) {

    }

    public void BAckTOmenu(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/View/mainForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setTitle("Main Form");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }
    public void initialize(){
        statustCombobox.setItems(status);
    }
}
