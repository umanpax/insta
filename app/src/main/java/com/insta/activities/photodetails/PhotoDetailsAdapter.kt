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
import kotlin.collections.ArrayList


class PhotoDetailsAdapter(
    private val listPhotosStatistics: Pair<ArrayList<Photo>,ArrayList<Statistics>>,
    private val context: Context,
    private val viewModel: PhotoDetailsViewModel
) :
    RecyclerView.Adapter<PhotoDetailsAdapter.ActualityView>() {

    init {
    }

    class ActualityView(v: View) : RecyclerView.ViewHolder(v) {
        val imvPhotoContent: ImageView = v.findViewById(R.id.imv_photo_content)
        var imvPhotoProfileAuthor: ImageView = v.findViewById(R.id.imv_photo_profile_author)
        var tvUserNameAuthor: TextView =  v.findViewById(R.id.tv_username_author)
        var tvDescPhoto: TextView = v.findViewById(R.id.tv_photo_date)
        var tvDatePhoto: TextView = v.findViewById(R.id.tv_photo_date)
        var tvCountLikesPhoto: TextView = v.findViewById(R.id.tv_count_photo_likes)
        var tvDownloadsCountPhoto: TextView = v.findViewById(R.id.tv_photo_downloads)
        var tvColourCodePhoto: TextView = v.findViewById(R.id.tv_photo_code_colour)
        var viewRoundColor: View = v.findViewById(R.id.view_round_color)
    }

    @SuppressLint("SetTextI18n", "Range")
    override fun onBindViewHolder(holder: ActualityView, position: Int) {
        holder.setIsRecyclable(false)
        val photo = listPhotosStatistics.first[position]
        val statistic = listPhotosStatistics.second[position]
        photo.urls?.let { urls ->
            Glide.with(context)
                .load(urls.regular)
                .into(holder.imvPhotoContent)
        }

        if (photo.user?.profile_image!!.small.isNotEmpty()) {
            Glide.with(context)
                .load(photo.user!!.profile_image!!.medium)
                .transform( CenterCrop(), RoundedCorners(60))
                .into(holder.imvPhotoProfileAuthor)
        }

        holder.tvUserNameAuthor.text = photo.user?.username
        holder.tvDescPhoto.text = photo.description
        val creationDate = Application.convertToFormatSpecific(ApplicationConstants.yyyyMMdd, ApplicationConstants.ddMMyyyy, photo.created_at!!.split("T")[0])
        holder.tvDatePhoto.text = creationDate
        holder.tvCountLikesPhoto.text = statistic.likes.total.toString()
        holder.tvCountLikesPhoto.text = context.getString(R.string.count_likes,statistic.likes.total.toString())
        holder.tvDownloadsCountPhoto.text = context.getString(R.string.count_downloads,statistic.downloads.total.toString())
        holder.tvColourCodePhoto.text = photo.color
        holder.viewRoundColor.background?.setTint(Color.parseColor(photo.color))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActualityView {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo_details, parent, false)
        return ActualityView(itemView)
    }

    override fun getItemCount(): Int {
        return listPhotosStatistics.first.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
