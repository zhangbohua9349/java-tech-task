package com.rezdy.lunch.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.server.ResponseStatusException;

import com.rezdy.lunch.model.Recipe;
import com.rezdy.lunch.service.LunchService;

@RunWith(PowerMockRunner.class)
public class LunchControllerTest
{
    @InjectMocks
    private LunchController lunchController;

    @Mock
    private LunchService lunchService;

    @Test
    public void testGetNonExpiredRecipes()
    {
        lunchController.getNonExpiredRecipes("1999-01-01");

        verify(lunchService, times(1)).getNonExpiredRecipesOnDate("1999-01-01");
    }

    @Test
    public void testGetRecipesWithTitle()
    {
        when(lunchService.getRecipesWithTitle("Hotdog")).thenReturn(Arrays.asList(new Recipe()));

        lunchController.getRecipesWithTitle("Hotdog");

        verify(lunchService, times(1)).getRecipesWithTitle("Hotdog");
    }

    @Test(expected = ResponseStatusException.class)
    public void testGetRecipesWithTitle_WithoutNoRecipeFound()
    {
        lunchController.getRecipesWithTitle("Hotdog");

        verify(lunchService, times(1)).getRecipesWithTitle("Hotdog");
    }

    @Test
    public void testGetRecipesWithoutIngredients()
    {
        List<String> ingredientTitles = Arrays.asList("Bread");

        lunchController.getRecipesWithoutIngredients(ingredientTitles);

        verify(lunchService, times(1)).getRecipesWithoutIngredients(ingredientTitles);
    }
}
