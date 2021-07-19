package com.example.marvel.bd

import androidx.room.TypeConverter
import com.example.marvel.data.marvel.Url
import java.util.stream.Collectors

class ConverterUrl {

    @TypeConverter
    fun fromUrl(url: List<Url>): String {

        return url.toString()
    }

    @TypeConverter
    fun toUrl(string: String): List<Url> {
        var list: MutableList<Url> = mutableListOf()

        return list
    }
}