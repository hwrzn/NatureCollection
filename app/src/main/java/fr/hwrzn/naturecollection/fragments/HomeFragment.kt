package fr.hwrzn.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.hwrzn.naturecollection.MainActivity
import fr.hwrzn.naturecollection.PlantModel
import fr.hwrzn.naturecollection.R
import fr.hwrzn.naturecollection.adapter.PlantAdapter
import fr.hwrzn.naturecollection.adapter.PlantItemDecoration

class HomeFragment(private val context: MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        //créer une liste qui va stocker les plantes
        val plantList = arrayListOf<PlantModel>()

        //enregister une première plante dans notre liste (pissenlit)
        plantList.add(PlantModel(
            "Pissenlit",
            "Jaune soleil",
            "https://images.unsplash.com/photo-1559979322-e64a4397c29f?q=80&w=1936&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            true
        ))

        //enregister une deuxième plante dans notre liste (rose)
        plantList.add(PlantModel(
            "Rose",
            "Ça pique",
            "https://images.unsplash.com/photo-1565573349860-cbf26ef3f40f?q=80&w=1935&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            false
        ))

        //enregister une troisième plante dans notre liste (cactus)
        plantList.add(PlantModel(
            "Cactus",
            "Ça pique aussi",
            "https://images.unsplash.com/photo-1555051932-24675e24a454?q=80&w=2151&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            true
        ))

        //enregister une quatrième plante dans notre liste (tulipe)
        plantList.add(PlantModel(
            "Tulipe",
            "C'est beau",
            "https://images.unsplash.com/photo-1614791199038-6869a104fe5f?q=80&w=1974&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            false
        ))

        //récupérer le premier RecyclerView
        val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView?.adapter = PlantAdapter(context, plantList, R.layout.item_horizontal_plant)

        //récupérer le second RecyclerView
        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)
        verticalRecyclerView?.addItemDecoration(PlantItemDecoration())

        return view
    }

}