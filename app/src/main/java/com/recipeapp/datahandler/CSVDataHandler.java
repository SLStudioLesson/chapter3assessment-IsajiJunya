package com.recipeapp.datahandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;




import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler{
    public String filePath;

    public CSVDataHandler(){
        this.filePath = "app/src/main/resources/recipes.csv";
    }
    public CSVDataHandler(String filePath){
        this.filePath = filePath;
    }
    public String getMode(){
        return "CSV";
    }
    public ArrayList<Recipe> filerecipe = new ArrayList<>();
    String line;
    public ArrayList<Recipe> readData(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            while ((line = reader.readLine()) != null) {
            
            String[] parts = line.split(",");

            String recipeName = parts[0].trim();

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            for (int i = 1; i < parts.length; i++) {
                ingredients.add(new Ingredient(parts[i].trim()));
            }
    
            Recipe recipe = new Recipe(recipeName, ingredients);
            filerecipe.add(recipe);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return filerecipe;
    }
    public void writeData(Recipe recipe){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            
            String recipeName = recipe.getName();
            
            StringBuilder ingredientsList = new StringBuilder();
            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredientsList.append(ingredient.getName()).append(", ");
            }

            String recipeLine = recipeName + "," + ingredientsList.toString();
            writer.write(recipeLine);
            writer.newLine();

            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.err.println("Failed to add new recipe: " + e.getMessage());
        }
    }
    public ArrayList<Recipe> searchData(String keyword){
        return null;
    }
}
