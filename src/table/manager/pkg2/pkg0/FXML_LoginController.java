/**
 * The login controller takes info entered in the login page and determines if a login is valid.
 * @author Srdjan Miletic
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
 *Controller for the login page
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
    
    /**
     * @param event 
     * 
     * On button press, checks contents of username and password boxes. If valid, opens main window.
     */
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        //fetch contents of boxes
        userName = username_textbox.getText();
        password = password_passwordbox.getText();
        
        //Check if user exists
        if(userData.get(userName) == null){
            Login_Msg_lbl.setText("Username not recognized.");
            return;
        }
        
        //Check if password is correct
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
    
    /**
     * closes login window and opens main view, passing user details in.
     * @throws Exception 
     */
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
    }
    
    /**
     * Sets userdata to input
     * @param userData 
     */
    public void setUserInfo(HashMap <String,User> userData){
        this.userData = userData;
    }
    
    /**
     * passing in stage from starting controller
     * @param stage 
     */
    public void setStage(Stage stage){
        this.stage = stage;
    }
    
}
