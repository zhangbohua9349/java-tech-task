package com.rezdy.lunch.comparator;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.rezdy.lunch.model.Ingredient;

public class IngredientComparatorTest
{
    @Test
    public void testIngredientComparator()
    {
        Ingredient eggs = new Ingredient();
        eggs.setTitle("Eggs");
        eggs.setBestBefore(LocalDate.parse("1999-01-01"));
        eggs.setUseBy(LocalDate.parse("2000-01-01"));

        Ingredient sausage = new Ingredient();
        sausage.setTitle("Sausage");
        sausage.setBestBefore(LocalDate.parse("2999-01-01"));
        sausage.setUseBy(LocalDate.parse("2999-01-01"));

        Ingredient bread = new Ingredient();
        bread.setTitle("Bread");
        bread.setBestBefore(LocalDate.parse("2020-01-01"));
        bread.setUseBy(LocalDate.parse("2999-01-01"));

        List<Ingredient> ingredients = Arrays.asList(eggs, sausage, bread);

        ingredients.sort(new IngredientComparator());

        assertEquals("Eggs", ingredients.get(0).getTitle());
        assertEquals("Bread", ingredients.get(1).getTitle());
        assertEquals("Sausage", ingredients.get(2).getTitle());
    }
}
