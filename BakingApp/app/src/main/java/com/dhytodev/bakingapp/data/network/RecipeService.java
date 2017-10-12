package com.dhytodev.bakingapp.data.network;

import com.dhytodev.bakingapp.BuildConfig;
import com.dhytodev.bakingapp.data.model.Recipe;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by izadalab on 9/5/17.
 */

public interface RecipeService {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Observable<List<Recipe>> fetchRecipesFromServer();

    class ServiceGenerator {
        public static RecipeService instance() {
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

            final Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .build();
            return retrofit.create(RecipeService.class);
        }
    }
}
