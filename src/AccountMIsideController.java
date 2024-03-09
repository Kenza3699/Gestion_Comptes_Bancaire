/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author lightland
 */
public class AccountMIsideController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private TableColumn<Customer,String> colAdresse;
    @FXML
    private TableColumn<Customer,String> colName;
    @FXML
    private TableColumn<Customer,String> colFamilyName;
    @FXML
    private AnchorPane five;
    @FXML
    private TableColumn<Customer,Double> colAmount;
    Connection con =null;
    ResultSet rs= null;
    PreparedStatement ps=null;
    Statement st;
    @FXML
    private TableView<Customer> tabCustomer;
    @FXML
    private TableColumn<Customer,Integer> colId;
    @FXML
    private TextField txtfName;
    @FXML
    private TextField txtfFamilyName;
    @FXML
    private TextField txtfAdresse;
    @FXML
    private TextField txtfAmount;
    @FXML
    private PasswordField txtfPassword;
    @FXML
    private TextField txtfId;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCustomer();
    }    

    @FXML
    private void Exit(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/Bank.fxml"));
       five.getChildren().setAll(pane);
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
        if(!(txtfAdresse.getText().equalsIgnoreCase("")) && !(txtfAmount.getText().equalsIgnoreCase("")) && !(txtfFamilyName.getText().equalsIgnoreCase("")) && !(txtfName.getText().equalsIgnoreCase("")) && !(txtfPassword.getText().equalsIgnoreCase("")) ){
        con = MySqlConection.ConnectDB();
         String sql="Insert into customer (adresse,name,familyName,password,amount) values (?,?,?,?,?)";
         try {
             ps= con.prepareStatement(sql);
            ps.setString(1, txtfAdresse.getText());
            ps.setString(2, txtfName.getText());
            ps.setString(3, txtfFamilyName.getText());
            ps.setString(4, txtfPassword.getText());
            ps.setString(5, txtfAmount.getText());
            ps.execute();
            vider();
            showCustomer();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Svp entrez les infomation de nouveau compte!");
        }
    }
   public ObservableList<Customer> getCustomerList(){
        ObservableList<Customer> CustomerList = FXCollections.observableArrayList();
         con = MySqlConection.ConnectDB();
         String query = "SELECT * FROM customer ";
         try {
            st=con.createStatement();
            rs=st.executeQuery(query);
            Customer customer;
            while(rs.next()){
                customer= new Customer(rs.getInt("Id"),rs.getString("adresse"),rs.getString("name"),rs.getString("familyName"),rs.getDouble("amount"),rs.getString("password"));
               CustomerList.add(customer);
               }
            
        } catch (Exception e) {
            e.printStackTrace();
          
        }
         return CustomerList;
    }
    public void showCustomer(){
         ObservableList<Customer> list=getCustomerList();
         colId.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("Id"));
         colAdresse.setCellValueFactory(new PropertyValueFactory<Customer,String>("adresse"));
         colName.setCellValueFactory(new PropertyValueFactory<Customer,String>("name"));
         colFamilyName.setCellValueFactory(new PropertyValueFactory<Customer,String>("familyName")); 
         colAmount.setCellValueFactory(new PropertyValueFactory<Customer,Double>("amount"));
        tabCustomer.setItems(list);
    }
    private Customer cstm;
    @FXML
    private void SelectedItem(MouseEvent event) {
        cstm= tabCustomer.getSelectionModel().getSelectedItem();
        if (cstm != null){
            txtfName.setText(cstm.getName());
            txtfFamilyName.setText(cstm.getFamilyName());
            txtfAdresse.setText(cstm.getAdresse());
            txtfAmount.setText(Double.toString(cstm.getAmount()));
            txtfName.setText(cstm.getName());
            txtfPassword.setText(cstm.getPasssword());
            txtfId.setText(Integer.toString(cstm.getId()));
            
        }else{
            JOptionPane.showMessageDialog(null, "Séléctioner un compte!");
        }
        
    }

    @FXML
    private void Delete(ActionEvent event) {
         if(!(txtfAdresse.getText().equalsIgnoreCase("")) && !(txtfAmount.getText().equalsIgnoreCase("")) && !(txtfFamilyName.getText().equalsIgnoreCase("")) && !(txtfName.getText().equalsIgnoreCase("")) && !(txtfPassword.getText().equalsIgnoreCase("")) ){
        con = MySqlConection.ConnectDB();
         String sql2="Select * from operation where Id='"+Integer.toString(cstm.getId())+"'";
        try {
            ps= con.prepareStatement(sql2);
            rs=ps.executeQuery();
           Operation operation;
            while(rs.next()){
               operation= new Operation(rs.getInt("Id"),rs.getInt("operationNumber"),rs.getString("type"),rs.getString("dateOfCreation"),rs.getDouble("amount"));
               con = MySqlConection.ConnectDB();
               String sql3="delete  from operation where operationNumber='"+Integer.toString(operation.getOperationNumber())+"'";
                try {
                  ps= con.prepareStatement(sql3);
                  ps.execute(); 
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }catch (Exception e){
                     JOptionPane.showMessageDialog(null, e);
                    }
        
        con = MySqlConection.ConnectDB();
        String sql="delete from customer where Id='"+Integer.toString(cstm.getId())+"' ";
        try {
            ps=con.prepareStatement(sql);
            ps.execute();
            vider();
            showCustomer();
             } catch (Exception e) {
             JOptionPane.showMessageDialog(null,  e);
        }
         }else{
              JOptionPane.showMessageDialog(null,  "Svp séléctionner le compte pour le supprimer! ");
         }
        
    }

    @FXML
    private void Update(ActionEvent event) {
        if(!(txtfAdresse.getText().equalsIgnoreCase("")) && !(txtfAmount.getText().equalsIgnoreCase("")) && !(txtfFamilyName.getText().equalsIgnoreCase("")) && !(txtfName.getText().equalsIgnoreCase("")) && !(txtfPassword.getText().equalsIgnoreCase("")) ){
        con = MySqlConection.ConnectDB();
         String sql="Update customer set adresse=? , name=? , familyName =? , password=? ,amount=? where Id='"+Integer.toString(cstm.getId())+"'";
         try {
             ps= con.prepareStatement(sql);
            ps.setString(1, txtfAdresse.getText());
            ps.setString(2, txtfName.getText());
            ps.setString(3, txtfFamilyName.getText());
            ps.setString(4, txtfPassword.getText());
            ps.setString(5, txtfAmount.getText());
            ps.execute();
            vider();
            showCustomer();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Svp séléctionner le compte pour le modifier! ");
        }
    }

   private void vider(){
       txtfAdresse.setText("");
       txtfAmount.setText("");
       txtfFamilyName.setText("");
       txtfId.setText("");
       txtfName.setText("");
       txtfPassword.setText("");
               
   }
}
