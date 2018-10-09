package keluskar.sanidhya.findacat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.catlist.*

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
    }
}

