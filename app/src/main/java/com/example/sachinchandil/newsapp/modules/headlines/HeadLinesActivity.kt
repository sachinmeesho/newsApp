package com.example.sachinchandil.newsapp.modules.headlines

import android.os.Bundle
import com.example.sachinchandil.newsapp.R
import com.example.sachinchandil.newsapp.arch.viewmodel.BaseActivity

class HeadLinesActivity : BaseActivity<HeadLinesViewModel>() {

    override fun getViewModelType(): Class<HeadLinesViewModel> = HeadLinesViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_head_lines)
    }
}
