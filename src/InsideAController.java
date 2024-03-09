/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author lightland
 */

public class InsideAController implements Initializable {

    @FXML
    private Label labelFullName;
    @FXML
    private Label labelAmount;
    Connection con =null;
    ResultSet rss= null;
    PreparedStatement pss=null;
    @FXML
    private TableView<Operation> tabOperation;
  
    @FXML
    private TableColumn<Operation,String> colOpType;
    @FXML
    private TableColumn<Operation,Double> colAmount;
    @FXML
    private AnchorPane inside;
    Customer customer;
    @FXML
    private TextField txtfAmountR;
    @FXML
    private TextField txtfAmountV;
    @FXML
    private TextField txtfIdV;
    @FXML
    private Label labelId;
    @FXML
    private TableColumn<Operation,String> colDate;
    @FXML
    DatePicker date;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
     
    @FXML
    private void Exit(ActionEvent event) throws IOException {
       AnchorPane pane=FXMLLoader.load(getClass().getResource("/Bank.fxml"));
       inside.getChildren().setAll(pane);
        
    }
    public void getCustomer(Customer customer){
        this.customer=customer;
        
    }
    public void  setInfo(String name, String familyName, Double amount ,int Id){
       
        this.labelFullName.setText(name +" "+ familyName );
        this.labelAmount.setText(amount.toString() +" DA");
        this.labelId.setText(Integer.toString(Id));
     
        
    }
    
 String d;
    @FXML
    private void Retrait(ActionEvent event) throws IOException {
         if(!(txtfAmountR.getText().equalsIgnoreCase(""))){
        Double a=customer.getAmount();
        Double b=Double.parseDouble(txtfAmountR.getText());
        if(a>=b){
            Double newAmount=a-b;
             setInfo(customer.getName(), customer.getFamilyName(), newAmount,customer.getId());
             con = MySqlConection.ConnectDB();
        String sql="Update customer set adresse=? , name=? , familyName =? , password=? ,amount=? where Id='"+Integer.toString(customer.getId())+"'";
         try {
             
             pss= con.prepareStatement(sql);
            pss.setString(1, customer.getAdresse());
            pss.setString(2, customer.getName());
            pss.setString(3, customer.getFamilyName());
            pss.setString(4, customer.getPasssword());
            pss.setString(5, Double.toString(newAmount) );
            pss.execute();
             }
             catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
        } Timestamp time=new Timestamp(System.currentTimeMillis());
        
             con = MySqlConection.ConnectDB();
             String sqll="Insert into operation (Id,type,dateOfCreation,amount) values (?,?,?,?)";
             try {
            pss= con.prepareStatement(sqll);
            pss.setString(1, Integer.toString(customer.getId()));
            pss.setString(2, "retrait");
            pss.setTimestamp(3,time );
            pss.setString(4, Double.toString(b));
            pss.execute();
            JOptionPane.showMessageDialog(null, "Retrait effectué!");
            showOperation(customer.getId());
                 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,  e);
            }
               
        }else{
            JOptionPane.showMessageDialog(null, "Insufisant amount!");
        } 
         }else{
              JOptionPane.showMessageDialog(null,  "Entrez le montant  à retirer!");
         }
   }
  
    @FXML
    private void versement(ActionEvent event) throws IOException {
         if(!(txtfAmountV.getText().equalsIgnoreCase(""))){
             if(!(txtfIdV.getText().equalsIgnoreCase(""))){
         Double V=Double.parseDouble(txtfAmountV.getText());
        Double a=customer.getAmount();
        if(a>=V){
            Double newAmount=a-V;
            setInfo(customer.getName(), customer.getFamilyName(), newAmount,customer.getId());
             con = MySqlConection.ConnectDB();
        String sql3="Update customer set adresse=? , name=? , familyName =? , password=? ,amount=? where Id='"+Integer.toString(customer.getId())+"'";
            try {
             
             pss= con.prepareStatement(sql3);
            pss.setString(1, customer.getAdresse());
            pss.setString(2, customer.getName());
            pss.setString(3, customer.getFamilyName());
            pss.setString(4, customer.getPasssword());
            pss.setString(5, Double.toString(newAmount) );
            pss.execute();
                   }
             catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
        } 
             Timestamp time=new Timestamp(System.currentTimeMillis());
             
             con = MySqlConection.ConnectDB();
             String sqll="Insert into operation (Id,type,dateOfCreation,amount) values (?,?,?,?)";
             try {
            pss= con.prepareStatement(sqll);
            pss.setString(1, Integer.toString(customer.getId()));
            pss.setString(2, "versement");
            pss.setString(3,time.toString() );
            pss.setString(4, Double.toString(V));
            pss.execute();
            JOptionPane.showMessageDialog(null, "Versement effectué !");
            showOperation(customer.getId());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,  e);
            }
             con = MySqlConection.ConnectDB();
       String query="Select * from customer where Id='"+txtfIdV.getText()+"'";
       try {
            pss= con.prepareStatement(query);
            rss=pss.executeQuery();
            Customer customer2;
            if(rss.next()){
                customer2= new Customer(rss.getInt("Id"),rss.getString("adresse"),rss.getString("name"),rss.getString("familyName"),rss.getDouble("amount"),rss.getString("password"));
               
               Double newAmountV=V+rss.getDouble("amount");
                con = MySqlConection.ConnectDB();
        String sql4="Update customer set adresse=? , name=? , familyName =? , password=? ,amount=? where Id='"+txtfIdV.getText()+"'";
            try {
             
             pss= con.prepareStatement(sql4);
            pss.setString(1, customer2.getAdresse());
            pss.setString(2, customer2.getName());
            pss.setString(3, customer2.getFamilyName());
            pss.setString(4, customer2.getPasssword());
            pss.setString(5, Double.toString(newAmountV) );
            pss.execute();
                   }
             catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
        } 
            }else{
                JOptionPane.showMessageDialog(null, "Id ne correspond a aucun compte!" );
            }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
           
               }else{
                   JOptionPane.showMessageDialog(null, "Insufisant amount!");
               }
             }else{
                   JOptionPane.showMessageDialog(null,  "Entrez le numéro de compte de votre récepteur!");
             }
            
         }else{
             JOptionPane.showMessageDialog(null,  "Entrez le montant  à verser!");
         }   
    }
    
    
     public ObservableList<Operation> getOperationList(int id){
        String idd= Integer.toString(id);
        ObservableList<Operation> OperationList = FXCollections.observableArrayList();
         con = MySqlConection.ConnectDB();
         String query = "Select * from operation where Id='"+idd+"'";
         try {
            pss= con.prepareStatement(query);
            rss=pss.executeQuery(query);
            Operation operation;
            
            while(rss.next()){
               
                operation= new Operation(rss.getInt("operationNumber"),rss.getInt("Id"),rss.getString("type"),rss.getString("dateOfCreation"),rss.getDouble("amount"));
               OperationList.add(operation);
               }
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
        }
         return OperationList;
    }
    public void showOperation(int id){
          final ObservableList<Operation> list=getOperationList(id);
          try {
          
         colOpType.setCellValueFactory(new PropertyValueFactory<Operation,String>("type"));
         colDate.setCellValueFactory(new PropertyValueFactory<Operation,String>("dateOfCreation"));
         colAmount.setCellValueFactory(new PropertyValueFactory<Operation,Double>("amount"));
          
         tabOperation.setItems(list);
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
        }
        
    } 
}


   