package com.l0122006.afifimam.responsi1.ui.daftar

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.l0122006.afifimam.responsi1.R

class ListTeamAdapter(private val listTeam: ArrayList<Team>) : RecyclerView.Adapter<ListTeamAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClicked(data: Team)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickListener) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDesc: TextView = itemView.findViewById(R.id.tv_item_description)

        init {
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listTeam[adapterPosition])
            }
        }
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
        holder.tvDesc.text = desc

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("EXTRA_TEAM", listTeam[holder.adapterPosition])
            holder.itemView.context.startActivity(intent)
        }

    }
}
