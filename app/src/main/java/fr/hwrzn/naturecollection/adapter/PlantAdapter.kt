package fr.hwrzn.naturecollection.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.hwrzn.naturecollection.MainActivity
import fr.hwrzn.naturecollection.PlantModel
import fr.hwrzn.naturecollection.PlantRepository
import fr.hwrzn.naturecollection.R

class PlantAdapter(private val context: MainActivity, private val plantList: List<PlantModel>,private val layoutId: Int) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    //boîte pour ranger tout les composants à contrôler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName:TextView? = view.findViewById<TextView>(R.id.name_item)
        val plantDescription:TextView? = view.findViewById<TextView>(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = plantList.size //en kotlin on peut mettre = lorsqu'il y a qu'une seule instruction

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //récupérer les informatiuons de la plante
        val currentPlant = plantList[position]

        //récupérer le repository
        val repo = PlantRepository()

        //utiliser la dépendance glide pour récupéer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)

        //mettre à jour le nom de la plante
        holder.plantName?.text = currentPlant.name

        //mettre à jour la description de la plante
        holder.plantDescription?.text = currentPlant.description

        //vérifier si la plante a été likée
        if(currentPlant.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)

        }

        //rajouter une interaction sur cette étoile
        holder.starIcon.setOnClickListener {
            //inverse si le bouton est liké ou non
            currentPlant.liked = !currentPlant.liked
            //mettre à jour l'objet plante
            repo.updatePlant(currentPlant)
        }

    }

}