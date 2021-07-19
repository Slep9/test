package com.example.marvel.repository

import com.example.marvel.data.marvel.ResponseClass
import io.reactivex.Flowable

interface RepositoryMarvel {
    fun getListAll(): Flowable<ResponseClass>
    fun searchHero(search: String): Flowable<ResponseClass>
    fun getListCharactersSkipPage(offset: Integer, limit: Integer): Flowable<ResponseClass>
}