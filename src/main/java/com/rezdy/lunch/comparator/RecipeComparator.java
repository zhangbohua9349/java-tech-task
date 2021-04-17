package com.rezdy.lunch.comparator;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.NoSuchElementException;

import com.rezdy.lunch.model.Ingredient;
import com.rezdy.lunch.model.Recipe;

/**
 * Custom comparator for model Recipe, which compares the recipes by the
 * descending earliest best before date of their ingredients.
 * 
 * @author bohuazhang
 */
public class RecipeComparator implements Comparator<Recipe>
{

    @Override
    public int compare(Recipe recipe1, Recipe recipe2)
    {
        LocalDate earliestIngredientBestBeforeDate1 = getEarliestIngredientBestBeforeDate(recipe1);
        LocalDate earliestIngredientBestBeforeDate2 = getEarliestIngredientBestBeforeDate(recipe2);

        return -earliestIngredientBestBeforeDate1.compareTo(earliestIngredientBestBeforeDate2);
    }

    private LocalDate getEarliestIngredientBestBeforeDate(Recipe recipe)
    {
        return recipe.getIngredients().stream().sorted(new IngredientComparator()).findFirst()
                .map(Ingredient::getBestBefore).orElseThrow(NoSuchElementException::new);
    }
}
