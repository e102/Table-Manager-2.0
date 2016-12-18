/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc_classes;

import java.util.ArrayList;

/**
 *
 * @author Srdjan
 */
public class Order {
    private User owner;
    private int table;
    private float totalPrice = 0;
    private ArrayList<Menu_Items> contents = new ArrayList();
    
    public Order(User owner, int table){
        this.owner = owner;
        this.table = table;
    }
    
    public void addItem(Menu_Items item){
        contents.add(item);
        findTotalPrice();
    }
    
    public void removeItem(Menu_Items item){
        contents.remove(item);
        findTotalPrice();
    }
    

    private void findTotalPrice(){
        totalPrice = 0;
        for (int i = 0; i < contents.size(); i++) {
            totalPrice += contents.get(i).getCost();
        }
    }
}
