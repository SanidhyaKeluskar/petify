package keluskar.sanidhya.findacat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import keluskar.sanidhya.findacat.generated.ResponseTwo
import kotlinx.android.synthetic.main.catlist.*
import kotlinx.android.synthetic.main.zip_dialog.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.xml.transform.Templates

class CatView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catlist)


        if(LocationDetector.postalCode==""){
            toast("Location not avaialable. Please enter zip code")
        }
        else{
            catlistrecycleview.layoutManager = GridLayoutManager(this, 2)
            doNetworkActivity(LocationDetector.postalCode)
        }
    }

    private fun doNetworkActivity(zipCode: String) {
        doAsync {
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.petfinder.com/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()
            val apiEndpoint = retrofit.create(ApiFindCat::class.java)
            apiEndpoint.getAllsecondPost("bc6a0292370a67e30750be05b5384e0a", "cat", zipCode, "json").enqueue(object : Callback<ResponseTwo> {
                override fun onFailure(call: Call<ResponseTwo>, t: Throwable) {
                 toast("Error in Network call please try later")
                }

                override fun onResponse(call: Call<ResponseTwo>, response: Response<ResponseTwo>) {

                    val responseBody = response.body()
                    runOnUiThread {
                        catlistrecycleview.adapter = CatAdapter(responseBody!!)
                    }
                    val pets = responseBody?.petfinder?.pets


                }

            })

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.viewcatmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId==R.id.enterzipcode){
            Toast.makeText(this,"item selected",Toast.LENGTH_LONG).show()
        }

        val mDialogView=LayoutInflater.from(this).inflate(R.layout.zip_dialog,null)
        val mBuilder=AlertDialog.Builder(this )
                .setView(mDialogView)
                .setTitle("Zip Code")
        val mAlertDialog=mBuilder.show()

        mDialogView.dialogsubmit.setOnClickListener{
            mAlertDialog.dismiss()
            val zipCode=mDialogView.dialogzipcode.text.toString()
            Toast.makeText(this,zipCode,Toast.LENGTH_LONG).show()
            catlistrecycleview.layoutManager = GridLayoutManager(this, 2)
            doNetworkActivity(zipCode)
        }

        mDialogView.dialogcancel.setOnClickListener{
            mAlertDialog.dismiss()
        }

        return super.onOptionsItemSelected(item)
    }
}

