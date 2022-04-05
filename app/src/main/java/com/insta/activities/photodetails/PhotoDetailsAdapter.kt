package com.insta.activities.photodetails

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.insta.R
import com.insta.model.Photo
import com.insta.utils.PrefsManager


class PhotoDetailsAdapter(
    mListPhotos: ArrayList<Photo>,
    mView: Context,
    mPresenter: PhotoDetailsPresenter
) :
    RecyclerView.Adapter<PhotoDetailsAdapter.ActualityView>() {
    private var listPhotos: ArrayList<Photo>? = null
    private var prefsManager: PrefsManager? = null
    private var view: Context? = null
    private var presenter: PhotoDetailsPresenter? = null

    init {
        view = mView
        presenter = mPresenter
        listPhotos = mListPhotos
        prefsManager = PrefsManager(mView)
    }

    class ActualityView(v: View) : RecyclerView.ViewHolder(v) {
        var imvPhotoContent: ImageView? = null
        var linearProfilePhotoAuthor: LinearLayout? = null
        var imvPhotoProfileAuthor: ImageView? = null
        var tvUserNameAuthor: TextView? = null
        var tvDescPhoto: TextView? = null
        var tvDatePhoto: TextView? = null
        var tvCountLikesPhoto: TextView? = null
        var tvDownloadsCountPhoto: TextView? = null
        var tvColourCodePhoto: TextView? = null
        var viewRoundColor: View? = null

        init {
            imvPhotoContent = v.findViewById(R.id.imv_photo_content)
            linearProfilePhotoAuthor = v.findViewById(R.id.linear_profile_photo_author)
            imvPhotoProfileAuthor = v.findViewById(R.id.imv_photo_profile_author)
            tvUserNameAuthor = v.findViewById(R.id.tv_username_author)
            tvDatePhoto = v.findViewById(R.id.tv_photo_date)
            tvCountLikesPhoto = v.findViewById(R.id.tv_count_photo_likes)
            tvDescPhoto = v.findViewById(R.id.tv_desc_photo)
            tvDownloadsCountPhoto = v.findViewById(R.id.tv_photo_downloads)
            tvColourCodePhoto = v.findViewById(R.id.tv_photo_code_colour)
            viewRoundColor = v.findViewById(R.id.view_round_color)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ActualityView, position: Int) {
        holder.setIsRecyclable(false)
        val photo = listPhotos!![position]

        if (photo.urls.full.isNotEmpty()) {
            Glide.with(view!!)
                .load(photo.urls.full)
                .into(holder.imvPhotoContent!!)
        }

        if (photo.user.profile_image.small.isNotEmpty()) {
            Glide.with(view!!)
                .load(photo.user.profile_image.small)
                .into(holder.imvPhotoProfileAuthor!!)
        }

        holder.tvUserNameAuthor?.text = photo.user.username
        holder.tvDescPhoto?.text = photo.description
        holder.tvDescPhoto?.text = photo.description

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActualityView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo_details, parent, false)
        return ActualityView(itemView)
    }

    override fun getItemCount(): Int {
        return listPhotos!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
