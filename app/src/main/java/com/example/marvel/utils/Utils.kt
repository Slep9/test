package com.example.marvel.utils

import android.content.Context
import android.content.Intent
import com.example.marvel.data.marvel.Result
import com.example.marvel.activities.DetailActivity
import okhttp3.internal.and
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class Utils {

    companion object{
        const val COUNT_PAGE = 10
        const val TAG = "Marvel"

        @JvmStatic
        fun MD5(md5: String): String?{
            try {
                val md = MessageDigest.getInstance("MD5")
                val array = md.digest(md5.toByteArray())
                val sb = StringBuffer()
                for (i in array.indices) {
                    sb.append(Integer.toHexString(array[i] and 0xFF or 0x100).substring(1, 3))
                }
                return sb.toString()
            } catch (e: NoSuchAlgorithmException) {
            }
            return null
        }

        @JvmStatic
        fun ts(): String = (Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000L).toString()

        @JvmStatic
        fun startActivityDetail(context: Context, result: Result){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("url", result.thumbnail.path + "." + result.thumbnail.extension)
            intent.putExtra("description", result.description)
            intent.putExtra("name", result.name)
            context.startActivity(intent)
        }
    }
}



