package net.omisoft.aborovskoy.viperdemo.ui.main

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel
import net.omisoft.aborovskoy.viperdemo.ui.main.data.MainRepo

class MainInteractor(private val repo: MainRepo) : MainContract.Interactor {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun getPhotos(onSuccess: (List<PhotoModel>) -> Unit, onError: (Throwable) -> Unit) {
        compositeDisposable.add(repo.getPhotos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError))
    }

    fun dispose() = compositeDisposable.dispose()
}