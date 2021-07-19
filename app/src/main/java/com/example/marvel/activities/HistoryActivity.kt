package com.example.marvel.activities;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.allheroes.AdapterAllHeroes
import com.example.marvel.allheroes.ClickHero
import com.example.marvel.app.App
import com.example.marvel.data.marvel.Result
import com.example.marvel.utils.Utils.Companion.startActivityDetail
import java.util.*

class HistoryActivity: AppCompatActivity(),  ClickHero {
    private var adapterAllHeroes: AdapterAllHeroes? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val viewById = findViewById<RecyclerView>(R.id.all_heroes_rv)
        Thread(Runnable {
            adapterAllHeroes = AdapterAllHeroes(ArrayList(), this)
            viewById.adapter = adapterAllHeroes
            adapterAllHeroes!!.list = App.getInstance().database.resultDao().getAll().sortedBy { it.name }.toMutableList()
        }).start()
    }

    override fun click(result: Result) {
        startActivityDetail(this, result)
    }

}