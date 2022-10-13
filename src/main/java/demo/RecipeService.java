package demo;

import demo.Interface.IRecipeBLL;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class RecipeService implements IRecipeBLL {

    private static  List<OwnRecipe>  RecipeList = new ArrayList<>();
    public String GetRecipes() throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://edamam-recipe-search.p.rapidapi.com/search?q=chicken"))
                .header("X-RapidAPI-Key", "8c06667cd5msh7a2d09e4a18676ap159e79jsn593e5f48e34f")
                .header("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());



        return response.body();
    }




static {
    RecipeList.add(new OwnRecipe(455,Arrays.asList("Paprika", "Tomaat", "Wortel", "Rundvlees"), "Rode stampot", "Alles bakken en dan alles bij elkaar stampen.", "1", 1.5, true, false, 4));
    RecipeList.add(new OwnRecipe(463,Arrays.asList("Worst", "Tomaat", "Pompoen", "Spinazie"), "Spinazie Worst", "Pak een worst en stop er spinazie overheen.", "2", 1.0, true, false, 5  ));
    RecipeList.add(new OwnRecipe(553,Arrays.asList("Paprika", "Worst", "Wortel", "Aardappel"), "Wortel Stampot met worst", "Wortel en stampot samen stampen en dan een worst er bij leggen.", "3", 0.5,true, false,4));

}

public List<OwnRecipe> FindAllOwnRecipes(){
       return RecipeList;
}


    public OwnRecipe deleteById(String id) {
        OwnRecipe recipe = findById(id);

        if (recipe == null)
            return null;

        if (RecipeList.remove(recipe)) {
            return recipe;
        }

        return null;
    }

    public OwnRecipe findById(String id) {

        for (OwnRecipe recipe : RecipeList) {
            if (Objects.equals(recipe.GetRecipeId(), id)) {
                return recipe;
            }
        }

        return null;
    }

    public String SearchRecipes(String ProductName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://edamam-recipe-search.p.rapidapi.com/search?q=" + ProductName ))
                .header("X-RapidAPI-Key", "8c06667cd5msh7a2d09e4a18676ap159e79jsn593e5f48e34f")
                .header("X-RapidAPI-Host", "edamam-recipe-search.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return response.body();
    }


public void CreateRecipe(OwnRecipe ownRecipe){
        RecipeList.add(ownRecipe);

}

public OwnRecipe UpdateRecipe(OwnRecipe ownRecipe){
        deleteById(ownRecipe.RecipeId);
        RecipeList.add(ownRecipe);

    return ownRecipe;
}



}