package keluskar.sanidhya.findacat.generated


import com.squareup.moshi.Json


data class ResponseTwo(

	@Json(name="petfinder")
	val petfinder: Petfinder? = null,

	@Json(name="@version")
	val version: String? = null,

	@Json(name="@encoding")
	val encoding: String? = null
)