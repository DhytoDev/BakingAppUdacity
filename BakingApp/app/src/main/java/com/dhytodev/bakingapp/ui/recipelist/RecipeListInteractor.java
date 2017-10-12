package com.dhytodev.bakingapp.ui.recipelist;

import com.dhytodev.bakingapp.data.model.Recipe;
import com.dhytodev.bakingapp.ui.base.MvpInteractor;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by izadalab on 9/19/17.
 */

public interface RecipeListInteractor extends MvpInteractor{

    Observable<List<Recipe>> getRecipes();
}
