package com.example.cameraapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val data: List<PhotoData>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photoNameTextView: TextView = view.findViewById(R.id.photoText)
        val photoDateTimeTextView: TextView = view.findViewById(R.id.dateText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
        holder.photoNameTextView.text = currentItem.photoName
        holder.photoDateTimeTextView.text = currentItem.photoDateTime
    }

    override fun getItemCount(): Int {
        return data.size
    }

    data class PhotoData(var photoName: String, val photoDateTime: String)

}