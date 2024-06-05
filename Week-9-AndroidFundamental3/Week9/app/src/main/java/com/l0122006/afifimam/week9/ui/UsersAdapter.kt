package com.l0122006.afifimam.week9.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.l0122006.afifimam.week9.R
import com.l0122006.afifimam.week9.response.UserResponse

class UserAdapter(
    private val context: LIstActivity,
    private val data: ArrayList<UserResponse>
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvJudul: TextView = view.findViewById(R.id.tvJudul)
        val tvPopularitas: TextView = view.findViewById(R.id.tvPopularitas)
        val tvBahasa: TextView = view.findViewById(R.id.tvBahasa)
        val tvRilis:TextView = view.findViewById(R.id.tvRilis)
        val imgPoster : ImageView = view.findViewById(R.id.poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_user, parent, false)

        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.tvJudul.text = data.get(position).name
        holder.tvPopularitas.text = data.get(position).popularity.toString()
        holder.tvBahasa.text = data.get(position).originalLanguage
        holder.tvRilis.text = data.get(position).firstAirDate
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${data.get(position).imageUrl}")
            .into(holder.imgPoster)
    }

    override fun getItemCount(): Int = data.size

    fun setData(newData: ArrayList<UserResponse>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}
