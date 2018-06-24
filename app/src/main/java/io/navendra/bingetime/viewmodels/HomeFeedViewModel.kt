package io.navendra.bingetime.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import io.navendra.bingetime.models.HomeFeedDataProvider
import io.navendra.bingetime.models.HomeFeedModel

class HomeFeedViewModel : ViewModel(){
    private var feedDataSet: MutableLiveData<List<HomeFeedModel>> = MutableLiveData()

    private var feedData: LiveData<HomeFeedModel>
    private var feedDataId: MutableLiveData<Int> = MutableLiveData()

    init {
        feedDataSet.value = HomeFeedDataProvider.getFeedDataSet()
        feedData = Transformations.map(feedDataId){
            HomeFeedDataProvider.getFeedDataSet(it)
        }
    }

    fun setFeedData(id:Int){
        feedDataId.value = id
    }

    fun getFeedDataSet() = feedDataSet
}