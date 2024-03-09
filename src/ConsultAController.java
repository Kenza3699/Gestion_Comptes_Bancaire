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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author lightland
 */
public class ConsultAController implements Initializable {
    

    @FXML
    private TextField txtfId;
    @FXML
    private TextField txtfPassword;
    Connection con =null;
    ResultSet rs= null;
    PreparedStatement ps=null;
    @FXML
    private AnchorPane Two;
    public int Idd;
    @FXML
    private Button btnExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void Exit(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/Bank.fxml"));
       Two.getChildren().setAll(pane);
    }

    @FXML
    private void Login(ActionEvent event) {
       
        con = MySqlConection.ConnectDB();
       String sql="Select * from customer where Id='"+txtfId.getText()+"' and password='"+txtfPassword.getText()+"'";
        try {
            ps= con.prepareStatement(sql);
            rs=ps.executeQuery();
            Customer customer;
            if(rs.next()){
                
                customer= new Customer(rs.getInt("Id"),rs.getString("adresse"),rs.getString("name"),rs.getString("familyName"),rs.getDouble("amount"),rs.getString("password"));
               FXMLLoader loader= new FXMLLoader();
               loader.setLocation(getClass().getResource("/InsideA.fxml"));
                try {
                    loader.load();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                InsideAController inside=loader.getController();
               
                inside.setInfo(rs.getString("name"), rs.getString("familyName"),rs.getDouble("amount"),rs.getShort("Id"));
                inside.getCustomer(customer);
                inside.showOperation(rs.getInt("Id"));
                Parent p=loader.getRoot();
                ((Node)event.getSource()).getScene().getWindow().hide();
                Stage stage = new Stage();
                stage.setScene(new Scene(p));
                Image image=new Image("/Image/kisspng-bitcoin-cryptocurrency-monero-initial-coin-offerin-bitcoin-5abdfe6babbd25.0459423215224008757035.png");
                stage.getIcons().add(image);
                stage.setTitle("My B Bank");
                stage.showAndWait();
                
            }else{
                JOptionPane.showMessageDialog(null, "Incorect information!");
            }
               
        } catch (Exception e) {
              JOptionPane.showMessageDialog(null, e);
               
        }
        
    }
   
}
