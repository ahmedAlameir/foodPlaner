package com.example.foodplaner.Model;

public class Ingredient {
    public String ingredientName;
    public String ingredientMeasure;
    public String ingredientThumb;

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setIngredientMeasure(String ingredientMeasure) {
        this.ingredientMeasure = ingredientMeasure;
    }

    public void setIngredientThumb(String ingredientThumb) {
        this.ingredientThumb = ingredientThumb;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getIngredientMeasure() {
        return ingredientMeasure;
    }

    public String getIngredientThumb() {
        return ingredientThumb;
    }

    public Ingredient(String ingredientName, String ingredientMeasure, String ingredientThumb) {
        this.ingredientName = ingredientName;
        this.ingredientMeasure = ingredientMeasure;
        this.ingredientThumb = ingredientThumb;
    }
}
