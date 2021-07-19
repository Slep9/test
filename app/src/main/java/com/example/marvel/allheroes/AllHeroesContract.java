package com.example.marvel.allheroes;

import com.example.marvel.data.marvel.Result;

import java.util.List;

public interface AllHeroesContract {
    interface View {
        void setPresenter(AllHeroesContract.Presenter presenter);

        void setResultResponse(List<Result> result);

        void addResultResponse(List<Result> result);

        void error(int code);
    }

    interface Presenter {
        void subscribe();

        void unsubscribe();

        void searchHero(String hero);

        void loadPage();
    }
}
