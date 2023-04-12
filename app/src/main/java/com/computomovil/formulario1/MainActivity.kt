package com.computomovil.formulario1
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.computomovil.formulario1.DatePickerFragment
import com.computomovil.formulario1.databinding.ActivityMainBinding
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var names:String
    private lateinit var surname:String
    private lateinit var birthdate:String
    private lateinit var email:String
    private lateinit var career:String
    private var aNumber by Delegates.notNull<Long>()
    private lateinit var aaCareers: ArrayAdapter<String>
    private lateinit var careerSelected:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Arreglo de carreras.
        aaCareers = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item)

        val carersOptions = resources.getStringArray(R.array.careers)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carersOptions)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aaCareers.addAll(
            listOf("Ingeniería Aeroespacial", "Ingeniería Civil",
            "Ingeniería Geomática", "Ingeniería Ambiental", "Ingeniería Geofísica",
            "Ingeniería Geológica", "Ingeniería Petrolera", "Ingeniería de Minas y Metalurgia",
            "Ingeniería en Computación", "Ingeniería Eléctrica Electrónica",
            "Ingeniería en Telecomunicaciones", "Ingeniería Mecánica", "Ingeniería Industrial",
            "Ingeniería Mecatrónica", "Ingeniería en Sistemas Biomédicos")
        )

        binding.spinnerCareers.onItemSelectedListener = this
        binding.spinnerCareers.adapter = adapter

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        careerSelected = aaCareers.getItem(position).toString()
        //binding.tvCarrer.text = careerSelected
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    //Método para la selección de fecha.
    fun clickDate(view: View) {
        val date = DatePickerFragment { year, month, day -> setDateSelected(year, month, day)}
        date.show(supportFragmentManager, "DatePicker")
    }

    fun click(view: View) {

        //Bandera para validar datos correctos.
        var verify:Boolean = true

        //Varíables que se obtienen del layout.
        names = binding.etNames.text.toString()
        surname = binding.etSurname.text.toString()
        birthdate =binding.etDate.text.toString()
        email = binding.etMail.text.toString()
        aNumber = binding.etAccountNumber.text.toString().toLong()
        career = careerSelected

        val intent = Intent(this, results::class.java)
        val bundle = Bundle()

        if (names.isNotEmpty()) {
            Log.d("LOGTAG", names)
            bundle.putString("name", names)


            bundle.putString("Parametro2", "Agustin Oscar")
            intent.putExtras(bundle)
            startActivity(intent)

        } else {
            binding.etNames.error = "Valor requerido."
            Toast.makeText(this, resources.getString(R.string.toastName), Toast.LENGTH_SHORT).show()
            //Placeholders.
            //binding.etNames.text = resources.getString(R.string.placeholders, 1, "setenta y siete")
        }
    }

    private fun setDateSelected(year:Int, month:Int, day:Int) {
        binding.etDate.setText("$day/$month/$year")
    }
}
