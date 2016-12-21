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
public class Menu_Item {
    private String name;
    private float cost;
    
    public Menu_Item(String name, float cost){
        this.name = name;
        this.cost = cost;
    }
    
    @Override
    public String toString(){
        return(name + " £" + cost);
    }
    
    public String getName(){
        return name;
    }
    
    public float getCost(){
    return cost;
    }
}
