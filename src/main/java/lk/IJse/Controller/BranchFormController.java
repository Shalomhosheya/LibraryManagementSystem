package lk.IJse.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJse.Dto.BranchDto;
import lk.IJse.Module.Branch;
import lk.IJse.Module.FactoryConfig.factoryConfiguration;
import lk.IJse.Module.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

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
    private TableView<Branch> branch_TableView;

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
    public ObservableList<Branch>branchObservableList;

    public BranchDto branchDto = new BranchDto();
    public Branch branch = new Branch();

    @FXML
    void Add(ActionEvent event) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        String id = branchIDtxt.getText();
        String branchADD = branchADDTXt.getText();
        int qty = Integer.parseInt(qtytxt.getText());

        String statusBox = statustCombobox.getValue();
        String status = branch.setStatus(statusBox);

        branch.setBranch_ID(id);
        branch.setBranchADD(branchADD);
        branch.setBooksQuantity(qty);
        branch.setStatus(status);

        // Refresh the TableView first
        refreshTableView();


        session.save(branch);

        transaction.commit();
        session.close();
    }

    public void refreshTableView() {
        Session session = factoryConfiguration.getInstance().getSessionFactory();

        try {
            // Fetch all books from the database
            Query<Branch> query = session.createQuery("FROM Branch", Branch.class);
            List<Branch> BranchList = query.list();

            // Update the existing ObservableList with the fetched data
            branchObservableList.setAll(BranchList);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @FXML
    void Update(ActionEvent event) {
        Session session = factoryConfiguration.getInstance().getSessionFactory();
        Transaction transaction = session.beginTransaction();

        // Get the branch by ID
        Branch branch1 = session.get(Branch.class, branchIDtxt.getText());

        String selected = statustCombobox.getValue();

        if (branch1 != null) {
            branch1.setBranch_ID(branchIDtxt.getText());
            branch1.setBranchADD(branchADDTXt.getText());
            branch1.setBooksQuantity(Integer.parseInt(qtytxt.getText()));
            branch1.setStatus(selected);


            session.saveOrUpdate(branch1);
            transaction.commit();
            session.close();
            refreshTableView();// SaveOrUpdate the branch;
        } else {
            new Alert(Alert.AlertType.ERROR,"Update Fail!").show();
        }
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
    public void selectedData(MouseEvent mouseEvent) {
        Branch selected = (Branch) branch_TableView.getSelectionModel().getSelectedItem();
        if (selected!=null){
            branchIDtxt.setText(selected.getBranch_ID());
            branchADDTXt.setText(selected.getBranchADD());
            qtytxt.setText(String.valueOf(selected.getBooksQuantity()));

            statustCombobox.getSelectionModel().select(selected.getStatus());
        }

    }

    public void initialize(){

        branchObservableList = FXCollections.observableArrayList();
        statustCombobox.setItems(status);

        branchIDCol.setCellValueFactory(new PropertyValueFactory<>("branch_ID"));
        branchNamecol.setCellValueFactory(new PropertyValueFactory<>("branchADD"));
        bookdqtycol.setCellValueFactory(new PropertyValueFactory<>("booksQuantity")); // Note the correction in property name
        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));

        branch_TableView.setItems(branchObservableList);
        refreshTableView();
    }


}
