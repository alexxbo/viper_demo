package net.omisoft.aborovskoy.viperdemo.app

import android.app.Application
import net.omisoft.aborovskoy.viperdemo.BuildConfig
import net.omisoft.aborovskoy.viperdemo.app.di.AppComponent
import net.omisoft.aborovskoy.viperdemo.app.di.AppModule
import net.omisoft.aborovskoy.viperdemo.app.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .context(this)
            .plus(AppModule())
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        component.inject(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}