package com.recipeapp.model;

public class Ingredient {
    //材料の名前
    private String name;

    public Ingredient(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

}
