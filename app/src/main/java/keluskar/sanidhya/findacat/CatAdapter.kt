package keluskar.sanidhya.findacat

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import keluskar.sanidhya.findacat.generated.ResponseTwo
import kotlinx.android.synthetic.main.catview.view.*
import javax.xml.transform.Templates

class CatAdapter(val responseBody: ResponseTwo) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CustomViewHolder {
        val LayoutInflater=LayoutInflater.from(parent.context)
        val cell=LayoutInflater.inflate(R.layout.catview,parent,false)
        return CustomViewHolder(cell)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder?.view?.textView6?.text= responseBody?.petfinder?.pets?.pet!![position]?.name?.T.toString()
        val catimageviewholder=holder?.view?.imageView2
        val photoItem = responseBody?.petfinder?.pets?.pet!![position]?.media?.photos?.photo!![2]
        Picasso.get().load(if (photoItem != null) photoItem.T else null).into(catimageviewholder)
        holder?.responseDetails=responseBody
    }


    override fun getItemCount(): Int {
         return 24
    }


}
class CustomViewHolder(val view : View, var responseDetails: ResponseTwo?=null): RecyclerView.ViewHolder(view){
    init {
        view.setOnClickListener {
            val intent = Intent(view.context,CatDetailActivity::class.java)
            intent.putExtra("nameofcat",responseDetails?.petfinder?.pets?.pet!![position]?.name?.T)
            intent.putExtra("genderofcat",responseDetails?.petfinder?.pets?.pet!![position]?.sex?.T)
            intent.putExtra("descriptionofcat",responseDetails?.petfinder?.pets?.pet!![position]?.description?.T)
            intent.putExtra("imageofcat",responseDetails?.petfinder?.pets?.pet!![position]?.media?.photos?.photo!![2]?.T)
            view.context.startActivity(intent)
        }
    }

}