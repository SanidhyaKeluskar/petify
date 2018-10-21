package keluskar.sanidhya.findacat

import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.widget.Button
import android.widget.TextSwitcher
import android.widget.TextView
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var goFindaCatView: Button
    lateinit var goFavoriteCatView: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goFindaCatView=findViewById(R.id.button2)
        goFavoriteCatView=findViewById(R.id.button3)

        goFindaCatView.setOnClickListener {
            val intent: Intent=Intent(applicationContext,CatView::class.java)
            startActivity(intent)
        }
        goFavoriteCatView.setOnClickListener {
            val intent: Intent=Intent(applicationContext,FavoriteCatActivity::class.java)
            startActivity(intent)
        }

        runOnUiThread {

        }

        doAsync {
            var catFact:TextView=findViewById(R.id.textView5)
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://catfact.ninja/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            val apiEndpoint = retrofit.create(API::class.java)

            val response = apiEndpoint.getAllPost().execute()

            if (response.isSuccessful) {
                Log.d("hii","retrofit succesfull")
                var news = response.body()
                runOnUiThread {
                    catFact.setText(news?.fact)
                }


            } else {
                Log.d("hii","something wrong")

            }
        }


    }
}
