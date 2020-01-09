package net.omisoft.aborovskoy.viperdemo.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import net.omisoft.aborovskoy.viperdemo.BuildConfig
import net.omisoft.aborovskoy.viperdemo.R
import net.omisoft.aborovskoy.viperdemo.app.App
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel
import net.omisoft.aborovskoy.viperdemo.ui.detail.di.DaggerDetailComponent
import net.omisoft.aborovskoy.viperdemo.ui.detail.di.DetailComponent
import net.omisoft.aborovskoy.viperdemo.ui.detail.di.DetailModule
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailContract.View {

    companion object {
        private const val PHOTO_ID = "${BuildConfig.APPLICATION_ID}_PHOTO_ID"

        fun launch(activity: Activity, photoId: String) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(PHOTO_ID, photoId)
            activity.startActivity(intent)
        }
    }

    @Inject
    lateinit var presenter: DetailPresenter

    private val component: DetailComponent by lazy {
        DaggerDetailComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(DetailModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        component.inject(this)
        presenter.bindView(this)
        initView()
        intent?.getStringExtra(PHOTO_ID).let { presenter.loadPhoto(it) }
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.unbindView()
    }

    override fun onBackPressed() {
        presenter.onBackClicked()
    }

    override fun publishData(photoModel: PhotoModel) {
        Glide.with(this)
            .load(photoModel.downloadUrl)
            .centerCrop()
            .into(photo)

        name.text = photoModel.author
        size.text = getString(R.string.size_placeholder, photoModel.height, photoModel.width)
    }

    override fun showLoading() {
        emptyState.visibility = View.GONE
        photo_container.visibility = View.GONE
        swipeRefreshContainer.isRefreshing = true
    }

    override fun hideLoading() {
        photo_container.visibility = View.VISIBLE
        swipeRefreshContainer.isRefreshing = false
    }

    override fun showEmptyState() {
        photo_container.visibility = View.GONE
        emptyState.visibility = View.VISIBLE
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        swipeRefreshContainer.isEnabled = false
    }
}
