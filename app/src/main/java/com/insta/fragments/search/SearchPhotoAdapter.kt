package com.insta.fragments.search

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.insta.R
import com.insta.ui.photodetails.PhotoDetailsActivity
import com.insta.model.Photo
import com.insta.utils.Application
import com.insta.utils.ApplicationConstants
import com.insta.utils.PrefsManager

class SearchPhotoAdapter(
    mListPhotos: ArrayList<Photo>,
    mView: Context,
    mViewModel: SearchViewModel
) :
    RecyclerView.Adapter<SearchPhotoAdapter.SearchPhotoView>() {
    private var listPhotos: ArrayList<Photo>? = null
    private var prefsManager: PrefsManager? = null
    private var view: Context? = null
    private var viewModel: SearchViewModel? = null

    init {
        view = mView
        viewModel = mViewModel
        listPhotos = mListPhotos
        prefsManager = PrefsManager(mView)
    }

    class SearchPhotoView(v: View) : RecyclerView.ViewHolder(v) {
        var constraintItemSearch: ConstraintLayout? = null
        var imvAuthorSearch: ImageView? = null
        var tvUserNameAuthor: TextView? = null
        var tvColourCodePhoto: TextView? = null
        var tvDateCreation: TextView? = null
        var viewRoundColor: View? = null

        init {
            constraintItemSearch = v.findViewById(R.id.constraint_item_search)
            imvAuthorSearch = v.findViewById(R.id.imv_item_photo_author)
            tvUserNameAuthor = v.findViewById(R.id.tv_username_author)
            tvColourCodePhoto = v.findViewById(R.id.tv_photo_code_colour)
            tvDateCreation = v.findViewById(R.id.tv_created_date)
            viewRoundColor = v.findViewById(R.id.view_round_color)
        }
    }

    @SuppressLint("SetTextI18n", "Range")
    override fun onBindViewHolder(holder: SearchPhotoView, position: Int) {
        holder.setIsRecyclable(false)
        val photo = listPhotos!![position]

        if (photo.urls!!.regular.isNotEmpty()) {
            Glide.with(view!!)
                .load(photo.urls!!.regular)
                .transform(CenterCrop(), RoundedCorners(8))
                .into(holder.imvAuthorSearch!!)
        }

        holder.tvUserNameAuthor?.text = photo.user!!.username
        val creationDate = Application.convertToFormatSpecific(
            ApplicationConstants.yyyyMMdd,
            ApplicationConstants.ddMMyyyy,
            photo.created_at!!.split("T")[0]
        )
        holder.tvDateCreation?.text = creationDate
        holder.tvColourCodePhoto?.text = photo.color
        holder.viewRoundColor?.background?.setTint(Color.parseColor(photo.color))


        holder.constraintItemSearch?.setOnClickListener {
            val intent = Intent(view, PhotoDetailsActivity::class.java)
            intent.putExtra(ApplicationConstants.PHOTO, photo)
            view?.startActivity(intent)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPhotoView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result_search, parent, false)
        return SearchPhotoView(itemView)
    }

    override fun getItemCount(): Int {
        return listPhotos!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
