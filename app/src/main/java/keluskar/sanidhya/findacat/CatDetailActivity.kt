package keluskar.sanidhya.findacat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CatDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_details)
        var catName: TextView =findViewById(R.id.textView11)
        var catGender:TextView =findViewById(R.id.textView2)
        var catdesc:TextView =findViewById(R.id.textView4)
        var catdeatilsimage: ImageView=findViewById(R.id.imageView)
        catName.setText(intent.getStringExtra("nameofcat"))
        catGender.setText(intent.getStringExtra("genderofcat"))
        catdesc.setText(intent.getStringExtra("descriptionofcat"))

        Picasso.get().load(intent.getStringExtra("imageofcat")).into(catdeatilsimage)
    }
}