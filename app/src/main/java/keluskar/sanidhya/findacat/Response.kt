package keluskar.sanidhya.findacat


import com.squareup.moshi.Json


data class Response(

	@Json(name="fact")
	val fact: String? = null,

	@Json(name="length")
	val length: Int? = null
)