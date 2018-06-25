package io.navendra.bingetime.views.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import com.github.ajalt.timberkt.Timber
import io.navendra.bingetime.R
import io.navendra.bingetime.models.HomeFeedDataProvider
import io.navendra.bingetime.views.adapters.HomeFeedAdapter
import io.navendra.bingetime.views.utils.RecyclerTouchListener
import kotlinx.android.synthetic.main.activity_home.*
import android.view.View
import io.navendra.bingetime.models.HomeFeedModel
import io.navendra.bingetime.viewmodels.HomeFeedViewModel


class HomeActivity : AppCompatActivity() {

    companion object {
        val TAG = HomeActivity::class.java.simpleName
    }

    //region LateInit VARIABLES for Adapter & Manager
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var recyclerClickListener: RecyclerTouchListener.ClickListener
    private lateinit var recyclerTouchListener: RecyclerTouchListener
    private lateinit var feedDataSet : MutableLiveData<List<HomeFeedModel>>
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        signOut.setOnClickListener { signOutUser() }
        val feedViewModelProviders = ViewModelProviders.of(this).get(HomeFeedViewModel::class.java)
        feedDataSet = feedViewModelProviders.getFeedDataSet()
        Timber.d { "Initializing Recycler View" }
        initRecycler()
    }

    private fun initRecycler(){
        viewManager = LinearLayoutManager(this)
        viewAdapter = HomeFeedAdapter(this,HomeFeedDataProvider.getFeedDataSet())
        recyclerClickListener = object : RecyclerTouchListener.ClickListener{
            override fun onClick(view: View, position: Int) {
                val feed = feedDataSet.value?.get(position)
                val intent =  Intent(this@HomeActivity, HomeDetailActivity::class.java).apply {
                    val key =  "$TAG.id"
                    putExtra(key, feed?.id)
                }
                startActivity(intent)
            }

            override fun onLongClick(view: View?, position: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        recyclerTouchListener = RecyclerTouchListener(this@HomeActivity,recycler_view,recyclerClickListener)

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            itemAnimator = DefaultItemAnimator()
            addItemDecoration(DividerItemDecoration(this@HomeActivity,LinearLayoutManager.VERTICAL))
            addOnItemTouchListener(recyclerTouchListener)

        }
        Timber.d { "Recycler View successfully initialised with adapters and animators" }
    }

    private fun signOutUser(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    Timber.d { "User Signed Out!! "}
                    startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                    finish()
                }
    }





}
