package net.omisoft.aborovskoy.viperdemo.ui.main

import io.reactivex.Single
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel

interface MainContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun publishData(data: List<PhotoModel>)
        fun showMessage(msg: String)
        fun showEmptyState()
    }

    interface Presenter {
        fun bindView(view: MainContract.View)
        fun unbindView()
        fun loadContent()
        fun onItemClicked(photoId: String)
    }

    interface Interactor {
        fun getPhotos(onSuccess: (List<PhotoModel>) -> Unit, onError: (Throwable) -> Unit)
    }

    interface Router {
        fun openFullPhoto(photoId: String)
    }

    interface Repo {
        fun getPhotos(): Single<List<PhotoModel>>
    }
}