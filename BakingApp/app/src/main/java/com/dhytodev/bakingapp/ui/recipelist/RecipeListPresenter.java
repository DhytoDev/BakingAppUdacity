package com.dhytodev.bakingapp.ui.recipelist;

import com.dhytodev.bakingapp.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by izadalab on 9/19/17.
 */

public class RecipeListPresenter<V extends RecipeListView, I extends RecipeListInteractor> extends BasePresenter<V, I> {

    public RecipeListPresenter(I mvpInteractor, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, compositeDisposable);
    }

    public void displayRecipes() {
        getMvpView().showLoading(true);

        getCompositeDisposable()
                .add(getInteractor().getRecipes()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(recipes -> {
                            getMvpView().showLoading(false);
                            if (recipes != null && recipes.size() > 0) {
                                getMvpView().displayRecipes(recipes);
                            }
                        }, throwable -> {
                            getMvpView().showLoading(false);
                            getMvpView().onError(throwable.getLocalizedMessage());
                        }));
    }
}
