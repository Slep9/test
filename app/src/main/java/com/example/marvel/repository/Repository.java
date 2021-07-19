package com.example.marvel.repository;

import com.example.marvel.app.App;
import com.example.marvel.data.marvel.ResponseClass;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Flowable;

public class Repository implements RepositoryMarvel {

    @NotNull
    @Override
    public Flowable<ResponseClass> getListAll() {
        return App.getInstance().getMarvelApi().getListCharacters();
    }

    @NotNull
    @Override
    public Flowable<ResponseClass> searchHero(@NotNull String search) {
        return App.getInstance().getMarvelApi().searchHero(search);
    }


    @NotNull
    @Override
    public Flowable<ResponseClass> getListCharactersSkipPage(@NotNull Integer offset, @NotNull Integer limit) {
        return App.getInstance().getMarvelApi().getListCharactersSkipPage(offset, limit);
    }
}
