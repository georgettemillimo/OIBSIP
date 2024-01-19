package com.devmillimo.calculatorapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {

    private  val _firstnumber: MutableStateFlow<Double?> = MutableStateFlow(null)
    val firstNumber = _firstnumber.asStateFlow()

    private  val _secondNumber: MutableStateFlow<Double?> = MutableStateFlow(null)
    val secondNumber = _secondNumber.asStateFlow()

    private val _action:MutableStateFlow<String> = MutableStateFlow("")
    val action = _action.asStateFlow()

    fun setFirstNumber(input:Double){
        _firstnumber.update { input }
    }
    fun setsecondNumber(input:Double){
        _secondNumber.update { input }
    }

    fun setAction(action:String){
        _action.update { action }
    }

    fun resetAll(){
        _action.update { "" }
        _firstnumber.update { null }
        _secondNumber.update { null }
    }

    fun getResult(): Double{
        return  when(_action.value){
            "-" ->{
                _firstnumber.value!! - secondNumber.value!!
            }
            "+" ->{
                _firstnumber.value!! + secondNumber.value!!
            }
            "÷" ->{
                _firstnumber.value!! / secondNumber.value!!
            }
            "x" ->{
                _firstnumber.value!! * secondNumber.value!!
            }
            else->{
                0.0
            }
        }
    }

}