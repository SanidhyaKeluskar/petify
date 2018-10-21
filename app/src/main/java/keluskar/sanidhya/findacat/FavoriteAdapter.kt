package keluskar.sanidhya.findacat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import keluskar.sanidhya.findacat.generated.Favorites
import keluskar.sanidhya.findacat.generated.ResponseTwo
import kotlinx.android.synthetic.main.favorite_view.view.*

class FavoriteAdapter(val favorite:List<Favorites>) : RecyclerView.Adapter<CustomSecondViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CustomSecondViewHolder {
        val LayoutInflater= LayoutInflater.from(parent.context)
        val cell=LayoutInflater.inflate(R.layout.favorite_view,parent,false)
        return CustomSecondViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return favorite.size
         }

    override fun onBindViewHolder(holder: CustomSecondViewHolder, p1: Int) {
        holder.view.textView7.text= favorite[p1].favName
        val favoriteImageHolder=holder.view.imageView4
        val photoOfFavorite=favorite[p1].favImage
        Picasso.get().load(photoOfFavorite).into(favoriteImageHolder)
    }

}
class CustomSecondViewHolder(val view : View): RecyclerView.ViewHolder(view){

}