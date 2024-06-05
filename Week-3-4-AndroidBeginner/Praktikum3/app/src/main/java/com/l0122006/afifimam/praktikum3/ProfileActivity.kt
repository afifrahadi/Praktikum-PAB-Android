package com.l0122006.afifimam.praktikum3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val EXTRA_NIM = "extra_nim"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_BATCH = "extra_batch"
        const val EXTRA_DESCRIPTION = "extra_description"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnShare : Button = findViewById(R.id.btn_share)
        btnShare.setOnClickListener(this)
        
        val tvProfileData : TextView = findViewById(R.id.tv_profile_data)
        val nim = intent.getStringExtra(EXTRA_NIM)
        val name = intent.getStringExtra(EXTRA_NAME)
        val batch = intent.getStringExtra(EXTRA_BATCH)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)

        val text = """
            $nim
            
            $name

            $batch

            Self Description

            $description
        """.trimIndent()

        tvProfileData.text = text
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_SUBJECT, "Name : Afif Imam Rahadi \n Nim : L0122006")
                intent.putExtra(Intent.EXTRA_TEXT, "Name : Afif Imam Rahadi \n Nim : L0122006")

                //Menampilkan daftar aplikasi yang dapat menangani intent share
                startActivity(Intent.createChooser(intent, "Share via"))
            }
        }
    }
}