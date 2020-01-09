package net.omisoft.aborovskoy.viperdemo.ui.main

import net.omisoft.aborovskoy.viperdemo.ui.detail.DetailActivity

class MainRouter(private val activity: MainActivity) : MainContract.Router {

    override fun openFullPhoto(photoId: String) = DetailActivity.launch(activity, photoId)
}