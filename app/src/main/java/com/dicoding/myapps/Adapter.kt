package com.dicoding.myapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dataList: ArrayList<DataClass>) : RecyclerView.Adapter<Adapter.ViewHolderClass>() {

    var onItemClick:((DataClass)-> Unit)? = null

    // Inflate the item layout and create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    // Bind data to the ViewHolder
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentaitem = dataList[position]
        holder.rvImage.setImageResource(currentaitem.dataImage)
        holder.rvTitle.text = currentaitem.dataTitle

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentaitem)
        }
    }

    // Return the total count of items
    override fun getItemCount(): Int {
        return dataList.size
    }

    // ViewHolder class holds references to the item views
    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.image)
        val rvTitle: TextView = itemView.findViewById(R.id.title)
    }
}
