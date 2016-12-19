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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import misc_classes.Menu_Item;
import misc_classes.Order;
import misc_classes.User;

/**
 * FXML Controller class
 *
 * @author Srdjan
 */
public class FXML_Add_OrderController implements Initializable {

    @FXML
    private ListView<?> lstview_order_contents;
    @FXML
    private ComboBox<?> combobox_menuItems;
    @FXML
    private Button bttn_add_item;
    @FXML
    private TextArea txtarea_comments;
    @FXML
    private Button bttn_remove_item;
    
    
    User owner;
    int table_number;
    
    Menu_Item water = new Menu_Item("Water", 2);
    Menu_Item coffee = new Menu_Item("coffee", 3);
    Menu_Item pasta = new Menu_Item("pasta", 10);
    Menu_Item pizza = new Menu_Item("pizza", 12);
    Menu_Item cheesecake = new Menu_Item("cheesecake", 5);
    
    Menu_Item[] menu = {water,coffee,pasta,pizza,cheesecake};
    
    Order this_order;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }
    
    public void set_owner(User owner){
        this.owner = owner;
    }
    public void set_table(int table_number){
        this.table_number = table_number;
    }
    
}
