package keluskar.sanidhya.findacat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import keluskar.sanidhya.findacat.generated.ResponseTwo
import kotlinx.android.synthetic.main.catlist.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.xml.transform.Templates

class CatView : AppCompatActivity() {
    val animals: ArrayList<Int> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catlist)

        animals.add(R.drawable.ic_launcher_background)
        animals.add(R.drawable.ic_launcher_foreground)
        animals.add(R.drawable.ic_launcher_background)
        catlistrecycleview.layoutManager = GridLayoutManager(this, 2)
        catlistrecycleview.adapter=CatAdapter()

        doAsync {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.petfinder.com/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            val apiEndpoint = retrofit.create(ApiFindCat::class.java)
          apiEndpoint.getAllsecondPost("bc6a0292370a67e30750be05b5384e0a","cat","20037","json").enqueue(object: Callback<ResponseTwo>{
              override fun onFailure(call: Call<ResponseTwo>, t: Throwable) {
                  Log.d("hii","Wrong")
                  Log.d("hii",t.toString())
              }

              override fun onResponse(call: Call<ResponseTwo>, response: Response<ResponseTwo>) {
                 Log.d("hii","succesfull")
                  val news = response.body().toString()

                //  var state= news?.petfinder?.xmlnsXsi.toString()
                  Log.d("hii",news)




              }

          })

        }


    }
}

