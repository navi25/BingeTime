package io.navendra.bingetime.views.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.github.ajalt.timberkt.Timber
import com.google.firebase.auth.FirebaseAuth
import io.navendra.bingetime.R
import io.navendra.bingetime.Services.NetworkService
import io.navendra.bingetime.Services.TimberLogging
import io.navendra.bingetime.models.WelcomeDataProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*

class LoginActivity : AppCompatActivity() {

    //region VARIABLES - networkService, SignInInt, loginBoolean
    private val networkService : NetworkService by lazy { NetworkService.getInstance(this) }
    private val RC_SIGN_IN = 123
    private var isUserLoggedIn : Boolean? = false
    private var USER_LOGIN_CHECK_KEY = "isUserLoggedIn"
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TimberLogging()
        setContentView(R.layout.activity_login)
        isUserLoggedIn = savedInstanceState?.getBoolean(USER_LOGIN_CHECK_KEY)
        welcomeText.text = WelcomeDataProvider.getWelcomeData()
        welcomeText.visibility = View.VISIBLE

        Timber.d { "Login Activity Started - Beginning ShowLogin Function" }
        if(networkService.isConnected()){
            showLogin()
        }else {
            Timber.d { "User Not Connected to Internet... Exiting ShowLogin function" }
            showOfflineLoginUI()
            networkService.showConnectionStateToast()
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        isUserLoggedIn = savedInstanceState?.getBoolean(USER_LOGIN_CHECK_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.run {
            putBoolean(USER_LOGIN_CHECK_KEY, isUserLoggedIn?:false)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestart() {
        super.onRestart()
        goToHome()
    }

    override fun onResume() {
        super.onResume()
        goToHome()
    }

    private fun goToHome(){
        if(isUserLoggedIn==true){ //this if check is required because of nullable boolean type
            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
            finish()
        }
    }

    private fun showOfflineLoginUI(){
        welcomeText.visibility = View.VISIBLE
    }

    private fun showLogin(){
        welcomeText.visibility = View.GONE
        var providers = Arrays.asList(
                AuthUI.IdpConfig.EmailBuilder().build(),
                AuthUI.IdpConfig.FacebookBuilder().build(),
                AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser

                Timber.d { "User Successfully signed In!!" }
                Timber.i { "User : " + user?.toString() }
                Timber.d { "Starting HomeActivity!"}
                this.isUserLoggedIn = true
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()

            } else {
                Timber.d { "SignIn Failed!!"}
                Timber.d(response?.error )
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

}

