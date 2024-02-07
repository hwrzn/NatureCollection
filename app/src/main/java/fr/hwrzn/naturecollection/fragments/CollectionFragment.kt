package fr.hwrzn.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.hwrzn.naturecollection.MainActivity
import fr.hwrzn.naturecollection.PlantRepository.Singleton.plantList
import fr.hwrzn.naturecollection.R
import fr.hwrzn.naturecollection.adapter.PlantAdapter
import fr.hwrzn.naturecollection.adapter.PlantItemDecoration

class CollectionFragment(private val context: MainActivity) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false)

        //récupérer la RecyclerView
        val collectionRecylerView = view?.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecylerView?.adapter = PlantAdapter(context, plantList.filter { it.liked }, R.layout.item_vertical_plant)
        collectionRecylerView?.layoutManager = LinearLayoutManager(context)
        collectionRecylerView?.addItemDecoration(PlantItemDecoration())

        return view
    }

}