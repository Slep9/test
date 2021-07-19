package com.example.marvel.repository;

import com.example.marvel.data.marvel.ResponseClass;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelApi {
    @GET("/v1/public/characters")
    Flowable<ResponseClass> getListCharacters();

    @GET("/v1/public/characters")
    Flowable<ResponseClass> searchHero(@Query("nameStartsWith") String search);

    @GET("/v1/public/characters")
    Flowable<ResponseClass> getListCharactersSkipPage(@Query("offset") int offset, @Query("limit") int limit);


}
