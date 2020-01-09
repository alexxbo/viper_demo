package net.omisoft.aborovskoy.viperdemo.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.omisoft.aborovskoy.viperdemo.R
import net.omisoft.aborovskoy.viperdemo.app.App
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel
import net.omisoft.aborovskoy.viperdemo.ui.main.adapter.MainAdapter
import net.omisoft.aborovskoy.viperdemo.ui.main.di.DaggerMainComponent
import net.omisoft.aborovskoy.viperdemo.ui.main.di.MainComponent
import net.omisoft.aborovskoy.viperdemo.ui.main.di.MainModule
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var presenter: MainPresenter

    private val component: MainComponent by lazy {
        DaggerMainComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(MainModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)
        presenter.bindView(this)
        presenter.loadContent()
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.unbindView()
    }

    override fun showLoading() {
        emptyState.visibility = View.GONE
        photoRecycler.visibility = View.GONE
        swipeRefreshContainer.isRefreshing = true
    }

    override fun hideLoading() {
        photoRecycler.visibility = View.VISIBLE
        swipeRefreshContainer.isRefreshing = false
    }

    override fun publishData(data: List<PhotoModel>) {
        val adapter = MainAdapter(data, object : MainAdapter.PhotoListener {
            override fun onItemClick(photoId: String) {
                presenter.onItemClicked(photoId)
            }
        })
        photoRecycler.adapter = adapter
    }

    override fun showEmptyState() {
        photoRecycler.visibility = View.GONE
        emptyState.visibility = View.VISIBLE
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        val manager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        photoRecycler.layoutManager = manager
        swipeRefreshContainer.setOnRefreshListener { presenter.loadContent() }
    }
}
