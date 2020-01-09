package net.omisoft.aborovskoy.viperdemo.ui.main.di

import dagger.BindsInstance
import dagger.Component
import net.omisoft.aborovskoy.viperdemo.app.di.scope.ActivityScope
import net.omisoft.aborovskoy.viperdemo.app.di.AppComponent
import net.omisoft.aborovskoy.viperdemo.ui.main.MainActivity

@ActivityScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent {

    fun inject(target: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: MainActivity): Builder
        fun appComponent(component: AppComponent): Builder
        fun plus(module: MainModule): Builder
        fun build(): MainComponent
    }
}