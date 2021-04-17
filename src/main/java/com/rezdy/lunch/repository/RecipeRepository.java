package com.rezdy.lunch.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rezdy.lunch.model.Recipe;

/**
 * Repository for lunch APIs to find recipes with specified criteria from
 * database.
 * 
 * @author bohuazhang
 */
@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String>
{

    List<Recipe> findAll();

    List<Recipe> findByTitleLike(String title);

    @Query(value = "select TITLE from recipe where TITLE not in (select distinct recipe.title from recipe, ingredient, recipe_ingredient where recipe.TITLE = recipe_ingredient.recipe and recipe_ingredient.ingredient = ingredient.TITLE and ingredient.USE_BY <= ?1)", nativeQuery = true)
    List<Recipe> findRecipesWithOnlyNonExpiredIngredients(LocalDate useBy);

    @Query(value = "select TITLE from recipe where TITLE not in (select distinct lunch.recipe.title from lunch.recipe, lunch.ingredient, lunch.recipe_ingredient where lunch.recipe.TITLE = lunch.recipe_ingredient.recipe and lunch.recipe_ingredient.ingredient = lunch.ingredient.TITLE and lunch.ingredient.TITLE in (?1))", nativeQuery = true)
    List<Recipe> findRecipesWithoutIngredients(List<String> ingredientTitles);
}
