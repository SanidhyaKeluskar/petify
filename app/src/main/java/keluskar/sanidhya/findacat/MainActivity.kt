package keluskar.sanidhya.findacat

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var goFindaCatView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goFindaCatView=findViewById(R.id.button2)

        goFindaCatView.setOnClickListener {
            val intent: Intent=Intent(applicationContext,CatView::class.java)
            startActivity(intent)

        }


    }
}
