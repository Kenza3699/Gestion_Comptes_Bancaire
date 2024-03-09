/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXSnackbar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author lightland
 */
public class AccountMController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtfUserName;
    @FXML
    private TextField txtfPassword;
    @FXML
    private AnchorPane User;
    @FXML
    private VBox rightBox;
    
    JFXSnackbar Errormsg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Exit(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/Bank.fxml"));
        User.getChildren().setAll(pane);
    }

    @FXML
    private void Login(ActionEvent event) throws IOException {
        if(txtfUserName.getText().equals("User") && txtfPassword.getText().equals("study hard")){
    
            AnchorPane pane1=FXMLLoader.load(getClass().getResource("/AccountMIside.fxml"));
            User.getChildren().setAll(pane1);
        }else if(!txtfUserName.getText().equals("User") ){
            Errormsg = new JFXSnackbar(rightBox);
            Errormsg.show("Try again your User name!", 1500);
        }else {
            Errormsg = new JFXSnackbar(rightBox);
            Errormsg.show("Try again your password!", 1500);
           
        }
    }
    
}
