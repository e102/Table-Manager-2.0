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
    private ArrayList<Menu_Item> contents = new ArrayList();
    private String comment = "";
    
    public Order(User owner, int table){
        this.owner = owner;
        this.table = table;
    }
    
    public Order(){
    }
    
    @Override
    public String toString(){
        return owner.getName();
    }
    
    public void addItem(Menu_Item item){
        contents.add(item);
        totalPrice += findTotalPrice();
    }
    
    public void removeItem(Menu_Item item){
        contents.remove(item);
        totalPrice -= findTotalPrice();
    }
    
    private float findTotalPrice(){
        int total = 0;
        for (int i = 0; i < contents.size(); i++) {
            total += contents.get(i).getCost();
        }
        return total;
    }
    
    private float getPrice(){
        return this.totalPrice;
    }
    
    public ArrayList<Menu_Item> get_contents(){
        return this.contents;
    }
}
