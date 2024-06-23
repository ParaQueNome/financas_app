package com.example.financasapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class TelaCadastro : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var senhaEditText: EditText
    private lateinit var confirmarSenhaEditText: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)

        // Inicialize a instância do Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Inicializa as views
        emailEditText = findViewById(R.id.editTextEmail)
        senhaEditText = findViewById(R.id.editTextSenha)
        confirmarSenhaEditText = findViewById(R.id.editTextConfirmarSenha)

        // Referência para o botão de cadastrar
        val cadastrarButton = findViewById<Button>(R.id.buttonCadastrar)
        cadastrarButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val senha = senhaEditText.text.toString()
            cadastrarUser(email, senha)
        }
    }

    private fun cadastrarUser(email: String, senha: String) {
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finaliza esta atividade para evitar voltar
            } else {
                Toast.makeText(this, "Erro ao fazer cadastro", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
