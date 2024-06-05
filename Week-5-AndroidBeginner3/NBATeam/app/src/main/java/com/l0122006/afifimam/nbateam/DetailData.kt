package com.l0122006.afifimam.nbateam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class DetailData : AppCompatActivity() {
    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESC = "extra_desc"
        const val EXTRA_IMG = "extra_img"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_data)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgTeam : ImageView = findViewById(R.id.img_team)
        val tvTeamName : TextView = findViewById(R.id.tv_nama_team)
        val tvTeamDesc : TextView = findViewById(R.id.tv_team_desc)

        val img = intent.getIntExtra(EXTRA_IMG, 0)
        val name = intent.getStringExtra(EXTRA_NAME)
        val desc = intent.getStringExtra(EXTRA_DESC)

        imgTeam.setImageResource(img)
        tvTeamName.text = name
        tvTeamDesc.text=desc

        }
}