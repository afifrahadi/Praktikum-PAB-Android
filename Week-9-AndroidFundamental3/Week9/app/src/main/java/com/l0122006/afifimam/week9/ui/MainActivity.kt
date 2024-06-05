package com.l0122006.afifimam.week9.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.l0122006.afifimam.week9.R
import com.l0122006.afifimam.week9.response.ApiResponse
import com.l0122006.afifimam.week9.response.UserResponse
import com.l0122006.afifimam.week9.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var tvTitle : TextView
    private lateinit var tvReleaseDate : TextView
    private lateinit var btnToList: Button
    private lateinit var imgPoster: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTitle = findViewById(R.id.judul_show)
        tvReleaseDate = findViewById(R.id.rilis_show)
        imgPoster = findViewById(R.id.foto_show)
        btnToList = findViewById(R.id.btn_to_list)
        btnToList.setOnClickListener(this)

        getUsers()
    }

    private fun getUsers() {
        ApiConfig.apiService().getUsers().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()?.results
                    data?.let {
                        val selectedData = data[0]
                        displayTV(selectedData)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("Error", "" + t.stackTraceToString())
            }
        })
    }

    fun displayTV(data: UserResponse) {
        tvTitle.text = data.name
        tvReleaseDate.text = data.firstAirDate
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${data.imageUrl}")
            .into(imgPoster)
    }
    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_to_list -> {
                val showMoreIntent = Intent(this, LIstActivity::class.java)
                startActivity(showMoreIntent)
            }
        }
    }
}