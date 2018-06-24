package io.navendra.bingetime.views.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.firebase.ui.auth.AuthUI
import io.navendra.bingetime.R
import io.navendra.bingetime.models.HomeFeedDataProvider
import io.navendra.bingetime.views.adapters.HomeFeedAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        signOut.setOnClickListener { signOutUser() }
        initRecycler()
    }

    fun initRecycler(){
        viewManager = LinearLayoutManager(this)
        viewAdapter = HomeFeedAdapter(this,HomeFeedDataProvider.getFeedDataSet())

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun signOutUser(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    // user is now signed out
                    startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                    finish()
                }
    }

}
