package io.navendra.bingetime.models

import java.util.*

data class WelcomeData(
        val text : String
)

object WelcomeDataProvider{

    private val welcomeDataSet = listOf(
            WelcomeData("I need to stop, I whispered to myself \nwhen I clicked next episode!"),
            WelcomeData("In a relationship with netflix"),
            WelcomeData("I should probably try to sleep,\nOh wait there's another episode"),
            WelcomeData("Yes, I'm still watching,\nStop Judging me Netflix"),
            WelcomeData("Sorry, I can't make it to the party tonight.\nI'm Netflixing!")
    )

    fun getWelcomeData() : String {
        val rand = Random()
        val n = rand.nextInt(50) % welcomeDataSet.size
        return welcomeDataSet.get(n).text
    }

}