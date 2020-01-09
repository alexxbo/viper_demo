package net.omisoft.aborovskoy.viperdemo.ui.main

import timber.log.Timber

class MainPresenter(private val router: MainContract.Router,
                    private val interactor: MainInteractor) : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
        interactor.dispose()
    }

    override fun loadContent() {
        view?.showLoading()
        interactor.getPhotos({
            view?.hideLoading()
            view?.publishData(it)
        }, this::onError)
    }

    override fun onItemClicked(photoId: String) {
        router.openFullPhoto(photoId)
    }

    private fun onError(error: Throwable) {
        Timber.e(error)
        view?.hideLoading()
        error.message?.let { view?.showMessage(it) }
        view?.showEmptyState()
    }
}