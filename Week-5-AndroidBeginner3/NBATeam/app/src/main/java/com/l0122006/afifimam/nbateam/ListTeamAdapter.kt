package com.l0122006.afifimam.nbateam

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListTeamAdapter(private val context: Context, private val listTeam: ArrayList<Team>) : RecyclerView.Adapter<ListTeamAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_team, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, desc, img) = listTeam[position]
        holder.imgPhoto.setImageResource(img)
        holder.tvName.text = name
        holder.tvDescription.text = desc
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTeam[holder.adapterPosition])
        }
        holder.itemView.setOnClickListener{
            val detailsIntent = Intent(context, DetailData::class.java)
            detailsIntent.putExtra(DetailData.EXTRA_NAME, name)
            detailsIntent.putExtra(DetailData.EXTRA_DESC, desc)
            detailsIntent.putExtra(DetailData.EXTRA_IMG, img)
            context.startActivity(detailsIntent)
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Team)
    }
}