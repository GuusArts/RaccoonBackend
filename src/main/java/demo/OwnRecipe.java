package demo;

import java.util.List;

public class OwnRecipe {
    public int Calories;
    public List<String> Ingredients;
    public String Name;

    public String PreperationMethod;
    public String RecipeId;

    public Double Spicyness;

    public String GetRecipeId(){
        return RecipeId;
    }

    public Boolean Nutfree;

    public Boolean IsVegan;

    public Integer Portions;


    public OwnRecipe(int calories, List<String> ingredients, String name, String preperationMethod, String recipeId, Double spicyness, Boolean nutfree, Boolean isVegan, Integer portions) {
        Calories = calories;
        Ingredients = ingredients;
        Name = name;
        PreperationMethod = preperationMethod;
        RecipeId = recipeId;
        Spicyness = spicyness;
        Nutfree = nutfree;
        IsVegan = isVegan;
        Portions = portions;
    }
}
