package net.omisoft.aborovskoy.viperdemo.ui.detail.di

import dagger.Module
import dagger.Provides
import net.omisoft.aborovskoy.viperdemo.app.di.scope.ActivityScope
import net.omisoft.aborovskoy.viperdemo.ui.detail.*
import net.omisoft.aborovskoy.viperdemo.ui.detail.api.DetailApi
import net.omisoft.aborovskoy.viperdemo.ui.detail.data.DetailRepo
import retrofit2.Retrofit

@Module
class DetailModule {

    @Provides
    @ActivityScope
    fun api(retrofit: Retrofit) = retrofit.create(DetailApi::class.java)

    @Provides
    @ActivityScope
    fun repository(api: DetailApi) = DetailRepo(api)

    @Provides
    @ActivityScope
    fun router(activity: DetailActivity): DetailContract.Router = DetailRouter(activity)

    @Provides
    @ActivityScope
    fun presenter(router: DetailContract.Router, interactor: DetailInteractor) = DetailPresenter(router, interactor)

    @Provides
    @ActivityScope
    fun interactor(repository: DetailRepo) = DetailInteractor(repository)
}