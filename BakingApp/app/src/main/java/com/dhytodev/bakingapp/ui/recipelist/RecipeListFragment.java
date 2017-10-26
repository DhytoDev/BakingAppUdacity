package com.dhytodev.bakingapp.ui.recipelist;

import android.os.Bundle;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dhytodev.bakingapp.R;
import com.dhytodev.bakingapp.data.model.Recipe;
import com.dhytodev.bakingapp.data.network.RecipeService;
import com.dhytodev.bakingapp.ui.base.BaseFragment;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by izadalab on 9/19/17.
 */

public class RecipeListFragment extends BaseFragment implements RecipeListView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recipes_rv)
    RecyclerView recipesRv;
    @BindView(R.id.loading_pb)
    ProgressBar loadingPb;
    @BindInt(R.integer.grid_column_count)
    int gridColumnCount;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private RecipeListInteractor mInteractor;
    private RecipeListPresenter<RecipeListView, RecipeListInteractor> mPresenter;
    private RecipeListAdapter mAdapter ;
    private List<Recipe> recipes = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        mInteractor = new RecipeListInteractorImpl(RecipeService.ServiceGenerator.instance());
        mPresenter = new RecipeListPresenter<>(mInteractor, new CompositeDisposable());
        mPresenter.onAttach(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.displayRecipes();
        refresh.setOnRefreshListener(this);
    }

    @Override
    protected void setUp(View view) {
        recipesRv.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), gridColumnCount);
        recipesRv.setLayoutManager(layoutManager);

        mAdapter = new RecipeListAdapter(recipes, this);
        recipesRv.setAdapter(mAdapter);
    }

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            recipesRv.setVisibility(View.VISIBLE);
            loadingPb.setVisibility(View.VISIBLE);
            refresh.setVisibility(View.GONE);
        } else {
            loadingPb.setVisibility(View.GONE);
            refresh.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void displayRecipes(List<Recipe> recipes) {
        this.recipes.clear();
        this.recipes.addAll(recipes);
        recipesRv.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRecipeClicked(Recipe recipe, View view) {

    }

    @Override
    public void onRefresh() {
        refresh.setRefreshing(false);
        mPresenter.displayRecipes();
    }
}
