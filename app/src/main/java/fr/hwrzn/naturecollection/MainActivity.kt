package fr.hwrzn.naturecollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.hwrzn.naturecollection.fragments.CollectionFragment
import fr.hwrzn.naturecollection.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //charger notre PlantRepository
        val repo = PlantRepository()

        repo.updateData{
            //injection de HomeFragment dans notre FrameLayout (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, CollectionFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }
}