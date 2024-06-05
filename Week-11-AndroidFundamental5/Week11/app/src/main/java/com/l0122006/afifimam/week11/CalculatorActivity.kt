package com.l0122006.afifimam.week11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.l0122006.afifimam.week11.databinding.ActivityCalculatorBinding

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.result.observe(this, Observer { result ->
            binding.tvResult.text = result
        })

        setButtonListeners()
    }

    private fun setButtonListeners() {
        binding.btn0.setOnClickListener { viewModel.onDigit('0') }
        binding.btn1.setOnClickListener { viewModel.onDigit('1') }
        binding.btn2.setOnClickListener { viewModel.onDigit('2') }
        binding.btn3.setOnClickListener { viewModel.onDigit('3') }
        binding.btn4.setOnClickListener { viewModel.onDigit('4') }
        binding.btn5.setOnClickListener { viewModel.onDigit('5') }
        binding.btn6.setOnClickListener { viewModel.onDigit('6') }
        binding.btn7.setOnClickListener { viewModel.onDigit('7') }
        binding.btn8.setOnClickListener { viewModel.onDigit('8') }
        binding.btn9.setOnClickListener { viewModel.onDigit('9') }

        binding.btnAdd.setOnClickListener { viewModel.onOperator('+') }
        binding.btnSubtract.setOnClickListener { viewModel.onOperator('-') }
        binding.btnMultiply.setOnClickListener { viewModel.onOperator('*') }
        binding.btnDivide.setOnClickListener { viewModel.onOperator('/') }

        binding.btnEquals.setOnClickListener { viewModel.onEquals() }
        binding.btnClear.setOnClickListener { viewModel.onClear() }
    }
}