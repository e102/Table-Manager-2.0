/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.manager.pkg2.pkg0;

import misc_classes.User;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

/**
 *
 * @author Srdjan
 */
public class TableManager20 extends Application {
    
    private User admin1 = new User("Gita", "best_coursework_ever", true);
    private User waiter1 = new User("Walter", "just_a_waiter", false);
    private HashMap <String,User> userData = new HashMap();
    
    @Override
    public void start(Stage stage) throws Exception {
        userData.put(admin1.getName(), admin1);
        userData.put(waiter1.getName(), waiter1);
        stage.setTitle("Table Manager 2.0 Login");
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("FXML_Login.fxml"));
        Scene scene = new Scene(loginLoader.load());
        FXML_LoginController loginCtrl = loginLoader.getController();
        loginCtrl.setUserInfo(userData);    //passing list of users to controller
        loginCtrl.setStage(stage);
        stage.setScene(scene);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
