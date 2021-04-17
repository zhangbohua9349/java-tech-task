package com.rezdy.lunch.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.rezdy.lunch.model.Ingredient;
import com.rezdy.lunch.model.Recipe;
import com.rezdy.lunch.repository.RecipeRepository;

@RunWith(PowerMockRunner.class)
public class LunchServiceTest
{
    @InjectMocks
    private LunchService lunchService;

    @Mock
    private RecipeRepository recipeRepository;

    private List<Recipe> recipes = new ArrayList<Recipe>();

    @Before
    public void setup()
    {
        if (recipes.isEmpty())
        {
            Ingredient eggs = new Ingredient();
            eggs.setTitle("Eggs");
            eggs.setBestBefore(LocalDate.parse("1999-01-01"));
            eggs.setUseBy(LocalDate.parse("2000-01-01"));

            Ingredient bread = new Ingredient();
            bread.setTitle("Bread");
            bread.setBestBefore(LocalDate.parse("2020-01-01"));
            bread.setUseBy(LocalDate.parse("2999-01-01"));

            Ingredient sausage = new Ingredient();
            sausage.setTitle("Sausage");
            sausage.setBestBefore(LocalDate.parse("2999-01-01"));
            sausage.setUseBy(LocalDate.parse("2999-01-01"));

            Recipe fryUp = new Recipe();
            fryUp.setTitle("Fry-up");
            fryUp.setIngredients(new HashSet<Ingredient>());
            fryUp.getIngredients().add(eggs);
            fryUp.getIngredients().add(sausage);

            Recipe hotdog = new Recipe();
            hotdog.setTitle("Hotdog");
            hotdog.setIngredients(new HashSet<Ingredient>());
            hotdog.getIngredients().add(bread);
            hotdog.getIngredients().add(sausage);

            recipes.add(fryUp);
            recipes.add(hotdog);
        }
    }

    @Test
    public void testGetNonExpiredRecipesOnDate()
    {
        when(recipeRepository
                .findRecipesWithOnlyNonExpiredIngredients(LocalDate.parse("1999-01-01")))
                        .thenReturn(recipes);

        List<Recipe> nonExpiredRecipes = lunchService.getNonExpiredRecipesOnDate("1999-01-01");

        assertEquals(2, nonExpiredRecipes.size());
        assertEquals("Hotdog", nonExpiredRecipes.get(0).getTitle());
        assertEquals("Fry-up", nonExpiredRecipes.get(1).getTitle());
    }

    @Test(expected = DateTimeParseException.class)
    public void testGetNonExpiredRecipesOnDate_WithDateParseException()
    {
        lunchService.getNonExpiredRecipesOnDate("19999-01-01");
    }

    @Test
    public void testGetRecipesWithTitle()
    {
        lunchService.getRecipesWithTitle("Hotdog");

        verify(recipeRepository, times(1)).findByTitleLike("%Hotdog%");
    }

    @Test
    public void testGetRecipesWithTitle_WithNoRecipeTitle()
    {
        lunchService.getRecipesWithTitle(null);

        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void testGetRecipesWithoutIngredients()
    {
        List<String> ingredientTitles = Arrays.asList("Bread");

        lunchService.getRecipesWithoutIngredients(ingredientTitles);

        verify(recipeRepository, times(1)).findRecipesWithoutIngredients(ingredientTitles);
    }

    @Test
    public void testGetRecipesWithoutIngredients_WithNoRecipeTitle()
    {
        lunchService.getRecipesWithoutIngredients(null);

        verify(recipeRepository, times(1)).findAll();
    }
}
