package com.example.marvel.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.allheroes.AdapterAllHeroes
import com.example.marvel.allheroes.AllHeroesContract
import com.example.marvel.allheroes.ClickHero
import com.example.marvel.allheroes.PresenterAllHeroes
import com.example.marvel.data.marvel.Result
import com.example.marvel.fragments.ErrorFragment
import com.example.marvel.repository.Repository
import com.example.marvel.utils.Utils
import com.example.marvel.utils.Utils.Companion.startActivityDetail
import java.util.*


class MainActivity : AppCompatActivity(), ClickHero, AllHeroesContract.View {
    var mPresenter: AllHeroesContract.Presenter? = null
    var mAdapterAllHeroes: AdapterAllHeroes? = null
    var mSearchET: EditText? = null
    var mContainerOk: View? = null
    var mContainerError: View? = null
    var errorFragment: ErrorFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSearchET = findViewById(R.id.search)
        mContainerOk = findViewById<View>(R.id.container_ok)
        mContainerError = findViewById<View>(R.id.container_error)
        mSearchET?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mPresenter?.searchHero(s.toString())
            }
        })

        PresenterAllHeroes(Repository(), this).also { it.subscribe() }

        val rv: RecyclerView = findViewById(R.id.all_heroes_rv)
        mAdapterAllHeroes = AdapterAllHeroes(mutableListOf(), this)
        rv.adapter = mAdapterAllHeroes

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (isLastItem(recyclerView)){
                    Log.d(Utils.TAG, "isLastItem: ${recyclerView.adapter!!.itemCount}")
                    mPresenter?.loadPage()
                }
            }

            private fun isLastItem(recyclerView: RecyclerView): Boolean {
                if (recyclerView.adapter?.itemCount!! > 8 ){
                    var lastVisiblePosition = (recyclerView.layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()
                    if (lastVisiblePosition != RecyclerView.NO_POSITION && recyclerView.adapter!!.itemCount - 1 == lastVisiblePosition){
                        return true
                    }
                }
                return false
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

    }

    override fun click(result: Result) {
        startActivityDetail(this, result)
    }

    override fun addResultResponse(result: MutableList<Result>?) {
        result?.let {

            var list = ArrayList(mAdapterAllHeroes?.list)
            list.addAll(list.size, it)

            mAdapterAllHeroes?.list = list
        }
    }

    override fun setPresenter(presenter: AllHeroesContract.Presenter?) {
        mPresenter = presenter
    }

    override fun setResultResponse(result: MutableList<Result>?) {
        mContainerError?.visibility = View.GONE
        mContainerOk?.visibility = View.VISIBLE
        errorFragment?.let { supportFragmentManager.beginTransaction()
                .remove(it)
                .commit() }

        result?.let {
            mAdapterAllHeroes?.list = it
        }
    }

    override fun error(code: Int) {
         mContainerOk?.visibility = View.GONE
         mContainerError?.visibility = View.VISIBLE

        val bundle = Bundle()
        bundle.putInt("error", code)

        errorFragment = ErrorFragment()
        errorFragment!!.arguments = bundle

        supportFragmentManager.beginTransaction()
                .replace(R.id.frame_error, errorFragment!!)
                .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.let { it.unsubscribe() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        menuInflater.inflate(R.menu.mainmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}