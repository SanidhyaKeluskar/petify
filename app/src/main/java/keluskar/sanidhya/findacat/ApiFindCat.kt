package keluskar.sanidhya.findacat

import keluskar.sanidhya.findacat.generated.Photos
import keluskar.sanidhya.findacat.generated.ResponseTwo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFindCat {
    @GET("pet.find")
    fun getAllsecondPost(@Query("key") key: String,@Query("animal") animal: String,@Query("location") location: String,@Query("format") format: String): Call<ResponseTwo>
}