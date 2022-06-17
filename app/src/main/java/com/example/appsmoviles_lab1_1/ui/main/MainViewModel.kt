package com.example.appsmoviles_lab1_1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
class MainViewModel: ViewModel() {

    private var borndate: MutableLiveData<String> = MutableLiveData()
    val borndateDone: LiveData<String> = borndate

    private var position = 0
    private var emptyFieldAt: MutableLiveData<Int> = MutableLiveData()
    val emptyFieldAtDone: LiveData<Int> = emptyFieldAt

    fun setBornDate(borndate_: String){
        borndate.value = borndate_
    }

    fun areEmptyFields(name_field: Boolean, lastname_field: Boolean,
                       email_field: Boolean,  pass_field: Boolean, confpass_field: Boolean){
        var fields = booleanArrayOf(name_field, lastname_field, email_field, pass_field, confpass_field)
        for (i in fields.indices) {
            Log.d("Verificando campo: ", i.toString() + fields.get(i).toString() )
            if (fields.get(i)) {
                position = i + 1
                Log.d("Campo vacío en: ", position.toString())
                emptyFieldAt.value = position
                break
            }
        }
    }
}