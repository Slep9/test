package com.example.marvel.bd

import androidx.room.TypeConverter
import com.example.marvel.data.marvel.Events
import com.example.marvel.data.marvel.Thumbnail

class ConverterEvents {
    @TypeConverter
    fun fromUrl(thumbnail: Events): String {

        return thumbnail.toString()
    }

    @TypeConverter
    fun toUrl(string: String): Events {

        return Events ()
    }
}