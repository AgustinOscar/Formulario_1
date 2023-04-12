package com.computomovil.formulario1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val bundle = intent.extras

        if (bundle != null) {
            val nombre = bundle.getString("Parametro2", "")
            var num = bundle.getInt("Parametro1", 0)
        }

    }
}