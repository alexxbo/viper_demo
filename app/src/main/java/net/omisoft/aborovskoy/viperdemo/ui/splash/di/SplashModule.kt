package net.omisoft.aborovskoy.viperdemo.ui.splash.di

import dagger.Module
import dagger.Provides
import net.omisoft.aborovskoy.viperdemo.app.di.scope.ActivityScope
import net.omisoft.aborovskoy.viperdemo.ui.splash.SplashActivity
import net.omisoft.aborovskoy.viperdemo.ui.splash.SplashContract
import net.omisoft.aborovskoy.viperdemo.ui.splash.SplashPresenter
import net.omisoft.aborovskoy.viperdemo.ui.splash.SplashRouter

@Module
class SplashModule {

    @Provides
    @ActivityScope
    fun router(activity: SplashActivity): SplashContract.Router = SplashRouter(activity)

    @Provides
    @ActivityScope
    fun presenter(router: SplashContract.Router) = SplashPresenter(router)
}