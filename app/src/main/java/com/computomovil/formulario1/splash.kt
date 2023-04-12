package com.computomovil.formulario1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.computomovil.formulario1.databinding.ActivitySplashBinding
import kotlin.concurrent.thread

class splash : AppCompatActivity() {

    //Vinculación entre la activity y el layout.
    private lateinit var binding:ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Hilo para realizar el cambio de pantalla.
        thread {

            //Tiempo que dura la visualización de la pantalla inicial.
            Thread.sleep(3000)

            //Mensajería hacia la otra pantalla.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            //Elimina la pantalla inicial del stack.
            finish()
        }
    }
}