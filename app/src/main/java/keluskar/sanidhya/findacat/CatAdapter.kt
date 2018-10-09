package keluskar.sanidhya.findacat

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.xml.transform.Templates

class CatAdapter : RecyclerView.Adapter<CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CustomViewHolder {
        val LayoutInflater=LayoutInflater.from(parent.context)
        val cell=LayoutInflater.inflate(R.layout.catview,parent,false)
        return CustomViewHolder(cell)
    }

    override fun onBindViewHolder(parent: CustomViewHolder, p1: Int) {

    }


    override fun getItemCount(): Int {
         return 30
    }


}
class CustomViewHolder(v: View): RecyclerView.ViewHolder(v){

}