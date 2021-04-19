
import gui.Home;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Kubeka BS 217010763
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        Home root = new Home();
 
        Scene scene = new Scene(root,1000,1000);
        
        primaryStage.setTitle("217010763_Coal Allocatorv0.1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

   
    public static void main(String[] args) {
        launch(args);
    }
    
}
