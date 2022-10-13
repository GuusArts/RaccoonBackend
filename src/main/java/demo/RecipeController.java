package demo;


import demo.DAL.User;
import demo.DAL.UserRepository;
import demo.Interface.IRecipeBLL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;



@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8080" })
@RestController
public class RecipeController {

    private IRecipeBLL _searchBLL;
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserRepository userRepo;
    public RecipeController(IRecipeBLL searchBLL) {
        _searchBLL = searchBLL;
    }

    @GetMapping ("/recipes")
    public String  GetRecipe() throws IOException, InterruptedException {

        return recipeService.GetRecipes();
    }


    @GetMapping("/OwnRecipes")
    public List<OwnRecipe> ShowOwnRecipes() {

       return recipeService.FindAllOwnRecipes();
    }

    @PutMapping("/OwnRecipes/{id}")
    public  ResponseEntity<Void> UpdateOwnRecipe(@PathVariable String id){
        OwnRecipe recipe = recipeService.deleteById(id);

        if (recipe != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/OwnRecipes/{id}")
    public ResponseEntity<Void> DeleteOwnRecipe(@PathVariable String id){
        OwnRecipe recipe = recipeService.deleteById(id);

        if (recipe != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/createrecipe")
    public ResponseEntity <Void> CreateOwnRecipe(@RequestBody OwnRecipe recipe){
         recipeService.CreateRecipe(recipe);

        if (recipe != null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search/{ProductName}")
    public String  SearchRecipes(@PathVariable String ProductName) throws IOException, InterruptedException {

        return _searchBLL.SearchRecipes(ProductName);
    }

    @GetMapping("/recipe/{id}")
    public OwnRecipe getrecipe(@PathVariable String id) {
        System.out.println("hello");
        return recipeService.findById(id);
    }

    @PutMapping("/Recipe/{id}")
    public ResponseEntity<OwnRecipe> UpdateRecipe(@PathVariable String id, @RequestBody OwnRecipe recipe){
        System.out.println("update");
        OwnRecipe recipeUpdated = recipeService.UpdateRecipe(recipe);


        return new ResponseEntity<OwnRecipe>(recipeUpdated, HttpStatus.OK);
    }


    @PostMapping("/raccoon/register")
    public User processRegister(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);

        return  user;
    }

}







