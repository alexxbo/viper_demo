package net.omisoft.aborovskoy.viperdemo.ui.splash

import net.omisoft.aborovskoy.viperdemo.ui.main.MainActivity

class SplashRouter(private val activity: SplashActivity) : SplashContract.Router {

    override fun openMain() {
        MainActivity.launch(activity)
        activity.finish()
    }
}