package com.l0122006.afifimam.week9.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.l0122006.afifimam.week9.databinding.ActivityListBinding
import com.l0122006.afifimam.week9.response.ApiResponse
import com.l0122006.afifimam.week9.response.UserResponse
import com.l0122006.afifimam.week9.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LIstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        adapter = UserAdapter(this, arrayListOf())

        binding.rvUser.adapter = adapter
        binding.rvUser.setHasFixedSize(true)
        binding.rvUser.layoutManager = LinearLayoutManager(this)

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
                        setData(it)
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.d("Error", "" + t.stackTraceToString())
            }
        })
    }

    fun setData(data: ArrayList<UserResponse>) {
        adapter.setData(data)
    }
}