/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iikh;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 * @author SHUBHAM
 */
public class Meal {
    
    private String Name, Date;
    private List<Integer> theRecipes;
    private List<String> Grocery;
    
    Meal() {
        theRecipes = new ArrayList<>();
        Grocery = new ArrayList<>();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public List<Integer> getTheRecipes() {
        return theRecipes;
    }

    public void setTheRecipes(List<Integer> theRecipes) {
        this.theRecipes = theRecipes;
        setGrocery();
    }
    
    public void setGrocery(){
        List<Recipe> recipesDB = Database.getRecipes();
        for(Integer e: theRecipes){
            Grocery.addAll(recipesDB.get(e).getIngredients());
        }
        Grocery = new ArrayList<String>(new LinkedHashSet<String>(Grocery));

    }
    
    @Override
    public String toString(){
        String str = "Name of the Meal:\n-----------------\n" + Name + "\nDate:\n-----\n" + Date + "\nRecipes:\n--------\n";
        List<Recipe> recipesDB = Database.getRecipes();
        int count = 1;
        for (Integer e:theRecipes){
            str += "(" + Integer.toString(count) + "." + ") " + recipesDB.get(e).getName() + " ";
            count++;
        }
        str += "\nGrocery Needed for this meal:\n-----------------------------\n";
        count = 1;
        for (String e:Grocery){
            str += "(" + Integer.toString(count) + "." + ") " + e + " ";
            count++;
        }
        
        
        return str;
    }
    
    
}
