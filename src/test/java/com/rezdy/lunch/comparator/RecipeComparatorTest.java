package com.rezdy.lunch.comparator;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import com.rezdy.lunch.model.Ingredient;
import com.rezdy.lunch.model.Recipe;

public class RecipeComparatorTest
{
    @Test
    public void testRecipeComparator()
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

        List<Recipe> recipes = Arrays.asList(fryUp, hotdog);

        recipes.sort(new RecipeComparator());

        assertEquals("Hotdog", recipes.get(0).getTitle());
        assertEquals("Fry-up", recipes.get(1).getTitle());
    }
}
