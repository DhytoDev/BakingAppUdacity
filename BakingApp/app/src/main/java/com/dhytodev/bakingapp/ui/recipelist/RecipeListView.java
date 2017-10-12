package com.dhytodev.bakingapp.ui.recipelist;

import android.view.View;

import com.dhytodev.bakingapp.data.model.Recipe;
import com.dhytodev.bakingapp.ui.base.MvpView;

import java.util.List;

/**
 * Created by izadalab on 9/19/17.
 */

public interface RecipeListView extends MvpView {
    void showLoading(boolean isLoading);
    void displayRecipes(List<Recipe> recipes);
    void onRecipeClicked(Recipe recipe, View view);
}
