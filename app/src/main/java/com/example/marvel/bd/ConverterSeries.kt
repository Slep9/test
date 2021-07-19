package com.example.marvel.bd

import androidx.room.TypeConverter
import com.example.marvel.data.marvel.Comics
import com.example.marvel.data.marvel.Series

class ConverterSeries {


    @TypeConverter
    fun fromUrl(thumbnail: Series): String {

        return thumbnail.toString()
    }

    @TypeConverter
    fun toUrl(string: String): Series {

        return Series()
    }
}