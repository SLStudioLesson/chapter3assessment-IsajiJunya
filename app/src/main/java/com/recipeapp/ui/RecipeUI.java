package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.recipeapp.datahandler.CSVDataHandler;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    CSVDataHandler csvDataHandler = new CSVDataHandler();

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {

        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes();
                        break;
                    case "2":
                        addNewRecipe();
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }
    private void displayRecipes(){
        try {
            ArrayList<Recipe> recipes = dataHandler.readData();
            if(recipes.isEmpty()){
                System.out.println("No recipes available.");
            }else{
                System.out.println("Recipes: ");

                for(Recipe recipe : recipes){
                    System.out.println("--------------------------------");
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.println("Main Ingredints: ");

                    ArrayList<String> ingredientNames = new ArrayList<>();
                    for(Ingredient ingredient : recipe.getIngredients()){
                        ingredientNames.add(ingredient.getName());
                    }
                    System.out.println(String.join(",", ingredientNames));
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    private void addNewRecipe(){
        try {
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input1;
            System.err.println("Adding a new recipe.");
            System.out.println("Enter recipe name: ");
            input1 = reader.readLine();
            System.out.println("Enter ingredients (type 'done' when finished): ");
            while (true) {
                System.out.print("Ingredient:");
                String input2 = reader.readLine();
                if(input2.equals("done")){
                    break;
                }else{
                    ingredients.add(new Ingredient(input2));
                }
            }
            Recipe nRecipe = new Recipe(input1, ingredients);
            csvDataHandler.writeData(nRecipe);
            } catch (IOException e) {
            System.err.println("Failed to add new recipe: " + e.getMessage());
        }
    }
}
