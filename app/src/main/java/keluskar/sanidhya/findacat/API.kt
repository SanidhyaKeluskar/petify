package keluskar.sanidhya.findacat

import retrofit2.Call
import retrofit2.http.GET
import java.util.*


interface API {
     @GET("fact")
fun getAllPost(): Call<Response>
}