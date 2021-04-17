package com.rezdy.lunch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rezdy.lunch.model.Recipe;
import com.rezdy.lunch.service.LunchService;

/**
 * Controller class for the lunch APIs.
 * 
 * @author bohuazhang
 */
@RestController
public class LunchController
{
    @Autowired
    private LunchService lunchService;

    @GetMapping("/lunch/recipes/nonExpired")
    public List<Recipe> getNonExpiredRecipes(
            @RequestParam(value = "useBy", required = false) String useBy)
    {
        return lunchService.getNonExpiredRecipesOnDate(useBy);
    }

    @GetMapping("/lunch/recipes/withTitle")
    public List<Recipe> getRecipesWithTitle(
            @RequestParam(value = "recipeTitle", required = false) String recipeTitle)
    {
        List<Recipe> recipesWithTitle = lunchService.getRecipesWithTitle(recipeTitle);

        if (CollectionUtils.isEmpty(recipesWithTitle))
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Unable to find recipe with title: %s", recipeTitle));
        }

        return recipesWithTitle;
    }

    @GetMapping("/lunch/recipes/withoutIngredients")
    public List<Recipe> getRecipesWithoutIngredients(
            @RequestParam(value = "ingredientTitle", required = false) List<String> ingredientTitles)
    {
        return lunchService.getRecipesWithoutIngredients(ingredientTitles);
    }
}
