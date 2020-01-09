package net.omisoft.aborovskoy.viperdemo.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.omisoft.aborovskoy.viperdemo.R
import net.omisoft.aborovskoy.viperdemo.app.App
import net.omisoft.aborovskoy.viperdemo.ui.splash.di.DaggerSplashComponent
import net.omisoft.aborovskoy.viperdemo.ui.splash.di.SplashComponent
import net.omisoft.aborovskoy.viperdemo.ui.splash.di.SplashModule
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject lateinit var presenter: SplashPresenter

    private val component: SplashComponent by lazy {
        DaggerSplashComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(SplashModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        component.inject(this)
        presenter.bindView(this)
    }

    override fun onStart() {
        super.onStart()

        presenter.doOnStart()
    }

    override fun onStop() {
        super.onStop()

        presenter.doOnStop()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.unbindView()
    }
}
