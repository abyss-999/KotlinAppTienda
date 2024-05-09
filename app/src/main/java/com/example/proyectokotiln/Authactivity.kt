package com.example.parcial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectokotiln.R
import com.example.proyectokotiln.Register
import com.example.proyectokotiln.tienda
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Authactivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    private lateinit var auth: FirebaseAuth
    var isSuccessful = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        signupButton = findViewById(R.id.signup)
        auth = Firebase.auth

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            signInUser(email, password)
        }
        signupButton.setOnClickListener {

            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    isSuccessful = true
                    val intent = Intent(this, tienda::class.java)
                    intent.putExtra("EMAIL", email) // Agregar el correo electrónico como un extra
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Error al iniciar sesión. Por favor, verifique sus credenciales.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}






