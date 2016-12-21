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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import misc_classes.Order;

/**
 * FXML Controller class
 *
 * @author Srdjan
 */
public class FXML_Warning_Delete_OrderController implements Initializable {

    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_back;
    
    int table_number;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void getTable(int i){
        table_number = i;
    }
    
    @FXML
    public void delete(ActionEvent e){
        if(table_number == 1){FXML_main_viewController.table_1_order = null;}
        if(table_number == 2){FXML_main_viewController.table_2_order = null;}
        if(table_number == 3){FXML_main_viewController.table_3_order = null;}
        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void back(ActionEvent e){
        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.close();
    }
}
