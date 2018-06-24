package io.navendra.bingetime.Services

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.github.ajalt.timberkt.Timber
import io.navendra.bingetime.Util.Constants
import io.navendra.bingetime.Util.SingletonHolder


class NetworkService private constructor(context: Context){
    companion object : SingletonHolder<NetworkService, Context>(::NetworkService)

    var connectivityManager : ConnectivityManager? = null
    var context : Context? = null

    init {
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        this.context = context
        if(connectivityManager == null){
            Timber.e { "ConnectivityManager is null, Network Service won't work!!" }
        }

    }

    fun isConnected() : Boolean{
        val activeNetwork: NetworkInfo? = connectivityManager?.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun showConnectionStateToast(){
        if(!isConnected()){
            Toast.makeText(context, Constants.NETWORK_NOT_CONNECTED,Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context,Constants.NETWORK_CONNECTED,Toast.LENGTH_LONG).show()
        }

    }
}