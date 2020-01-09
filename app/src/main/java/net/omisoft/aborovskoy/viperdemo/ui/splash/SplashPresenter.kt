package net.omisoft.aborovskoy.viperdemo.ui.splash

import android.os.Handler

class SplashPresenter(private val router: SplashContract.Router) : SplashContract.Presenter {

    companion object {
        private const val SPLASH_DISPLAY_TIME = 1000L
    }

    private val handler by lazy { Handler() }

    private var view: SplashContract.View? = null

    override fun bindView(view: SplashContract.View) {
        this.view = view
    }

    override fun doOnStart() {
        handler.postDelayed({
            router.openMain()
        }, SPLASH_DISPLAY_TIME)
    }

    override fun doOnStop() {
        handler.removeCallbacksAndMessages(null)
    }

    override fun unbindView() {
        view = null
    }
}