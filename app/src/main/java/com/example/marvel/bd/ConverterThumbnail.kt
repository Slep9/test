package com.example.marvel.bd

import androidx.room.TypeConverter
import com.example.marvel.data.marvel.Thumbnail
import com.example.marvel.data.marvel.Url

class ConverterThumbnail {

    @TypeConverter
    fun fromUrl(thumbnail: Thumbnail): String {

        return thumbnail.toStringRoom()
    }

    @TypeConverter
    fun toUrl(string: String): Thumbnail {

        val split = string.split('\n')
        val thumbnail = Thumbnail()
        thumbnail.path = split[0].split("=")[1]
        thumbnail.extension = split[1].split("=")[1]
        return thumbnail
    }

}