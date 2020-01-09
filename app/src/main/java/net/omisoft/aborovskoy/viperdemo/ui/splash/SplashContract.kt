package net.omisoft.aborovskoy.viperdemo.ui.splash

interface SplashContract {
    interface View

    interface Presenter {
        fun bindView(view: SplashContract.View)
        fun unbindView()
        fun doOnStart()
        fun doOnStop()
    }

    interface Router {
        fun openMain()
    }
}