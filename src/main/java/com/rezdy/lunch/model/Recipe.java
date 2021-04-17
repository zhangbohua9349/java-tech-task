package com.rezdy.lunch.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "recipe")
public class Recipe
{

    @Id
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe", referencedColumnName = "title"), inverseJoinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "title"))
    private Set<Ingredient> ingredients;

    public String getTitle()
    {
        return title;
    }

    public Recipe setTitle(String title)
    {
        this.title = title;
        return this;
    }

    public Set<Ingredient> getIngredients()
    {
        return ingredients;
    }

    public Recipe setIngredients(Set<Ingredient> ingredients)
    {
        this.ingredients = ingredients;
        return this;
    }
}
