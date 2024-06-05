package com.l0122006.afifimam.praktikum3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnProfile : Button = findViewById(R.id.btn_profile)
        btnProfile.setOnClickListener(this)
        val btnGithub : Button = findViewById(R.id.btn_github)
        btnGithub.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_profile -> {
                val profileIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                profileIntent.putExtra(ProfileActivity.EXTRA_NIM, "L0122006")
                profileIntent.putExtra(ProfileActivity.EXTRA_NAME, "Afif Imam Rahadi")
                profileIntent.putExtra(ProfileActivity.EXTRA_BATCH, "Informatika, 2022")
                profileIntent.putExtra(ProfileActivity.EXTRA_DESCRIPTION, "I am currently studying at Sebelas Maret University")
                startActivity(profileIntent)
            }
            R.id.btn_github -> {
                val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/afifrahadi"))
                startActivity(githubIntent)
            }
        }
    }
}
