package com.rezdy.lunch.comparator;

import java.util.Comparator;

import com.rezdy.lunch.model.Ingredient;

/**
 * Custom comparator for model Ingredient, which compares the ingredients by
 * ascending best before date.
 * 
 * @author bohuazhang
 */
public class IngredientComparator implements Comparator<Ingredient>
{

    @Override
    public int compare(Ingredient o1, Ingredient o2)
    {
        return o1.getBestBefore().compareTo(o2.getBestBefore());
    }
}
