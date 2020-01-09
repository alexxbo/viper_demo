package net.omisoft.aborovskoy.viperdemo.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_photo.view.*
import net.omisoft.aborovskoy.viperdemo.R
import net.omisoft.aborovskoy.viperdemo.app.model.PhotoModel

class MainAdapter(private val photos: List<PhotoModel>,
                  private val listener: PhotoListener) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos[position])
    }

    override fun getItemCount() = photos.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val photo = itemView.photo!!

        fun bind(photoModel: PhotoModel) {
            Glide.with(itemView)
                .load(photoModel.downloadUrl)
                .centerCrop()
                .into(photo)

            itemView.setOnClickListener { listener.onItemClick(photoModel.id) }
        }
    }

    interface PhotoListener {
        fun onItemClick(photoId: String)
    }
}