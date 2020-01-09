package net.omisoft.aborovskoy.viperdemo.ui.detail

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel
import net.omisoft.aborovskoy.viperdemo.ui.detail.data.DetailRepo

class DetailInteractor(private val repo: DetailRepo) : DetailContract.Interactor {

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun getPhoto(photoId: String, onSuccess: (PhotoModel) -> Unit, onError: (Throwable) -> Unit) {
        compositeDisposable.add(repo.getPhoto(photoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onSuccess, onError))
    }

    fun dispose() = compositeDisposable.dispose()
}