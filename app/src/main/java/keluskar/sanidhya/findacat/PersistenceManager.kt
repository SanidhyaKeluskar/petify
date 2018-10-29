package keluskar.sanidhya.findacat

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import keluskar.sanidhya.findacat.generated.Favorites
import java.io.IOException

class PersistenceManager (private val context: Context){
    private val sharedPreferences: SharedPreferences

    init {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun saveFavorites( favImage: String, favName: String, favLocation: String,favGender: String, favDesc: String, favEmail: String){
        val favorites= fetchFavorites().toMutableList()
        val element=Favorites(favImage,favName, favLocation, favGender, favDesc, favEmail)
        if(favorites.contains(element)){
            Toast.makeText(context,"Deleted from Favorites",Toast.LENGTH_LONG).show()
            favorites.remove(element)
        }
        else{
        favorites.add(element)
        Toast.makeText(context,"Added to Favorites",Toast.LENGTH_LONG).show()
        }
        val editor = sharedPreferences.edit()

        val moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, Favorites::class.java)
        val jsonAdapter = moshi.adapter<List<Favorites>>(listType)
        val jsonString = jsonAdapter.toJson(favorites)

        editor.putString("FAVORITES", jsonString)

        editor.apply()
    }

    fun fetchFavorites(): List<Favorites> {
        val jsonString = sharedPreferences.getString("FAVORITES", null)

        if(jsonString == null) {
            return arrayListOf<Favorites>() as List<Favorites>
        }
        else{
            val listType = Types.newParameterizedType(List::class.java, Favorites::class.java)
            val moshi = Moshi.Builder()
                    .build()
            val jsonAdapter = moshi.adapter<List<Favorites>>(listType)
            var favorites:List<Favorites>? = emptyList<Favorites>()
            try {
                favorites = jsonAdapter.fromJson(jsonString)
            } catch (e: IOException) {
                Log.e("hii", e.message)
            }

            if (favorites!=null){
                return favorites
            }
            else{
                return emptyList<Favorites>()
            }
        }

    }
}