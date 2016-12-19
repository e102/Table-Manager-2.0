/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.manager.pkg2.pkg0;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
    private ListView<Menu_Item> lstview_order_contents;
    @FXML
    private ComboBox<Menu_Item> combobox_menuItems;
    @FXML
    private Button bttn_add_item;
    @FXML
    private TextArea txtarea_comments;
    @FXML
    private Button bttn_remove_item;
    @FXML
    private Label lbl_table_num;
    
    User owner;
    int table_number;
    
    Menu_Item water = new Menu_Item("Water", 2);
    Menu_Item coffee = new Menu_Item("Coffee", 3);
    Menu_Item pasta = new Menu_Item("Pasta", 10);
    Menu_Item pizza = new Menu_Item("Pizza", 12);
    Menu_Item cheesecake = new Menu_Item("Cheesecake", 5);
    
    ArrayList menu = new ArrayList();
    
    Order this_order = new Order();

    ObservableList<Menu_Item> observable_contents;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Setting up menu item comboBox
        menu.add(water);
        menu.add(coffee);
        menu.add(pasta);
        menu.add(pizza);
        menu.add(cheesecake);
        ObservableList<Menu_Item> observable_menu = FXCollections.observableArrayList(menu);
        combobox_menuItems.setItems(observable_menu);
        
        lbl_table_num.setText(Integer.toString(table_number));

    }
    
    @FXML
    public void addItem(ActionEvent e){
        this_order.addItem(cheesecake); //PROBLEM
        this_order.addItem(combobox_menuItems.getValue());
    }
    
    @FXML
    public void deleteItem(ActionEvent e){
        this_order.removeItem(combobox_menuItems.getValue());
    }
    
    public void set_owner(User owner){
        this.owner = owner;
    }
    public void set_table(int table_number){
        this.table_number = table_number;
        
//      Setting up table number lbl
        lbl_table_num.setText(Integer.toString(table_number));
    }
    
    public void initialize_order(){
        this_order.setOwner(owner);
        this_order.setTable(table_number);
        observable_contents = FXCollections.observableArrayList(this_order.get_contents());
        lstview_order_contents.setItems(observable_contents);
    }
    
}
