/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author lightland
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       Parent root=FXMLLoader.load(getClass().getResource("/Bank.fxml"));
        Scene scene = new Scene(root, 800, 590);
        Image image=new Image("/Image/kisspng-bitcoin-cryptocurrency-monero-initial-coin-offerin-bitcoin-5abdfe6babbd25.0459423215224008757035.png");
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("My B Bank");
        primaryStage.setScene(scene);
        
        primaryStage.show();
        primaryStage.setResizable(false);
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
