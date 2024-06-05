package com.l0122006.afifimam.week11

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // Calculator
    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    private var currentInput = ""
    private var operator: Char? = null
    private var operand1: Double? = null

    fun onDigit(digit: Char) {
        currentInput += digit
        _result.value = currentInput
    }

    fun onOperator(op: Char) {
        operator = op
        operand1 = currentInput.toDoubleOrNull()
        currentInput = ""
    }

    fun onEquals() {
        val operand2 = currentInput.toDoubleOrNull()
        if (operand1 != null && operand2 != null && operator != null) {
            val resultValue = when (operator) {
                '+' -> operand1!! + operand2
                '-' -> operand1!! - operand2
                '*' -> operand1!! * operand2
                '/' -> operand1!! / operand2
                else -> 0.0
            }
            _result.value = resultValue.toString()
            currentInput = resultValue.toString()
            operand1 = null
            operator = null
        }
    }

    fun onClear() {
        currentInput = ""
        operator = null
        operand1 = null
        _result.value = "0"
    }

    // Timer
    private val _elapsedTime = MutableLiveData<Long>()
    val elapsedTime: LiveData<Long> get() = _elapsedTime

    private var isRunning = false
    private var startTime = 0L
    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 1000L

    private val runnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                val currentTime = System.currentTimeMillis()
                _elapsedTime.value = (currentTime - startTime) / 1000
                handler.postDelayed(this, updateInterval)
            }
        }
    }

    fun startTimer() {
        if (!isRunning) {
            isRunning = true
            startTime = System.currentTimeMillis()
            handler.post(runnable)
        }
    }

    fun stopTimer() {
        isRunning = false
        handler.removeCallbacks(runnable)
    }

    fun resetTimer() {
        stopTimer()
        _elapsedTime.value=0
    }
}