package keluskar.sanidhya.findacat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.widget.Toast
import android.content.ActivityNotFoundException





class CatDetailActivity: AppCompatActivity() {
    private lateinit var persistenceManager: PersistenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_details)
        persistenceManager = PersistenceManager(this)
        var catName: TextView =findViewById(R.id.textView11)
        var catGender:TextView =findViewById(R.id.textView2)
        var catdesc:TextView =findViewById(R.id.textView4)
        var catdeatilsimage: ImageView=findViewById(R.id.imageView)
        var catLocation: TextView=findViewById(R.id.textView12)

        catName.text = intent.getStringExtra("nameofcat")
        catGender.text = intent.getStringExtra("genderofcat")
        catdesc.text = intent.getStringExtra("descriptionofcat")
        catLocation.text = intent.getStringExtra("locationofcat")

        Picasso.get().load(intent.getStringExtra("imageofcat")).into(catdeatilsimage)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cat_details_activity_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.share_button ->shareButtonPressed(item)
            R.id.email_button ->sendEmail()
            R.id.favoritesbutton ->persistenceManager.saveFavorites(intent.getStringExtra("imageofcat"),intent.getStringExtra("nameofcat"))



        }

        return super.onOptionsItemSelected(item)
    }
    fun shareButtonPressed(item: MenuItem) {
        val sendIntent = Intent()

        sendIntent.action = Intent.ACTION_SEND

        val shareText = "hii"
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText)
        sendIntent.type = "text/plain"

        startActivity(Intent.createChooser(sendIntent,"Share"))
    }



    fun sendEmail(){
        val mailto = "mailto:bob@example.org" +
                "?cc=" + "alice@example.com" +
                "&subject=" + Uri.encode("jjjj") +
                "&body=" + Uri.encode("bbb")
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse(mailto)

        try {
            startActivity(emailIntent)
        } catch (e: ActivityNotFoundException) {

        }

    }


}