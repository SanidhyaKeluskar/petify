package keluskar.sanidhya.findacat


import com.squareup.moshi.Json


data class Response(

	@field:Json(name="fact")
	val fact: String? = null,

	@field:Json(name="length")
	val length: Int? = null
)