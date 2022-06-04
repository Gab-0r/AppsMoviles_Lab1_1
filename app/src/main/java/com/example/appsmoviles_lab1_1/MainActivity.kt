package com.example.appsmoviles_lab1_1

import android.app.Activity
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.appsmoviles_lab1_1.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : Activity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var borndate = ""
    private val calendar  = Calendar.getInstance()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(format)
            borndate = sdf.format(calendar.time).toString()
            mainBinding.borndDateButton.text = borndate
        }

        with(mainBinding){
            borndDateButton.setOnClickListener{
                DatePickerDialog(
                    this@MainActivity,
                    dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            registerButton.setOnClickListener{
                val nombre_ = TextInputEditTextNombre.text.toString()
                val apellido_ = TextInputEditTextApellido.text.toString()
                val email_ = TextInputEditTextEmail.text.toString()
                val pass_ = TextInputEditTextPass.text.toString()
                val confpass_ = TextInputEditTextConfirmpass.text.toString()
                val genero_ = if(radioButtonMasculino.isChecked) getString(R.string.string_masculino)
                else getString(R.string.string_femenino)

                var hobbies_ = ""
                if(checkBoxEjercicio.isChecked) hobbies_ += getString(R.string.string_ejercicio) + " "
                if(checkBoxLeer.isChecked) hobbies_ += getString(R.string.string_leer) + " "
                if(checkBoxEventos.isChecked) hobbies_ += getString(R.string.string_eventos) + " "
                if(checkBoxSenderismo.isChecked) hobbies_ += getString(R.string.string_senderismo) + " "

                val ciudadnac_ = spinnerLugarnac.selectedItem.toString()
                textViewSavedInfo.text = getString(R.string.info, nombre_, apellido_,email_, pass_, confpass_, genero_, hobbies_, ciudadnac_, borndate)
            }
        }
    }
};