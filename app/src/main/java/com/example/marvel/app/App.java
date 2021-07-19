package com.example.marvel.app;

import android.app.Application;

import androidx.room.Room;

import com.example.marvel.bd.AppDatabaseHeroes;
import com.example.marvel.repository.MarvelApi;
import com.example.marvel.utils.Utils;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class App extends Application {
    private static final String API_KEY = "API_KEY";
    private static final String PRIVATE_KEY = "PRIVATE_KEY";
    private static App instance;
    private static AppDatabaseHeroes database;
    private static MarvelApi marvelApi;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDB();
        initRetrofit();
    }

    private void initRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @NotNull
            @Override
            public Response intercept(@NotNull Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl  = original.url();

                String ts = Utils.ts();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("apikey", API_KEY)
                        .addQueryParameter("ts", ts)
                        .addQueryParameter("hash", Utils.MD5(ts + PRIVATE_KEY + API_KEY))
                        .build();

                return chain.proceed(original.newBuilder().url(url).build());
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gateway.marvel.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .client(httpClient.build())
                .build();

        marvelApi = retrofit.create(MarvelApi.class);
    }

    private void initDB() {
        database = Room.databaseBuilder(this, AppDatabaseHeroes.class, "data")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabaseHeroes getDatabase() {
        return database;
    }

    public MarvelApi getMarvelApi() {
        return marvelApi;
    }

}