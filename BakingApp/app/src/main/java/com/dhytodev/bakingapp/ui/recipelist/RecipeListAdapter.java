package com.dhytodev.bakingapp.ui.recipelist;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dhytodev.bakingapp.R;
import com.dhytodev.bakingapp.data.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.grantland.widget.AutofitTextView;

/**
 * Created by izadalab on 9/20/17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    private List<Recipe> recipes;
    private RecipeListView mView;
    private Context context;

    public RecipeListAdapter(List<Recipe> recipes, RecipeListView mView) {
        this.recipes = recipes;
        this.mView = mView;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, parent, false);
        return new RecipeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.recipe = recipes.get(position);
        holder.recipeName.setText(holder.recipe.getName());
        holder.recipeServings.setText(String.format(context.getString(R.string.serving), holder.recipe.getServings()));

        Glide.with(context)
                .load(R.drawable.dummy_recipe_preview).asBitmap()
                .into(new BitmapImageViewTarget(holder.recipeImage) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);


                    }
                });

    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.recipe_image)
        ImageView recipeImage;
        @BindView(R.id.title_background)
        View titleBackground;
        @BindView(R.id.recipe_name)
        AutofitTextView recipeName;
        @BindView(R.id.recipe_servings)
        AutofitTextView recipeServings;

        private Recipe recipe ;

        public RecipeListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            RecipeListAdapter.this.mView.onRecipeClicked(recipe, view);
        }
    }
}
