/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author lightland
 */
public class BankController implements Initializable {

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
    private AnchorPane First;


    @FXML
    private void create(ActionEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/CreateC.fxml"));
        First.getChildren().setAll(pane);
       
    }

    @FXML
    private void consult(ActionEvent event) throws IOException {
        AnchorPane pane1=FXMLLoader.load(getClass().getResource("/ConsultA.fxml"));
        First.getChildren().setAll(pane1);
    }

    @FXML
    private void accountM(ActionEvent event) throws IOException {
        AnchorPane pane2=FXMLLoader.load(getClass().getResource("/AccountM.fxml"));
        First.getChildren().setAll(pane2);
    }

    @FXML
    private void Exit(ActionEvent event) {
         Platform.exit();
    }

   
}
