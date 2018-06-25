package io.navendra.bingetime.views.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import io.navendra.bingetime.R
import io.navendra.bingetime.models.HomeFeedModel
import io.navendra.bingetime.viewmodels.HomeFeedViewModel
import kotlinx.android.synthetic.main.activity_home_detail.*
import kotlin.properties.Delegates

class HomeDetailActivity : AppCompatActivity() {

    var feed : LiveData<HomeFeedModel> by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_detail)
        val key = "${HomeActivity.TAG}.id"
        val id = intent.getIntExtra(key, 0)

        val feedViewModelProviders = ViewModelProviders.of(this).get(HomeFeedViewModel::class.java)

        feedViewModelProviders.setFeedData(id)

        feed = feedViewModelProviders.getFeedData()

        feed.observe(this, Observer {
            updateUI()
        })
    }

    fun updateUI(){

        val imageId = feed.value?.imageId ?: 0
        val drawable = getDrawable(imageId)
        imageView.setImageDrawable(drawable)
        titleTextView.text = feed.value?.title
        content.text = feed.value?.content
        url.text = feed.value?.url

    }
}
