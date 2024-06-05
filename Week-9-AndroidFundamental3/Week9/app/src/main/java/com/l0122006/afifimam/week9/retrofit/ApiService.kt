package com.l0122006.afifimam.week9.retrofit

import com.l0122006.afifimam.week9.response.ApiResponse
import com.l0122006.afifimam.week9.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("trending/tv/day?api_key=06d6b89b66a1c459be23eb0a647b5115")
    fun getUsers(): Call<ApiResponse>
}