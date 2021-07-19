package com.example.marvel.bd

import androidx.room.TypeConverter
import com.example.marvel.data.marvel.Series
import com.example.marvel.data.marvel.Stories

class ConverterStories {
    @TypeConverter
    fun fromUrl(thumbnail: Stories): String {

        return thumbnail.toString()
    }

    @TypeConverter
    fun toUrl(string: String): Stories {

        return Stories()
    }
}