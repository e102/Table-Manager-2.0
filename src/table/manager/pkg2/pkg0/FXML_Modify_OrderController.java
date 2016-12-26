/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.manager.pkg2.pkg0;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.Stage;
import misc_classes.Menu_Item;
import misc_classes.Order;
import misc_classes.User;

/**
 * FXML Controller class
 *
 * @author Srdjan
 */
public class FXML_Modify_OrderController implements Initializable {

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
    @FXML
    private Label lbl_error_msg;
    @FXML
    private Button btn_finish_order;
    @FXML
    private Label lbl_total;
    
    Order this_order;
    
    Menu_Item water = new Menu_Item("Water", 2);
    Menu_Item coffee = new Menu_Item("Coffee", 3);
    Menu_Item pasta = new Menu_Item("Pasta", 10);
    Menu_Item pizza = new Menu_Item("Pizza", 12);
    Menu_Item cheesecake = new Menu_Item("Cheesecake", 5);
    
    List<Menu_Item> menu = new ArrayList<Menu_Item>();

    ObservableList<Menu_Item> observable_contents;
    private ArrayList<Menu_Item> contents = new ArrayList();
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
        ObservableList<Menu_Item> observable_menu = FXCollections.observableList(menu);
        combobox_menuItems.setItems(observable_menu);
        combobox_menuItems.getSelectionModel().selectFirst();
    }

    
    /**
     * Adds selected item to order
     * @param e 
     */
    @FXML
    public void addItem(ActionEvent e){
        observable_contents.add(combobox_menuItems.getValue());
        lbl_total.setText("Total = " + Float.toString(this_order.getPrice()));
    }
    /**
     * Deletes selected item from order
     * @param e 
     */
    @FXML
    public void deleteItem(ActionEvent e){
        observable_contents.remove(combobox_menuItems.getValue());
        lbl_total.setText("Total = " + Float.toString(this_order.getPrice()));
    }
    
    /**
     * Checks if order is valid. If yes, passes to main controller and closes window.
     * @param e 
     */
    @FXML
    public void finish_order(ActionEvent e){
        //An order must contain at least 1 item
        lbl_error_msg.setText("");
        if(observable_contents.size() == 0){
            lbl_error_msg.setText("Empty orders are not allowed");
            return;
        }
        
        //Passes comment text to order
        this_order.setComment(txtarea_comments.getText());
        
        //sets main widow order to this order
        if(this_order.getTable() == 1){ FXML_main_viewController.table_1_order = this_order;}
        if(this_order.getTable() == 2){ FXML_main_viewController.table_2_order = this_order;}
        if(this_order.getTable() == 3){ FXML_main_viewController.table_3_order = this_order;}
        
        //Closes window
        Stage stage = (Stage) btn_finish_order.getScene().getWindow();
        stage.close();
    }
    
    /**
     * passes in pointer to main order
     * @param order 
     */
    public void set_order(Order order){
        this_order = order;
    }
    
    /**
     * Initializes observable list, listview and table number label
     */
    public void initialize_order(){
        observable_contents = FXCollections.observableList(this_order.get_contents());
        lstview_order_contents.setItems(observable_contents);
        lbl_table_num.setText(Integer.toString(this_order.getTable()));
    }
}