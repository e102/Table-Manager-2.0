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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import misc_classes.Order;
import misc_classes.User;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    @FXML
    private Label lbl_total;
    @FXML
    private Button btn_add_order1;
    @FXML
    private Label Order_Erro_Label;
    
    private User user;
    private int selected_table = 0; //0 = no table selected
    protected static Order table_1_order = null;
    protected static Order table_2_order = null;
    protected static Order table_3_order = null;
    private ArrayList<Order> old_orders = new ArrayList();

    

    
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
        if(selected_table == 1){
            table_1_order = new Order(user,selected_table);
        }
        if(selected_table == 2){
            table_2_order = new Order(user,selected_table);
        }
        if(selected_table == 3){
            table_3_order = new Order(user,selected_table);
        }
        
        //Open order modifier page
        Stage modify_order = new Stage();
        modify_order.setAlwaysOnTop(true);
        modify_order.setTitle("Order Creator");
        FXMLLoader order_chooser_loader = new FXMLLoader(getClass().getResource("FXML_Add_Order.fxml"));
        try{
        Scene scene = new Scene(order_chooser_loader.load());
        FXML_Add_OrderController ctrl = order_chooser_loader.getController();
        modify_order.setScene(scene);
        ctrl.set_owner(user);
        ctrl.set_table(selected_table);
        ctrl.initialize_order();
        modify_order.show();
//        ctrl.set_order(table_to_order(selected_table));   //Not neccessary. Orders are public
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void cancel_order(ActionEvent a){
        
        // no table selected = try again
        if(selected_table == 0){
            Order_Erro_Label.setText("Please select a table");
            return;
        }
        
        //no order = can't delete
        if(table_to_order(selected_table) == null){
            Order_Erro_Label.setText("There is no order to delete.");
            return;
        }
        
        // table selected = delete current order
        if(selected_table == 1){
            table_1_order = null;
        }
        if(selected_table == 2){
            table_2_order = null;
        }       
        if(selected_table == 3){
            table_3_order = null;
        }
        
        System.out.println("Order deleted!");
    }
    
    @FXML 
    private void close_order(ActionEvent a){
        // no table selecte = try again
        if(selected_table == 0){
            return;
        }
        
        // table selected = delete current order        
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
    }
    
    public Order table_to_order(int i){
        if(i == 1){
            return table_1_order;
        }
        if(i == 2){
            return table_2_order;
        }       
        if(i == 3){
            return table_3_order;
        }
        return null;
    }
}
