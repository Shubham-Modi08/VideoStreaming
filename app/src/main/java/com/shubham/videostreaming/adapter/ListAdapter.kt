package com.shubham.videostreaming.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.shubham.videostreaming.R
import com.shubham.videostreaming.model.ResponseItem


class ListAdapter(private val mList: List<ResponseItem>, private val lifecycle: Lifecycle) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the item_video view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val items = mList[position]
        holder.title.text = items.TITLE
        holder.desc.text = items.DESCRIPTION
        val videoLinks = items.LINK
        val videoId: String = videoLinks.substringAfterLast("=")
        var player: YouTubePlayer
        lifecycle.addObserver(holder.video)
        holder.video.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                player = youTubePlayer
                youTubePlayer.cueVideo(videoId, 0f)
            }
        })

    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to video and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val desc: TextView = itemView.findViewById(R.id.description)
        var video: YouTubePlayerView = itemView.findViewById(R.id.videoView)


    }


}


