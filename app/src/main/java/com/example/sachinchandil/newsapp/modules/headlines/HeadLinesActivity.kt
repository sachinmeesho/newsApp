package com.example.sachinchandil.newsapp.modules.headlines

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import com.example.sachinchandil.newsapp.R
import com.example.sachinchandil.newsapp.arch.viewmodel.BaseActivity
import kotlinx.android.synthetic.main.activity_head_lines.*

class HeadLinesActivity : BaseActivity<HeadLinesViewModel>() {

    lateinit var adapter: HeadLinesListAdapter

    override fun init() {
        adapter = HeadLinesListAdapter(this)
        rvNewsList.layoutManager = LinearLayoutManager(this)
        rvNewsList.adapter = adapter
        mViewModel.fetchHeadLines()
    }

    override fun setupEvents() {
        mViewModel.viewState.observe(this, Observer { viewEvent ->
            viewEvent?.doIfNotHandled {
                if (it.data.isNotEmpty()) {
                    adapter.setItems(it.data)
                    rvNewsList.visibility = View.VISIBLE
                } else {
                    rvNewsList.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.headlines_page_menu, menu)
        menu?.apply {
            val actionSearch = findItem(R.id.action_search)
            val searchView = actionSearch.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("not implemented")
                }

                override fun onQueryTextChange(newText: String?): Boolean = false
            })
        }

        return super.onCreateOptionsMenu(menu)

    }

    override fun getLayout(): Int = R.layout.activity_head_lines
    override fun getViewModelType(): Class<HeadLinesViewModel> = HeadLinesViewModel::class.java
}
