package com.example.financasapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialize a instância de AuthManager
        auth = FirebaseAuth.getInstance()

        // Referencie o botão de login
        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val email = findViewById<EditText>(R.id.emailText).text.toString()
            val senha = findViewById<EditText>(R.id.senhaText).text.toString()
            loginUser(email, senha)
        }

        // Referencie o botão de cadastrar
        val cadastrarButton: Button = findViewById(R.id.cadastrarButton)
        cadastrarButton.setOnClickListener {
            val intent = Intent(this, TelaCadastro::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, senha: String) {
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                makeText(this, "Login realizado com sucesso", LENGTH_SHORT).show()
                val intent = Intent(this, Financas::class.java)
                startActivity(intent)
            } else {
                makeText(this, "Erro ao fazer login", LENGTH_SHORT).show()

            }
        }
    }
}
