package com.example.w1d4htipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val incoming =intent.getParcelableExtra<Cheque>("payment")
        pay_total_amount.text = incoming.total.toString()
    }
}
