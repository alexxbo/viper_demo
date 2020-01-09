package net.omisoft.aborovskoy.viperdemo.ui.detail

class DetailRouter(private val activity: DetailActivity) : DetailContract.Router {

    override fun finish() {
        activity.finish()
    }
}