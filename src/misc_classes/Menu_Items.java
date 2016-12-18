/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misc_classes;

/**
 *
 * @author Srdjan
 */

//i.e: pasta, etc...
public class Menu_Items {
    private String name;
    private float cost;
    
    public Menu_Items(String name, float cost){
        this.name = name;
        this.cost = cost;
    }
    
    public String getName(){
        return this.name;
    }
    
    public float getCost(){
    return this.cost;
    }
}
