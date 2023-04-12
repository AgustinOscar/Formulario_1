package com.computomovil.formulario1

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment (val listener:(year:Int, month:Int, day:Int) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year =  c.get(Calendar.YEAR)
        val month =  c.get(Calendar.MONTH)
        val day =  c.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(requireActivity(), this, year, month, day)
        picker.datePicker.maxDate = c.timeInMillis
        return picker
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        listener(year, month+1, day)
    }
}