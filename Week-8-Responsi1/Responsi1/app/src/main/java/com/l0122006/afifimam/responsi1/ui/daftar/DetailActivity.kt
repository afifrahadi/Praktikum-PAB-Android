package com.l0122006.afifimam.responsi1.ui.daftar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.l0122006.afifimam.responsi1.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imgPhoto: ImageView = findViewById(R.id.img_team)
        val tvName: TextView = findViewById(R.id.tv_nama_team)
        val tvDesc: TextView = findViewById(R.id.tv_team_desc)

        val team = intent.getParcelableExtra<Team>("EXTRA_TEAM") as Team

        imgPhoto.setImageResource(team.img)
        tvName.text = team.name
        tvDesc.text = team.desc

    }

}