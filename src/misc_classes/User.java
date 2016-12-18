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
public class User {
    private boolean isAdmin;
    private String password;
    private String name;
    
    public User(String name, String password, boolean isAdmin){
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public boolean isAdmin(){
        if(isAdmin == true){
            return true;
        }
        else{
            return false;
        }
    }
}
