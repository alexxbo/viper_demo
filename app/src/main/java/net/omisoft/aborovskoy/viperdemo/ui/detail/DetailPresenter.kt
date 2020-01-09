package net.omisoft.aborovskoy.viperdemo.ui.detail

import timber.log.Timber

class DetailPresenter(private val router: DetailContract.Router,
                      private val interactor: DetailInteractor) : DetailContract.Presenter {

    private var view: DetailContract.View? = null

    override fun bindView(view: DetailContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
        interactor.dispose()
    }

    override fun loadPhoto(photoId: String?) {
        if (photoId == null) {
            onEmptyData()
        } else {
            view?.showLoading()
            interactor.getPhoto(photoId, {
                view?.hideLoading()
                view?.publishData(it)
            }, this::onError)
        }
    }

    override fun onBackClicked() {
        router.finish()
    }

    private fun onError(error: Throwable) {
        Timber.e(error)
        view?.hideLoading()
        error.message?.let { view?.showMessage(it) }
        view?.showEmptyState()
    }

    private fun onEmptyData() {
        view?.hideLoading()
        view?.showEmptyState()
    }
}