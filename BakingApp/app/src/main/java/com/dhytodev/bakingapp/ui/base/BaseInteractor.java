package com.dhytodev.bakingapp.ui.base;


import com.dhytodev.bakingapp.data.network.RecipeService;

/**
 * Created by izadalab on 9/6/17.
 */

public class BaseInteractor implements MvpInteractor {

    private RecipeService service ;

    public BaseInteractor() {
    }

    public BaseInteractor(RecipeService service) {
        this.service = service;
    }

    @Override
    public RecipeService getService() {
        return service;
    }
}
