package keluskar.sanidhya.findacat.generated


import com.squareup.moshi.Json


data class ResponseTwo(

	@field:Json(name="petfinder")
	val petfinder: Petfinder? = null,

	@field:Json(name="@version")
	val version: String? = null,

	@field:Json(name="@encoding")
	val encoding: String? = null
)