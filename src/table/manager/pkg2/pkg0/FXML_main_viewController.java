/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.manager.pkg2.pkg0;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import misc_classes.User;

/**
 * FXML Controller class
 *
 * @author Srdjan
 */
public class FXML_main_viewController implements Initializable {

    @FXML
    private Button btn_close_order;
    @FXML
    private Label lbl_selected_table;
    @FXML
    private Button btn_add_order;
    @FXML
    private ListView<?> list_orders;
    @FXML
    private Button btn_cancel_order;
    @FXML
    private ImageView img_table1;
    @FXML
    private ImageView img_table2;
    @FXML
    private ImageView img_table3;
    @FXML
    private Label welcome_user_lbl;
    
    
    User user;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setUser(User user){
        this.user = user;
        welcome_user_lbl.setText("Current User: " + user.getName());
    }
}
