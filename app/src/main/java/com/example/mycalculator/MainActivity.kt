package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy{ ActivityMainBinding.inflate(layoutInflater)}

    var lastNumeric = false
    var lastDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        //Toast.makeText(this,"Button clicked",Toast.LENGTH_LONG).show()
        binding.tvInput.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View){
        binding.tvInput.text = ""
        lastDot = false
        lastNumeric = false
    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastDot) {
            binding.tvInput.append(".")
            lastNumeric = false
        }
    }

        fun isOperatorAdded(value: String): Boolean {
            return if (value.startsWith("-")){
                false
            }
            else {
                value.contains("/") || value.contains("*")
                        || value.contains("-") || value.contains("+")
            }
        }

        fun onOperator(view: View){
            if(lastNumeric && !isOperatorAdded(binding.tvInput.text.toString()))
                binding.tvInput.append((view as Button).text)
            lastDot = false
            lastNumeric = false
        }

    fun onEqual(view: View){

        if(lastNumeric){
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                }

                else if (tvValue.contains("+")) {
                        val splitValue = tvValue.split("+")
                        var one = splitValue[0]
                        var two = splitValue[1]


                        if (!prefix.isEmpty()) {
                            one = prefix + one
                        }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    }

                else if (tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                }

                else if (tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")
                    var one = splitValue[0]
                    var two = splitValue[1]


                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    binding.tvInput.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }

            }
            catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }
    }

    fun removeZeroAfterDot(result: String): String {
        var value = result
        if(result.contains(".0"))
            value = result.substring(0,result.length-2)
        return value
    }

    }
