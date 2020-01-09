package net.omisoft.aborovskoy.viperdemo.ui.detail.di

import dagger.BindsInstance
import dagger.Component
import net.omisoft.aborovskoy.viperdemo.app.di.AppComponent
import net.omisoft.aborovskoy.viperdemo.app.di.scope.ActivityScope
import net.omisoft.aborovskoy.viperdemo.ui.detail.DetailActivity

@ActivityScope
@Component(modules = [DetailModule::class], dependencies = [AppComponent::class])
interface DetailComponent {

    fun inject(target: DetailActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: DetailActivity): Builder
        fun appComponent(component: AppComponent): Builder
        fun plus(module: DetailModule): Builder
        fun build(): DetailComponent
    }
}