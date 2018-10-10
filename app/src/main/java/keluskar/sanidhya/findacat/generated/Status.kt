package keluskar.sanidhya.findacat.generated


import com.squareup.moshi.Json


data class Status(

	@Json(name="code")
	val code: Code? = null,

	@Json(name="message")
	val message: Message? = null,

	@Json(name="\$t")
	val T: String? = null
)