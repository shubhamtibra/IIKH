/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iikh;

import java.io.FileNotFoundException;
import java.util.Scanner;

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
    
        System.out.println("########################################################\n"
                + "** Welcome to Interactive Intelligent Kitchen Helper. **\n"
                + "########################################################\n\n"
                + "Please input a choice from the list given below:\n");
        while(true){
            System.out.println("\n1. Browse Recipe Database.\n2. Add a new Recipe.\n"
                    + "3. Edit an existing Recipe.\n4. Search the meals by date.\n"
                    + "5. View an existing plan for the meals.\n"
                    + "6. Add a new Meal to the Plan.\n0. Quit.\n");
            Scanner io = new Scanner(System.in);
            int choice = io.nextInt();
            boolean quit = false;
            switch(choice){
                case 1:
                    System.out.println(Database.showRecipes());
                    break;
                case 2:
                    Database.addRecipe();
                    break;
                case 3:
                    Database.editRecipe();
                    break;
                case 5:
                    System.out.println(Database.showMeals());
                    break;
                case 6:
                    Database.addMeal();
                    break;
                case 4:
                    System.out.println(Database.searchByDate());
                    break;
                default:
                    quit = true;
                    break;
            }
            if(quit) {
                System.out.println("\nThanks for stopping by. See you again soon. :)\n");
                break;
            
            }
        
        }
 
        
    }
}
