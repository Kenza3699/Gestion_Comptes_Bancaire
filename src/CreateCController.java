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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author lightland
 */
public class CreateCController implements Initializable {

    @FXML
    private TextField txtfName;
    @FXML
    private TextField txtfFName;
    @FXML
    private TextField txtfAmount;
    @FXML
    private PasswordField txtfPassword;
    @FXML
    private TextField txtfAdresse;
    Connection con =null;
    ResultSet rs= null;
    PreparedStatement ps=null;
    @FXML
    private AnchorPane three;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add(ActionEvent event) {
         if(!(txtfAdresse.getText().equalsIgnoreCase("")) && !(txtfAmount.getText().equalsIgnoreCase("")) && !(txtfFName.getText().equalsIgnoreCase("")) && !(txtfName.getText().equalsIgnoreCase("")) && !(txtfPassword.getText().equalsIgnoreCase("")) ){
         con = MySqlConection.ConnectDB();
         String sql="Insert into customer (adresse,name,familyName,password,amount) values (?,?,?,?,?)";
         try {
             ps= con.prepareStatement(sql);
            ps.setString(1, txtfAdresse.getText());
            ps.setString(2, txtfName.getText());
            ps.setString(3, txtfFName.getText());
            ps.setString(4, txtfPassword.getText());
            ps.setString(5, txtfAmount.getText());
            ps.execute();
             JOptionPane.showMessageDialog(null, "Created!");
             con = MySqlConection.ConnectDB();
             String sqll="Select * from customer where adresse='"+txtfAdresse.getText()+"' and password='"+txtfPassword.getText()+"' and amount='"+txtfAmount.getText()+"' and name='"+txtfFName.getText()+"' and familyName='"+txtfFName.getText()+"'";
             try {
                 ps= con.prepareStatement(sqll);
            rs=ps.executeQuery();
            Customer customer;
            if(rs.next()){
                   JOptionPane.showMessageDialog(null, "Le némuro de votre compte est "+rs.getString("Id"));
             } }catch (Exception e) {
             }
            AnchorPane pane=FXMLLoader.load(getClass().getResource("/Bank.fxml"));
            three.getChildren().setAll(pane);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Remplissez les champs correctement!");
        }
             
         }else{
             JOptionPane.showMessageDialog(null, "Svp entrez les infomation de nouveau compte!");
         }
         
    }

    @FXML
    private void Exit(ActionEvent event) throws IOException {
         AnchorPane pane=FXMLLoader.load(getClass().getResource("/Bank.fxml"));
         three.getChildren().setAll(pane);
    }
    
}
