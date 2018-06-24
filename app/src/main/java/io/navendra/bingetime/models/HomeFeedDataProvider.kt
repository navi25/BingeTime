package io.navendra.bingetime.models

import android.arch.lifecycle.MutableLiveData
import io.navendra.bingetime.R

data class HomeFeedModel(
        var id:Int,
        var title : String,
        var content : String,
        var url : String,
        var imageId : Int
)

object HomeFeedDataProvider{

    private val feedDataSet = listOf(
            HomeFeedModel(0,"Title1","Content1", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(1,"Title2","Content2", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(2,"Title3","Content3", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(3,"Title4","Content4", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(4,"Title5","Content5", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(5,"Title6","Content6", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(0,"Title1","Content1", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(1,"Title2","Content2", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(2,"Title3","Content3", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(3,"Title4","Content4", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(4,"Title5","Content5", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(5,"Title6","Content6", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(0,"Title1","Content1", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(1,"Title2","Content2", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(2,"Title3","Content3", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(3,"Title4","Content4", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(4,"Title5","Content5", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(5,"Title6","Content6", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(0,"Title1","Content1", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(1,"Title2","Content2", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(2,"Title3","Content3", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(3,"Title4","Content4", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(4,"Title5","Content5", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(5,"Title6","Content6", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(0,"Title1","Content1", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(1,"Title2","Content2", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(2,"Title3","Content3", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(3,"Title4","Content4", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(4,"Title5","Content5", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(5,"Title6","Content6", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(0,"Title1","Content1", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(1,"Title2","Content2", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(2,"Title3","Content3", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(3,"Title4","Content4", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(4,"Title5","Content5", "url-lin", R.drawable.com_facebook_button_icon),
            HomeFeedModel(5,"Title6","Content6", "url-lin", R.drawable.com_facebook_button_icon)

    )

    fun getFeedDataSet() = feedDataSet

    fun getFeedDataSetLive() = MutableLiveData<List<HomeFeedModel>>().apply {
        value = feedDataSet
    }

    fun getFeedDataSet(id:Int) = feedDataSet.first{it.id == id}

    fun getPokemonsLive(id:Int) = MutableLiveData<HomeFeedModel>().apply {
        value = feedDataSet.first{ it.id == id}
    }


}