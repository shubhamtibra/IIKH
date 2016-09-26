/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iikh;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author SHUBHAM
 */
public class Recipe {
   
    // Name of the recipe and Instructions to make it.
   private String Name, Instructions;
   // List of Ingredients
   private List<String> Ingredients;
   
   Recipe() {
       Ingredients = new ArrayList<>();
   }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public List<String> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.Ingredients = ingredients;
    }

    public String getInstructions() {
        return Instructions;
    }

    public void setInstructions(String procedure) {
        this.Instructions = procedure;
    }
    
    @Override
    public String toString(){
        String ingrToString = "";
        int count = 1;
        for(String e: Ingredients){
            ingrToString += Integer.toString(count) + ". " + e + " ";
            count++;
        }
        return "Name:\n" + Name + "\nIngredients:\n"+ ingrToString + "\nInstructions:\n" + Instructions;
    }

}
