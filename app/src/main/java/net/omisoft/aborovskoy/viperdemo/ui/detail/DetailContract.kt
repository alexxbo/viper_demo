package net.omisoft.aborovskoy.viperdemo.ui.detail

import io.reactivex.Single
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel

interface DetailContract {
    interface View {
        fun publishData(photoModel: PhotoModel)
        fun showMessage(msg: String)
        fun showEmptyState()
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun bindView(view: DetailContract.View)
        fun unbindView()
        fun loadPhoto(photoId: String?)
        fun onBackClicked()
    }

    interface Interactor {
        fun getPhoto(photoId: String, onSuccess: (PhotoModel) -> Unit, onError: (Throwable) -> Unit)
    }

    interface Router {
        fun finish()
    }

    interface Repo {
        fun getPhoto(photoId: String): Single<PhotoModel>
    }
}