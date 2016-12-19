/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.manager.pkg2.pkg0;

import misc_classes.User;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Srdjan
 */
public class FXML_LoginController implements Initializable {
    private HashMap <String,User> userData = new HashMap();
    
    
    private Label label;
    @FXML
    private Button login_button;
    @FXML
    private Button admin_button;
    @FXML
    private Label username_label;
    @FXML
    private Label password_label;
    @FXML
    private PasswordField password_passwordbox;
    @FXML
    private TextField username_textbox;
    @FXML
    private Label Login_Msg_lbl;
    
    private String userName;
    private String password;
    private Stage stage;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        userName = username_textbox.getText();
        password = password_passwordbox.getText();
        
        if(userData.get(userName) != null){
            if(userData.get(userName).getPassword().equals(password)){
                Login_Msg_lbl.setTextFill(Color.web("#009933"));
                Login_Msg_lbl.setText("Login details Correct");
                try{
                    this.login();
                }
                catch(Exception e){
                    System.err.println(e.getMessage());
                    System.out.println("Oops");
                }
            }
            else{
                Login_Msg_lbl.setText("Incorrect password");
            }
        }
        else{
            Login_Msg_lbl.setText("Username not recognized.");
        }
    }
    
    private void login() throws Exception{
        stage.setTitle("Table Manager 2.0 Main");
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("FXML_main_view.fxml"));
        Scene scene = new Scene(mainLoader.load());
        FXML_main_viewController mainCtrl = mainLoader.getController();
        mainCtrl.setUser(userData.get(userName));
        stage.setScene(scene);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setUserInfo(HashMap <String,User> userData){
        this.userData = userData;
    }
    
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
}
