package fr.hwrzn.naturecollection.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import fr.hwrzn.naturecollection.R

class PlantAdapter(private val layoutId: Int) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    //boîte pour ranger tout les composants à contrôler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 5 //équivaut à {return 5} en java

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

}