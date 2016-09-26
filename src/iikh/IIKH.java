/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iikh;

import java.io.FileNotFoundException;

/**
 *
 * @author SHUBHAM
 */
public class IIKH {

    /**
     * @param args the command line arguments
     * 
     */
    public static void main(String[] args) throws FileNotFoundException{
    
        Database.addMeal();
        System.out.println(Database.showMeals());
        System.out.println(Database.showRecipes());
 
        
    }
}
