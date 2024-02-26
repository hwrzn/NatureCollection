package fr.hwrzn.naturecollection.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import fr.hwrzn.naturecollection.Login
import fr.hwrzn.naturecollection.MainActivity
import fr.hwrzn.naturecollection.PlantModel
import fr.hwrzn.naturecollection.PlantRepository
import fr.hwrzn.naturecollection.PlantRepository.Singleton.downloadUri
import fr.hwrzn.naturecollection.R
import java.util.UUID

class AddPlantFragment(private val context: MainActivity) : Fragment() {

    private var file: Uri? = null
    private var uploadedImage: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add_plant, container, false)

        //récupérer uploadedImage pour lui associer son composant
        uploadedImage = view?.findViewById(R.id.preview_image)

        //récupérer le Button pour charger l'image
        val pickupImageButton = view?.findViewById<AppCompatButton>(R.id.upload_button)

        //lorsqu'on clique dessus ça affiche les images du téléphones
        pickupImageButton?.setOnClickListener { pickupImage() }

        //récupérer le bouton confirmer
        val confirmButton = view?.findViewById<AppCompatButton>(R.id.confirm_button)
        confirmButton?.setOnClickListener {
            sendForm(view)
            Toast.makeText(context, "Plante ajoutée.", Toast.LENGTH_SHORT,).show()
        }

        return view
    }

    private fun sendForm(view: View) {
        val repo = PlantRepository()
        repo.uploadImage(file!!) {
            val plantName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val plantDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
            val plantGrow = view.findViewById<Spinner>(R.id.grow_spinner).selectedItem.toString()
            val plantWater = view.findViewById<Spinner>(R.id.water_spinner).selectedItem.toString()
            val downloadImageUrl = downloadUri

            //créer un nouvel objet de type PlantModel
            val plant = PlantModel(
                UUID.randomUUID().toString(),
                plantName,
                plantDescription,
                downloadImageUrl.toString(),
                plantGrow,
                plantWater,
            )

            //envoyer en bdd
            repo.insertPlant(plant)

        }
    }

    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 47 && resultCode == Activity.RESULT_OK) {

            //vérifier sur les données sont nulles
            if(data == null || data.data == null) return

            //récupérer l'image
            file = data.data

            //mettre à jour l'aperçu de l'image
            uploadedImage?.setImageURI(file)
        }
    }

}