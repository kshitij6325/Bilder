package com.example.imageloadingtest

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bilder.Bilder
import com.example.bilder.Source

class ImageAdapter(private val list: List<Pair<Int, String>>, private val bilder: Bilder.Config) :
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_image, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Bilder.init(holder.image.context as Activity).configure {
            onBitmapLoadFailure = {
                Log.e("Bilder:: ", it.message.toString())
            }
        }.load(Source.Url(list[position].second), imageView = holder.image)
    }

    override fun getItemCount() = list.size
}
