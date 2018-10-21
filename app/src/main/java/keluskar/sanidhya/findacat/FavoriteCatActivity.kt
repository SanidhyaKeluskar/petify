package keluskar.sanidhya.findacat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.catlist.*
import kotlinx.android.synthetic.main.favorite_cat_list.*

class FavoriteCatActivity: AppCompatActivity() {
    private lateinit var persistenceManager: PersistenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_cat_list)
        persistenceManager = PersistenceManager(this)
        favoriteRecyclerView.layoutManager = GridLayoutManager(this, 1)
        val favorites=persistenceManager.fetchFavorites()
        favoriteRecyclerView.adapter=FavoriteAdapter(favorites)

    }
}