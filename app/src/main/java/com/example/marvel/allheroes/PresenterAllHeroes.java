package com.example.marvel.allheroes;

import android.util.Log;

import com.example.marvel.app.App;
import com.example.marvel.bd.AppDatabaseHeroes;
import com.example.marvel.bd.ResultDao;
import com.example.marvel.data.marvel.ResponseClass;
import com.example.marvel.data.marvel.Result;
import com.example.marvel.repository.RepositoryMarvel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.marvel.utils.Utils.COUNT_PAGE;
import static com.example.marvel.utils.Utils.TAG;

public class PresenterAllHeroes implements AllHeroesContract.Presenter {
    private RepositoryMarvel mRepositoryInt;
    private AllHeroesContract.View mView;
    private final CompositeDisposable mCompositeDisposable;
    private int mPage = 2;
    private Boolean isSearch = false;

    public PresenterAllHeroes(RepositoryMarvel mRepositoryInt, AllHeroesContract.View mView) {
        this.mRepositoryInt = mRepositoryInt;
        this.mView = mView;
        mCompositeDisposable = new CompositeDisposable();
        mView.setPresenter(this);
    }

    private static ResponseClass apply(ResponseClass it) {
        AppDatabaseHeroes database = App.getInstance().getDatabase();
        ResultDao resultDao = database.resultDao();
        List<Result> results = it.getData().getResults();
        results.forEach(result -> resultDao.insert(result));
        Log.d(TAG, "setResultResponse: " + resultDao.getAll().size());
        return it;
    }

    @Override
    public void subscribe() {
        Disposable disposable = mRepositoryInt.getListAll()
                .subscribeOn(Schedulers.io())
                .map(PresenterAllHeroes::apply)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseClass -> {
                    mView.setResultResponse(responseClass.getData().getResults());
                });

        mCompositeDisposable.add(disposable);
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void searchHero(String hero) {
        int length = hero.length();
        if (length == 0){
            isSearch = false;
            subscribe();
        } else if (length > 2) {
            isSearch = true;
            Disposable disposable = mRepositoryInt.searchHero(hero)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(responseClass -> {
                        mView.setResultResponse(responseClass.getData().getResults());
                    });
            mCompositeDisposable.add(disposable);
        }
    }

    @Override
    public void loadPage() {
        if (isSearch){
            return;
        }
        mPage ++;
        Log.d(TAG, "loadPage: " + mPage);
        Disposable disposable = mRepositoryInt.getListCharactersSkipPage(mPage * COUNT_PAGE, COUNT_PAGE)
                .subscribeOn(Schedulers.io())
                .map(PresenterAllHeroes::apply)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseClass -> {
                    mView.addResultResponse(responseClass.getData().getResults());
                });

        mCompositeDisposable.add(disposable);
    }
}
