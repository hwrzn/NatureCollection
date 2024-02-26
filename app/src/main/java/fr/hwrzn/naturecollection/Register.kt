package fr.hwrzn.naturecollection

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        var editTextEmail : TextInputEditText = findViewById(R.id.email)
        var editTextPassword : TextInputEditText = findViewById(R.id.password)
        var progressBar : ProgressBar = findViewById(R.id.progressBar)
        var buttonReg : Button = findViewById(R.id.btn_register)
        var textView : TextView = findViewById(R.id.loginNow)

        textView.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

        buttonReg.setOnClickListener {
            var email = editTextEmail.text.toString()
            var password = editTextPassword.text.toString()

            progressBar.visibility = View.VISIBLE

            if(TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Entrez un email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    progressBar.visibility = View.GONE

                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Inscription réussie.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Inscription échouée.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }

        }

    }
}