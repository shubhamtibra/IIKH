/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iikh;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
import java.util.Arrays;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
/**
 *
 * @author SHUBHAM
 */
public class Database {
    
    // Store the recipes in a list.
    private static List<Recipe> recipesDB;
    private static List<Meal> mealsDB;
    
    static {
        
        try {
           /* read the recipes and meals
            * from txt files and store them
            * in the class.
            */
            
            readRecipesFromtxt();
            readMealsFromtxt();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Recipe> getRecipes() {
        return recipesDB;
    }
    
    // writes the recipes to recipeDB.txt
    static void writeRecipestotxt()throws FileNotFoundException{
        
        try{
            File file = new File("recipeDB.txt");
            try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
                String str = converRecipestoString();
                writer.println(str);
            }
    }
        catch(FileNotFoundException ex){
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // writes the meals to txt files for later use.
    static void writeMealstotxt()throws FileNotFoundException{
        
        try{
            File file = new File("mealDB.txt");
            try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
                String str = converMealstoString();
                writer.println(str);
            }
    }
        catch(FileNotFoundException ex){
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // convert the database to string format to be stored.
    static String converRecipestoString(){
        String str = "";
        
        for (Recipe element : recipesDB) { 
            String ingr = "";
            for(String e: element.getIngredients()){
                ingr += e + "@@";
            }
            str += element.getName() + ",," + ingr + ",," + element.getInstructions()+ "\n";
        }
        return str;
    }
    
    static String converMealstoString(){
        String str = "";
        for(Meal element: mealsDB){
            String rcps = "";
            for(Integer e: element.getTheRecipes()){
                rcps += Integer.toString(e) + "@@";
            }
        str += element.getName()+ ",," + rcps + ",," + element.getDate() + "\n";
        }
        return str;
    }
    
    // reads recipeDB.txt and stores to the database
    static void readRecipesFromtxt()throws FileNotFoundException{
        
        List<Recipe> recipesread = new ArrayList<>();
        Scanner scan = new Scanner(new File("recipeDB.txt"));
        
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            //Here you can manipulate the string the way you want
            List<String> linearray = Arrays.asList(line.split(",,"));
            if (linearray.size() !=3 ) break;
            Recipe r1 = new Recipe();
            r1.setName(linearray.get(0));
            String ingr = linearray.get(1);
            List<String> ingrbylist = Arrays.asList(ingr.split("@@"));
            r1.setIngredients(ingrbylist);
            r1.setInstructions(linearray.get(2));
            recipesread.add(r1);
        
    }
    recipesDB = recipesread;
    }
    
    // similar to readRecipesFromtxt
    static void readMealsFromtxt()throws FileNotFoundException{
        
        List<Meal> mealsread = new ArrayList<>();
        Scanner scan = new Scanner(new File("mealDB.txt"));
        
        while(scan.hasNextLine()){
            String line = scan.nextLine();
            //Here you can manipulate the string the way you want
            List<String> linearray = Arrays.asList(line.split(",,"));
            if (linearray.size() !=3 ) break;
            Meal r1 = new Meal();
            r1.setName(linearray.get(0));
            String rcps = linearray.get(1);
            List<String> rcps_s = Arrays.asList(rcps.split("@@"));
            List<Integer> rcps_I = new ArrayList<>();
            for(String element: rcps_s){
                int parse = Integer.parseInt(element);
                rcps_I.add(parse);
            }
            r1.setTheRecipes(rcps_I);
            r1.setDate(linearray.get(2));
            mealsread.add(r1);
        
    }
    mealsDB = mealsread;
    }
    
    // adds a new recipe to the database
    static void addRecipe()throws FileNotFoundException{
        String name_a = "", procedure_a = "";
        Recipe x_a = new Recipe();
        System.out.println("Enter the name of the recipe:\n");
        Scanner take = new Scanner(System.in);
        name_a = take.nextLine();
        x_a.setName(name_a);
        System.out.println("Enter the ingredients:\n");
        List<String> ingredients_a = Arrays.asList(take.nextLine().split(", "));
        x_a.setIngredients(ingredients_a);
        System.out.println("Give the Instructions:\n");
        procedure_a = take.nextLine();
        x_a.setInstructions(procedure_a);
        recipesDB.add(x_a);
        writeRecipestotxt();
        
    }
    
    // edit some existing recipe
    static void editRecipe()throws FileNotFoundException{
        Scanner Read;
        Read = new Scanner(System.in);
        int id_r;
        System.out.println("Which recipe would like to change?");
        id_r = Read.nextInt()-1;
        System.out.println("What do you want to edit?\n1. Name\n2. Ingredients\n3. Procedure\n4. All\n");
        int c = Read.nextInt();
        Read.nextLine();
        String n_name, n_procedure;
        List<String> n_ingredients;
        
        switch(c){
            
            case 1:
                System.out.println("Enter the new name");
                n_name = Read.nextLine();
                recipesDB.get(id_r).setName(n_name);
                break;
                
            case 2:
                System.out.println("Enter the new ingredients");
                String p = Read.nextLine();
                n_ingredients = Arrays.asList(p.split(", "));
                recipesDB.get(id_r).setIngredients(n_ingredients);
                break;
                
            case 3:
                System.out.println("Enter a new procedure");
                n_procedure = Read.nextLine();
                recipesDB.get(id_r).setInstructions(n_procedure);
                break;
            default:
                System.out.println("Enter the new name");
                n_name = Read.nextLine();
                recipesDB.get(id_r).setName(n_name);
                System.out.println("Enter the new ingredients");
                String q = Read.nextLine();
                n_ingredients = Arrays.asList(q.split(", "));
                recipesDB.get(id_r).setIngredients(n_ingredients);
                 System.out.println("Enter a new procedure");
                n_procedure = Read.nextLine();
                recipesDB.get(id_r).setInstructions(n_procedure);
                
        }
        writeRecipestotxt();
    }
    
    // add a new meal to the plan manager.
    static void addMeal() throws FileNotFoundException{
        String name_a = "", date_a = "";
        List<Integer> recipes_a = new ArrayList<>();
        Meal x_a = new Meal();
        System.out.println("Enter the name of the Meal");
        Scanner take = new Scanner(System.in);
        name_a = take.nextLine();
        x_a.setName(name_a);
        System.out.println("Give the date.");
        date_a = take.nextLine();
        x_a.setDate(date_a);
        System.out.println("Give the recipe numbers in this Plan");
        List<String> p = Arrays.asList(take.nextLine().split(", "));
        
        for (String e: p){
            recipes_a.add(Integer.parseInt(e)-1);
        }
        x_a.setTheRecipes(recipes_a);
        mealsDB.add(x_a);
        writeMealstotxt();
    }
    
    public static String showRecipes(){
        String r;
        int x = 0;
        r = "";
        for(Recipe element: recipesDB){
            if (x==1) r += "\n\n";
            r += element.toString();
            x = 1;
        }
        return r;
    }
    
    public static String showMeals(){
        String r;
        int x = 0;
        r = "";
        for(Meal element: mealsDB){
            if (x==1) r += "\n\n";
            r += element.toString();
            x = 1;
        }
        return r;
    }
    
}
