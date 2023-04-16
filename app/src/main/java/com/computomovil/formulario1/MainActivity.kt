package com.computomovil.formulario1
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.computomovil.formulario1.DatePickerFragment
import com.computomovil.formulario1.R.string.*
import com.computomovil.formulario1.databinding.ActivityMainBinding
import java.io.IOException
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding:ActivityMainBinding
    private lateinit var names:String
    private lateinit var surname:String
    private lateinit var birthdate:String
    private lateinit var email:String
    private lateinit var career:String
    private lateinit var aNumber: String
    private lateinit var aaCareers: ArrayAdapter<String>
    private lateinit var careerSelected:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Límitando caracteres del edit text de número de cuenta.
        binding.etAccountNumber.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(9))

        //Arreglo de carreras.
        aaCareers = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item)

        val carersOptions = resources.getStringArray(R.array.careers)
        val adapter = ArrayAdapter(this, R.layout.spinner_items, carersOptions)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        aaCareers.addAll(
            listOf("Selecciona tu carrera",
                "Ingeniería Aeroespacial", "Ingeniería Civil",
                "Ingeniería Geomática", "Ingeniería Ambiental", "Ingeniería Geofísica",
                "Ingeniería Geológica", "Ingeniería Petrolera", "Ingeniería de Minas y Metalurgia",
                "Ingeniería en Computación", "Ingeniería Eléctrica Electrónica",
                "Ingeniería en Telecomunicaciones", "Ingeniería Mecánica", "Ingeniería Industrial",
                "Ingeniería Mecatrónica", "Ingeniería en Sistemas Biomédicos")
        )

        binding.spinnerCareers.contentDescription = "Selecciona tu carrera"
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

    fun validateEmail(email: String): Boolean {
        var patroncito: Pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        var comparador: Matcher = patroncito.matcher(email)
        return comparador.find()
    }

    fun click(view: View) {

        //Bandera para validar datos correctos.
        var verifyData:Boolean = true

        //Varíables que se obtienen del layout.
        names = binding.etNames.text.toString()
        surname = binding.etSurname.text.toString()
        birthdate = binding.etDate.text.toString()
        email = binding.etMail.text.toString()
        aNumber = binding.etAccountNumber.text.toString()
        career = careerSelected

        val intent = Intent(this, results::class.java)
        val bundle = Bundle()

        //Validando nombres.
        if (names.isNotEmpty()){
            bundle.putString("name", names)

        }else{
            verifyData = false
            binding.etNames.error = resources.getString(R.string.emptyInputName)
            Toast.makeText(this, resources.getString(R.string.toastName),
                Toast.LENGTH_SHORT).show()
        }

        //Validando apellidos.
        if (surname.isNotEmpty()){
            bundle.putString("surname", surname)

        }else{
            verifyData = false
            binding.etSurname.error = resources.getString(R.string.emptyInputSurname)
            Toast.makeText(this, resources.getString(R.string.toastSurname),
                Toast.LENGTH_SHORT).show()
        }

        //Validando fecha de nacimiento.
        if (birthdate.isNotEmpty()){
            bundle.putString("birthdate", birthdate)

        }else{
            verifyData = false
            binding.etDate.error = resources.getString(R.string.emptyInputDate)
            Toast.makeText(this, resources.getString(R.string.toastDate),
                Toast.LENGTH_SHORT).show()
        }

        //Validando el correo eletrónico.
        if (email.isNotEmpty()){
            Log.d("LOGTAG", email)

            if (validateEmail(email)) {
                bundle.putString("email", email)
            } else {
                verifyData = false
                binding.etMail.error = resources.getString(R.string.emptyInputMail)
                Toast.makeText(this, resources.getString(R.string.toastInvalidMail),
                    Toast.LENGTH_SHORT).show()
            }
        }else{
            verifyData = false
            binding.etMail.error = resources.getString(R.string.emptyInputMail)
            Toast.makeText(this, resources.getString(R.string.toastMail),
                Toast.LENGTH_SHORT).show()
        }


        //Validando número de cuenta.
        if (aNumber.isNotBlank()){
                if (aNumber.length != 9) {
                    verifyData = false
                    Toast.makeText(this, resources.getString(R.string.toastInvalidaNumber2),
                        Toast.LENGTH_SHORT).show()
                } else {
                    bundle.putString("aNumber", aNumber)
                }
        } else {
            verifyData = false
            binding.etAccountNumber.error = resources.getString(R.string.emptyInputaNumber)
            Toast.makeText(this, resources.getString(R.string.toastInvalidaNumber),
                Toast.LENGTH_SHORT).show()
            }

        //Validando la carrera seleccionada.
        when (career) {
            "Selecciona tu carrera" -> {
                verifyData = false
                Toast.makeText(this, resources.getString(R.string.toastInvalidCareer),
                    Toast.LENGTH_SHORT).show()
            }
            "Select Career" -> {
                verifyData = false
                Toast.makeText(this, resources.getString(R.string.toastInvalidCareer),
                    Toast.LENGTH_SHORT).show()
            }
            else -> bundle.putString("career", career)
        }

        //Bandera de datos correctos.
        if (verifyData) {
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun setDateSelected(year:Int, month:Int, day:Int) {
        binding.etDate.setText("$day/$month/$year")
    }
}