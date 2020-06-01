package com.example.android_app_calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastNumeric : Boolean = false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        tv_input_number.append((view as Button).text)
        lastNumeric = true

//        if( tv_input_number.text.contains("1") ) {
//            tv_input_number.text = "txt"
//        }
    }

    fun onClear(view: View) {
        tv_input_number.text = ""
        lastNumeric = false
    }

    fun onDecimelPoint(view: View) {
        if(lastNumeric && !lastDot) {
            tv_input_number.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onRsult(view: View) {
        if(lastNumeric) {
            var tvValue = tv_input_number.text.toString()
            var prefix = ""

            try {

                if (tvValue.startsWith("-")) {
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tv_input_number.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tv_input_number.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tv_input_number.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    tv_input_number.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View) {
        if(lastNumeric && !AddedOperator(tv_input_number.text.toString())) {
            tv_input_number.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun AddedOperator(value: String) : Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

    private fun removeZeroAfterDot(result: String) : String {
        var value = result

        if(result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }
}
