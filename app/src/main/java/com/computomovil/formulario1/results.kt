package com.computomovil.formulario1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.computomovil.formulario1.databinding.ActivityResultsBinding

import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class results : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras

        if (bundle != null) {
            val name = bundle.getString("name", "")
            val surname = bundle.getString("surname", "")
            val date = bundle.getString("birthdate", "")
            val mail = bundle.getString("email", "")
            val aNumber = bundle.getString("aNumber", "")
            val career = bundle.getString("career", "")

            val ds = date.split("/") //Data Split

            //Se calcula la edad en años
            val age:Int = calculateAge(ds[2], ds[1], ds[0])

            //Signo Zodiacal.
            val zs:String = zodiacSign(ds[0], ds[1])

            //Signo Chino.
            val cs:String = chineseSign(ds[2])

            binding.nameResultsR.text = "$name $surname"
            binding.ageResultsR.text = resources.getString(R.string.ageR, age)
            binding.zodiacalSignR.text = zs
            binding.chineseSignR.text = cs
            binding.mailR.text = mail
            binding.aNumberR.text = aNumber

            selectimage(binding.imageView, career)

            Log.d("LOGTAG", career)

        }

    }

    fun selectimage(iv:ImageView, career:String){

        if (career == "Ingeniería Aeroespacial" || career == "Aerospace Engineering") {
            val resourceId = iv.context.resources.getIdentifier("aeroespacial",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Civil" || career == "Civil Engineering") {
            val resourceId = iv.context.resources.getIdentifier("civil",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Geomática" || career == "Geomatics Engineering") {
            val resourceId = iv.context.resources.getIdentifier("geomatica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Ambiental" || career == "Environmental Engineering") {
            val resourceId = iv.context.resources.getIdentifier("ambiental",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Geofísica" || career == "Geophysical Engineering") {
            val resourceId = iv.context.resources.getIdentifier("geofisica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Petrolera" || career == "Petroleum Engineering") {
            val resourceId = iv.context.resources.getIdentifier("petrolera",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Geológica" || career == "Geological Engineering") {
            val resourceId = iv.context.resources.getIdentifier("geologica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería de Minas y Metalurgia" ||
            career == "Mining and Metallurgical Engineering") {
            val resourceId = iv.context.resources.getIdentifier("minas",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería en Computación" || career == "Computer Engineering") {
            val resourceId = iv.context.resources.getIdentifier("computacion",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Eléctrica Electrónica" ||
            career == "Electronic Electrical Engineering") {
            val resourceId = iv.context.resources.getIdentifier("electricaelectronica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería en Telecomunicaciones" ||
            career == "Telecommunications Engineering") {
            val resourceId = iv.context.resources.getIdentifier("telecomunicaciones",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Mecánica" || career == "Mechanical Engineering") {
            val resourceId = iv.context.resources.getIdentifier("mecanica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Industrial" || career == "Industrial Engineering") {
            val resourceId = iv.context.resources.getIdentifier("industrial",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else if (career == "Ingeniería Mecatrónica" || career == "Mechatronics Engineering") {
            val resourceId = iv.context.resources.getIdentifier("mecatronica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }

        else {
            val resourceId = iv.context.resources.getIdentifier("biomedica",
                "drawable", iv.context.packageName)
            binding.imageView.setImageResource(resourceId)
        }
    }

    fun calculateAge(year: String, month:String, day:String): Int {
        val db = "$year-$month-$day"
        val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) //Date Format
        val date = df.parse(db)
        val dateT = Calendar.getInstance().time //Today.
        val dif = dateT.time - date.time
        return (dif / (1000 * 60 * 60 * 24 * 365.25)).toInt()
    }

    fun zodiacSign(day:String, month:String): String {
        val d:Int = day.toInt()
        val m:Int = month.toInt()

        return when {
            (d >= 21 && m == 3)  || (d <= 20 && m == 4)  -> resources.getString(R.string.aries)
            (d >= 21 && m == 4)  || (d <= 20 && m == 5)  -> resources.getString(R.string.tauro)
            (d >= 21 && m == 5)  || (d <= 21 && m == 6)  -> resources.getString(R.string.geminis)
            (d >= 22 && m == 6)  || (d <= 22 && m == 7)  -> resources.getString(R.string.cancer)
            (d >= 23 && m == 7)  || (d <= 23 && m == 8)  -> resources.getString(R.string.leo)
            (d >= 24 && m == 8)  || (d <= 23 && m == 9)  -> resources.getString(R.string.virgo)
            (d >= 24 && m == 9)  || (d <= 23 && m == 10) -> resources.getString(R.string.libra)
            (d >= 24 && m == 10) || (d <= 22 && m == 11) -> resources.getString(R.string.escorpio)
            (d >= 23 && m == 11) || (d <= 21 && m == 12) -> resources.getString(R.string.sagitario)
            (d >= 22 && m == 12) || (d <= 20 && m == 1)  -> resources.getString(R.string.capricornio)
            (d >= 21 && m == 1)  || (d <= 19 && m == 2)  -> resources.getString(R.string.acuario)
            else -> resources.getString(R.string.piscis)
        }

    }

    fun chineseSign(year: String):String {
        return when((year.toInt() - 1900) % 12) {
            0 -> resources.getString(R.string.rat)
            1 -> resources.getString(R.string.ox)
            2 -> resources.getString(R.string.tiger)
            3 -> resources.getString(R.string.rabbit)
            4 -> resources.getString(R.string.dragon)
            5 -> resources.getString(R.string.snake)
            6 -> resources.getString(R.string.horse)
            7 -> resources.getString(R.string.goat)
            8 -> resources.getString(R.string.bun)
            9 -> resources.getString(R.string.rooster)
            10 -> resources.getString(R.string.dog)
            11 -> resources.getString(R.string.pig)
            else -> resources.getString(R.string.stranger)
        }
    }
}