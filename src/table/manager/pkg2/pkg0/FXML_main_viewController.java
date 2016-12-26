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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import misc_classes.Order;
import misc_classes.User;
import misc_classes.Menu_Item;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class for main view
 *
 * handles table clicks, opening/closing/deleting/changing orders.
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
    private ListView<Menu_Item> list_orders;
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
    @FXML
    private Label lbl_total;
    @FXML
    private Button btn_add_order1;
    @FXML
    private Label Order_Erro_Label;
    @FXML
    private Button btn_refresh;
    
    protected static User user;
    protected static int selected_table = 0; //0 = no table selected
    protected static Order table_1_order = null;
    protected static Order table_2_order = null;
    protected static Order table_3_order = null;
    private ArrayList<Order> old_orders = new ArrayList();

    

    ObservableList<Menu_Item> observable_order_contents;
    ObservableList<Menu_Item> observable_empty = FXCollections.emptyObservableList();
    
    /**
     * <p>Adds eventListeners to table images.</p>
     * 
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
    

    /**
     * takes the table number and updates labels, currentorder and the listview
     * @param table_number 
     */
    private void table_pressed(int table_number){
        
        //Set selected table variable and label  
        selected_table = table_number;
        lbl_selected_table.setText(Integer.toString(table_number));
        
        //Set list and total to empty if there is no order. Else set to display order contents
        if(table_to_order(table_number) != null){
            observable_order_contents = FXCollections.observableList(table_to_order(table_number).get_contents());
            list_orders.setItems(observable_order_contents);
            lbl_total.setText(Float.toString(table_to_order(selected_table).getPrice()));
        }
        else{
            observable_order_contents = null;
            list_orders.setItems(observable_empty);
            lbl_total.setText("N/A");
        }
    }
    
    /**
     * If no order exists for current table, opens the order adder window.
     * User can then create a new order.
     */
    @FXML
    private void add_order(ActionEvent a){
        
        //check if an order already exists
        if(table_to_order(selected_table) != null){
            Order_Erro_Label.setText("Open order already exists.");
            return;
        }
        
        // check if a table is selected
        if(selected_table == 0){
            Order_Erro_Label.setText("Please select a table");
            return;
        }
        
        //Create new order for the seleted table
        if(selected_table == 1){table_1_order = new Order(user,selected_table);}
        if(selected_table == 2){table_2_order = new Order(user,selected_table);}
        if(selected_table == 3){table_3_order = new Order(user,selected_table);}
        
        //Open order modifier page
        Stage add_order = new Stage();
        add_order.setAlwaysOnTop(true);
        add_order.setTitle("Order Creator");
        FXMLLoader order_chooser_loader = new FXMLLoader(getClass().getResource("FXML_Add_Order.fxml"));
        try{
        Scene scene = new Scene(order_chooser_loader.load());
        FXML_Add_OrderController ctrl = order_chooser_loader.getController();
        add_order.setScene(scene);
        ctrl.set_table(selected_table);
        ctrl.initialize_order();
        add_order.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Opens the order modifier window to change an existing order
     * @param a 
     */
    @FXML
    private void modify_order(ActionEvent a){
        
        // check if a table is selected
        if(selected_table == 0){
            Order_Erro_Label.setText("Please select a table");
            return;
        }
        
        //check if an order exists
        if(table_to_order(selected_table) == null){
            Order_Erro_Label.setText("No open order exists for this table.");
            return;
        }
        
        //Open order modifier page
        Stage modify_order = new Stage();
        modify_order.setAlwaysOnTop(true);
        modify_order.setTitle("Order Modifier");
        FXMLLoader order_chooser_loader = new FXMLLoader(getClass().getResource("FXML_Modify_Order.fxml"));
        try{
        Scene scene = new Scene(order_chooser_loader.load());
        FXML_Modify_OrderController ctrl = order_chooser_loader.getController();
        modify_order.setScene(scene);
        ctrl.set_order(table_to_order(selected_table));
        ctrl.initialize_order();
        modify_order.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes the currently selected order
     * @param a 
     */
    @FXML
    private void cancel_order(ActionEvent a){
        
        // no table selected = try again
        if(selected_table == 0){
            Order_Erro_Label.setText("Please select a table");
            return;
        }
        
        //no order = can't delete
        if(table_to_order(selected_table) == null){
            Order_Erro_Label.setText("There is no open order to delete.");
            return;
        }
        
        //add to old orders
        old_orders.add(table_to_order(selected_table));
        
        //open warning window
        Stage warning = new Stage();
        warning.setAlwaysOnTop(true);
        warning.setTitle("Warning");
        FXMLLoader warning_loader = new FXMLLoader(getClass().getResource("FXML_Warning_Delete_Order.fxml"));
        try{
        Scene scene = new Scene(warning_loader.load());
        FXML_Warning_Delete_OrderController ctrl = warning_loader.getController();
        warning.setScene(scene);
        ctrl.getTable(selected_table);
        warning.show();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
//        // table selected = delete current order
//        if(selected_table == 1){
//            table_1_order = null;
//        }
//        if(selected_table == 2){
//            table_2_order = null;
//        }       
//        if(selected_table == 3){
//            table_3_order = null;
//        }
        
        refresh_list();
    }
    
    /**
     * Archives the current order
     * @param a 
     */
    @FXML 
    private void close_order(ActionEvent a){
        // no table selecte = try again
        if(selected_table == 0){
            return;
        }
        
        // no order = can't delete
        if(table_to_order(selected_table) == null){
            lbl_total.setText("N/A");
            list_orders.setItems(null);
            return;
        }
        
        // fetch and delete current order
        if(selected_table == 1){
            old_orders.add(table_1_order);
            table_1_order = null;
        }
        if(selected_table == 2){
            old_orders.add(table_2_order);
            table_2_order = null;
        }       
        if(selected_table == 3){
            old_orders.add(table_3_order);
            table_3_order = null;
        }
        
        //refresh the listview
        refresh_list();
    }
    
    /**
     * refreshes the listview
     * @param z 
     */
    @FXML
    private void refresh_list(ActionEvent z){
        // no table selected = error
        if(selected_table == 0){return;}
        
        // no order = list and total should be empty
        if(table_to_order(selected_table) == null){
            lbl_total.setText("N/A");
            list_orders.setItems(null);
            return;
        }
        
        // otherwise, set list to order contents and label to total
        observable_order_contents = FXCollections.observableList(table_to_order(selected_table).get_contents());
        list_orders.setItems(observable_order_contents);
        lbl_total.setText(Float.toString(table_to_order(selected_table).getPrice()));
    }
    
    /**
     * refreshes the listview (no paramaters)
     */
    private void refresh_list(){
        if(selected_table == 0){return;}
        if(table_to_order(selected_table) == null){
            lbl_total.setText("N/A");
            list_orders.setItems(null);
            return;
        }
        observable_order_contents = FXCollections.observableList(table_to_order(selected_table).get_contents());
        list_orders.setItems(observable_order_contents);
        lbl_total.setText(Float.toString(table_to_order(selected_table).getPrice()));
    }
    
    /**
     * Sets the user currently logged in. Used by the login controler
     * @param user 
     */
    public void setUser(User user){
        this.user = user;
        welcome_user_lbl.setText("Current User: " + user.getName());
    }
    
    /**Misc method which returns current order when given table number.
     * @param table_number
     * @return corresponding table order
     */
    public static Order table_to_order(int table_number){
        if(table_number == 1){
            return table_1_order;
        }
        if(table_number == 2){
            return table_2_order;
        }       
        if(table_number == 3){
            return table_3_order;
        }
        return null;
    }
}
