package com.example.marvel.data.marvel

data class ResponseClass(var data: Data,
                         val code: Integer,
                         val status: String,
                         val copyright: String,
                         val attributionText: String,
                         val attributionHTML: String) {
}