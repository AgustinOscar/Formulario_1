package com.computomovil.formulario1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

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

            Log.d("LOGTAG", age.toString())
            Log.d("LOGTAG", zs)
            Log.d("LOGTAG", cs)

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