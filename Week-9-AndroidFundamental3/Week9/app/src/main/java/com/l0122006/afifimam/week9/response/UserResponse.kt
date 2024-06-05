package com.l0122006.afifimam.week9.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	val popularity: Any? = null,

	val name: String? = null,

	@field:SerializedName("poster_path")
	val imageUrl: String? = null
)