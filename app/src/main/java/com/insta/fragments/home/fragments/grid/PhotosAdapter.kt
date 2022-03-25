package com.insta.fragments.home.fragments.grid

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.insta.R
import com.insta.model.Photo
import com.insta.utils.PrefsManager

class PhotosAdapter(
    listPhotos: ArrayList<Photo>,
    view: Context,
    presenter: GridPresenter
) : RecyclerView.Adapter<PhotosAdapter.DisplayPhotoAlbumView>() {

    private var listPhotos: ArrayList<Photo>? = listPhotos
    private var prefsManager: PrefsManager? = null
    private var view: Context? = null
    private var presenter: GridPresenter? = null

    init {
        prefsManager = PrefsManager(view.applicationContext)
        this.presenter = presenter
        this.view = view
    }

    class DisplayPhotoAlbumView(v: View) : RecyclerView.ViewHolder(v) {
        var constraintItemPhoto: LinearLayout? = null
        var imvPhoto: ImageView? = null


        init {
            constraintItemPhoto = v.findViewById(R.id.constraint_display_photo_item)
            imvPhoto = v.findViewById(R.id.imv_item_display_photo)
        }
    }

    override fun onBindViewHolder(holder: DisplayPhotoAlbumView, position: Int) {
        holder.setIsRecyclable(false)
        val photo = listPhotos!![position]

        if (photo.urls.raw.isNotEmpty()) {
            Glide.with(view!!.applicationContext)
                .load(photo.urls.small)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .into(holder.imvPhoto!!)
        }

        holder.constraintItemPhoto?.setOnClickListener {
           // presenter?.toDetails(photo)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayPhotoAlbumView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        return DisplayPhotoAlbumView(itemView)
    }

    override fun getItemCount(): Int {
        return listPhotos!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}
