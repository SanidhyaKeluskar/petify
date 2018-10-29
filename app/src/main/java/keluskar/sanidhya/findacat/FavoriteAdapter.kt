package keluskar.sanidhya.findacat

import android.content.Intent
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

    override fun onBindViewHolder(holder: CustomSecondViewHolder, position: Int) {
        holder.view.textView7.text= favorite[position].favName
        val favoriteImageHolder=holder.view.imageView4
        val photoOfFavorite=favorite[position].favImage
        Picasso.get().load(photoOfFavorite).into(favoriteImageHolder)
        holder?.favouriteDetails=favorite
    }

}
class CustomSecondViewHolder(val view : View, var favouriteDetails: List<Favorites>?=null): RecyclerView.ViewHolder(view){
    init {
        view.setOnClickListener {
            val intent = Intent(view.context,CatDetailActivity::class.java)
            intent.putExtra("nameofcat",favouriteDetails!![position]?.favName)
            intent.putExtra("genderofcat",favouriteDetails!![position]?.favGender)
            intent.putExtra("descriptionofcat",favouriteDetails!![position]?.favDesc)
            intent.putExtra("imageofcat",favouriteDetails!![position]?.favImage)
            intent.putExtra("locationofcat",favouriteDetails!![position]?.favLocation)
            intent.putExtra("emailOfCat", favouriteDetails!![position]?.favEmail)
            view.context.startActivity(intent)
        }
    }
}