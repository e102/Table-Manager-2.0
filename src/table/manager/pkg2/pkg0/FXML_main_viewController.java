/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.manager.pkg2.pkg0;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import misc_classes.Order;
import misc_classes.User;
import javafx.scene.input.MouseEvent;

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
    int selected_table = 0; //0 = no table selected
    ArrayList<Order> current_orders = new ArrayList();
    ArrayList<Order> old_orders = new ArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        img_table1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                table_pressed(1);
                event.consume();
            }
        });
        
        img_table2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                table_pressed(2);
                event.consume();
            }
        });
        
        img_table3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                table_pressed(3);
                event.consume();
            }
        });
    }  
    
    public void setUser(User user){
        this.user = user;
        welcome_user_lbl.setText("Current User: " + user.getName());
    }
    
    private void table_pressed(int table_number){
        selected_table = table_number;
        lbl_selected_table.setText(Integer.toString(table_number));
    }
}
