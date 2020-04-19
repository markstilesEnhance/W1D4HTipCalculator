package com.example.w1d4htipcalculator

import android.os.Bundle
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {

    lateinit var cheque: Cheque

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cheque = Cheque(
            BigDecimal("0"),
            BigDecimal("15"),
            BigDecimal("0"),
            BigDecimal("0")
        )
        check_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                cheque.check=if(s.length>0) BigDecimal(s.toString()) else BigDecimal("0")
                updateText()
            }
        })
        rounding_switch.setOnCheckedChangeListener { buttonView, isChecked ->
            updateText()
        }
        down_button.setOnClickListener{
            cheque.tipPercent= BigDecimal("10")
            updateText()
        }
        mixed_button.setOnClickListener{
            cheque.tipPercent= BigDecimal("15")
            updateText()
        }
        up_button.setOnClickListener{
            cheque.tipPercent= BigDecimal("20")
            updateText()
        }
        pay_button.setOnClickListener {
            val intent = Intent(this,Main2Activity::class.java)
            intent.putExtra("payment", cheque)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("cheque", cheque)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState.containsKey("cheque")) {
            cheque = savedInstanceState.getParcelable("cheque")!!
            updateText()
        }
    }



    private fun updateText() {
        cheque.check=cheque.check.setScale(2, RoundingMode.CEILING)
        cheque.tipPercent=cheque.tipPercent.setScale(2, RoundingMode.CEILING)
        cheque.tipAmount=(cheque.tipPercent * cheque.check / 100.toBigDecimal())
            .setScale(2, RoundingMode.CEILING)
        if(rounding_switch.isChecked){
            cheque.total = (cheque.check + cheque.tipAmount).setScale(0, RoundingMode.CEILING)

        }
        else {
            cheque.total = (cheque.check + cheque.tipAmount).setScale(2, RoundingMode.CEILING)
        }
        tip_amount.text = cheque.tipAmount.toString()
        total_amount.text = cheque.total.toString()
        starting_tip.text = cheque.tipPercent.toString()+"%"
    }
}
