package com.example.appsmoviles_lab1_1.ui.main

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appsmoviles_lab1_1.R
import com.example.appsmoviles_lab1_1.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val calendar  = Calendar.getInstance()
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.borndateDone.observe(this){
            mainBinding.borndDateButton.text = it
        }

        mainViewModel.isPassOkDone.observe(this){
            if (!it) Toast.makeText(this@MainActivity, "Las contraseñas son diferentes", Toast.LENGTH_SHORT).show()
        }

        mainViewModel.showStringDone.observe(this){
            mainBinding.textViewSavedInfo.text = it
        }


        mainViewModel.emptyFieldAtDone.observe(this){at ->
            when(at){
                1 -> Toast.makeText(this@MainActivity,"Digite su nombre", Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this@MainActivity, "Digite su apellido", Toast.LENGTH_SHORT).show()
                3 -> Toast.makeText(this@MainActivity, "Digite su e-mail", Toast.LENGTH_SHORT).show()
                4 -> Toast.makeText(this@MainActivity, "Digite su contraseña", Toast.LENGTH_SHORT).show()
                5 -> Toast.makeText(this@MainActivity, "Confirme la contraseña", Toast.LENGTH_SHORT).show()
            }
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val format = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(format)
            mainViewModel.setBornDate(sdf.format(calendar.time).toString())
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

                mainViewModel.areEmptyFields(
                    TextInputEditTextNombre.text.toString().isEmpty(),
                    TextInputEditTextApellido.text.toString().isEmpty(),
                    TextInputEditTextEmail.text.toString().isEmpty(),
                    TextInputEditTextPass.text.toString().isEmpty(),
                    TextInputEditTextConfirmpass.text.toString().isEmpty()
                )

                mainViewModel.storeFields(
                    TextInputEditTextNombre.text.toString(),
                    TextInputEditTextApellido.text.toString(),
                    TextInputEditTextEmail.text.toString(),
                    TextInputEditTextPass.text.toString(),
                    TextInputEditTextConfirmpass.text.toString(),
                    spinnerLugarnac.selectedItem.toString()
                )


                if(radioButtonMasculino.isChecked) mainViewModel.storeGenre(getString(R.string.string_masculino))
                else mainViewModel.storeGenre(getString(R.string.string_femenino))

                mainViewModel.isPassCorrect()

                if(checkBoxEjercicio.isChecked) mainViewModel.storeHobbies(getString(R.string.string_ejercicio))
                if(checkBoxLeer.isChecked) mainViewModel.storeHobbies(getString(R.string.string_leer))
                if(checkBoxEventos.isChecked) mainViewModel.storeHobbies(getString(R.string.string_eventos))
                if(checkBoxSenderismo.isChecked) mainViewModel.storeHobbies(getString(R.string.string_senderismo))

                mainViewModel.showInfo()
            }
        }
    }
};