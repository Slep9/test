package com.example.marvel.bd

import androidx.room.TypeConverter
import com.example.marvel.data.marvel.Comics
import com.example.marvel.data.marvel.Thumbnail

class ConverterComics {

    @TypeConverter
    fun fromUrl(thumbnail: Comics): String {

        return thumbnail.toString()
    }

    @TypeConverter
    fun toUrl(string: String): Comics {

        return Comics()
    }
}