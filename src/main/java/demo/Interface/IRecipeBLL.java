package demo.Interface;

import java.io.IOException;

public interface IRecipeBLL {
    public String SearchRecipes(String ProductName) throws IOException, InterruptedException;
}
