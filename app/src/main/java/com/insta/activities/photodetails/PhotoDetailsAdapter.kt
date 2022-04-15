package com.insta.activities.photodetails

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.insta.R
import com.insta.model.Photo
import com.insta.model.Statistics
import com.insta.utils.Application
import com.insta.utils.ApplicationConstants
import com.insta.utils.PrefsManager
import kotlin.collections.ArrayList


class PhotoDetailsAdapter(
    mListPhotosStatistics: Pair<ArrayList<Photo>,ArrayList<Statistics>>,
    mView: Context,
    mViewModel: PhotoDetailsViewModel
) :
    RecyclerView.Adapter<PhotoDetailsAdapter.ActualityView>() {
    private var listPhotosStatistics: Pair<ArrayList<Photo>,ArrayList<Statistics>>? = null
    private var prefsManager: PrefsManager? = null
    private var view: Context? = null
    private var viewModel: PhotoDetailsViewModel? = null

    init {
        view = mView
        viewModel = mViewModel
        listPhotosStatistics = mListPhotosStatistics
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

    @SuppressLint("SetTextI18n", "Range")
    override fun onBindViewHolder(holder: ActualityView, position: Int) {
        holder.setIsRecyclable(false)
        val photo = listPhotosStatistics!!.first[position]
        val statistic = listPhotosStatistics!!.second[position]

        if (photo.urls!!.full.isNotEmpty()) {
            Glide.with(view!!)
                .load(photo.urls!!.regular)
                .into(holder.imvPhotoContent!!)
        }

        if (photo.user?.profile_image!!.small.isNotEmpty()) {
            Glide.with(view!!)
                .load(photo.user!!.profile_image!!.medium)
                .transform( CenterCrop(), RoundedCorners(60))
                .into(holder.imvPhotoProfileAuthor!!)
        }

        holder.tvUserNameAuthor?.text = photo.user!!.username
        holder.tvDescPhoto?.text = photo.description
        val creationDate = Application.convertToFormatSpecific(ApplicationConstants.yyyyMMdd, ApplicationConstants.ddMMyyyy, photo.created_at!!.split("T")[0])
        holder.tvDatePhoto?.text = creationDate
        holder.tvCountLikesPhoto?.text = statistic.likes.total.toString()
        holder.tvCountLikesPhoto?.text = view?.getString(R.string.count_likes,statistic.likes.total.toString())
        holder.tvDownloadsCountPhoto?.text = view?.getString(R.string.count_downloads,statistic.downloads.total.toString())
        holder.tvColourCodePhoto?.text = photo.color
        holder.viewRoundColor?.background?.setTint(Color.parseColor(photo.color))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActualityView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo_details, parent, false)
        return ActualityView(itemView)
    }

    override fun getItemCount(): Int {
        return listPhotosStatistics!!.first.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
