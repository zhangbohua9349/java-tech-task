package com.rezdy.lunch.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.rezdy.lunch.comparator.RecipeComparator;
import com.rezdy.lunch.model.Recipe;
import com.rezdy.lunch.repository.RecipeRepository;

/**
 * Service class for lunch APIs to handle the business logic for getting
 * required recipes and perform necessary transformations.
 * 
 * @author bohuazhang
 */
@Service
public class LunchService
{

    @Autowired
    private RecipeRepository recipeRepository;

    /**
     * Get all recipes which have only non expired ingredients based on use by
     * date. If no use by date provided, then use current date as use by date.
     * 
     * @param useBy
     * @return recipes with only non expired ingredients based on the use by
     *         date if provided; otherwise, recipes with only non expired
     *         ingredients based on current date
     */
    public List<Recipe> getNonExpiredRecipesOnDate(String useBy)
    {
        LocalDate useByDate = StringUtils.isEmpty(useBy) ? LocalDate.now() : LocalDate.parse(useBy);

        List<Recipe> recipesWithOnlyNonExpiredIngredients = recipeRepository
                .findRecipesWithOnlyNonExpiredIngredients(useByDate);

        recipesWithOnlyNonExpiredIngredients.sort(new RecipeComparator());

        return recipesWithOnlyNonExpiredIngredients;
    }

    /**
     * Get all recipes whose titles contains the specified keyword. If no
     * keyword provided, return all recipes.
     * 
     * @param recipeTitle
     * @return recipes whose titles contains the specified keyword if provided;
     *         otherwise, all recipes
     */
    public List<Recipe> getRecipesWithTitle(String recipeTitle)
    {
        if (StringUtils.isEmpty(recipeTitle))
        {
            return recipeRepository.findAll();
        }

        return recipeRepository.findByTitleLike(String.format("%%%s%%", recipeTitle));
    }

    /**
     * Get all recipes which does not include the specified ingredients. If no
     * ingredient provided, return all recipes.
     * 
     * @param ingredientTitles
     * @return recipes which does not include the specified ingredients if
     *         provided; otherwise, return all recipes.
     */
    public List<Recipe> getRecipesWithoutIngredients(List<String> ingredientTitles)
    {
        if (CollectionUtils.isEmpty(ingredientTitles))
        {
            return recipeRepository.findAll();
        }

        return recipeRepository.findRecipesWithoutIngredients(ingredientTitles);
    }
}
