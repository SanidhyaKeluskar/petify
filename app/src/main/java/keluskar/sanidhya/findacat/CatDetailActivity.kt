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
    lateinit var nameOfCat: String
    lateinit var emailOfCat: String
    lateinit var photoOfCat:String
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

        nameOfCat=intent.getStringExtra("nameofcat")
        photoOfCat=intent.getStringExtra("imageofcat")
        emailOfCat=intent.getStringExtra("emailOfCat")

        catName.text = nameOfCat
        catGender.text = intent.getStringExtra("genderofcat")
        catdesc.text = intent.getStringExtra("descriptionofcat")
        catLocation.text = intent.getStringExtra("locationofcat")

        Picasso.get().load(photoOfCat).into(catdeatilsimage)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cat_details_activity_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.share_button ->shareButtonPressed(item)
            R.id.email_button ->sendEmail()
            R.id.favoritesbutton ->persistenceManager.saveFavorites(intent.getStringExtra("imageofcat"),intent.getStringExtra("nameofcat"),intent.getStringExtra("locationofcat"),intent.getStringExtra("genderofcat"),intent.getStringExtra("descriptionofcat"),emailOfCat )



        }

        return super.onOptionsItemSelected(item)
    }
    fun shareButtonPressed(item: MenuItem) {
        val sendIntent = Intent()

        sendIntent.action = Intent.ACTION_SEND

        val shareText = "Check out this cat named: " + nameOfCat +". Email " + emailOfCat+" for more info. "+ "For Image of Cat please check: " + photoOfCat
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText)
        sendIntent.type = "text/plain"

        startActivity(Intent.createChooser(sendIntent,"Share"))
    }



    fun sendEmail(){
        val concatemail="mailto:"+emailOfCat
        val concatsubject="I’m interested in your cat named " +nameOfCat
        val mailto = concatemail +
                "?cc=" + "" +
                "&subject=" + Uri.encode(concatsubject) +
                "&body=" + Uri.encode("Please can i get more info about the cat")
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse(mailto)

        try {
            startActivity(emailIntent)
        } catch (e: ActivityNotFoundException) {

        }

    }


}