package com.dhytodev.bakingapp.ui.recipelist;

import com.dhytodev.bakingapp.data.model.Recipe;
import com.dhytodev.bakingapp.data.network.RecipeService;
import com.dhytodev.bakingapp.ui.base.BaseInteractor;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by izadalab on 9/19/17.
 */

public class RecipeListInteractorImpl extends BaseInteractor implements RecipeListInteractor {

    public RecipeListInteractorImpl(RecipeService service) {
        super(service);
    }

    @Override
    public Observable<List<Recipe>> getRecipes() {
        return getService().fetchRecipesFromServer().flatMap(Observable::just);
    }
}
