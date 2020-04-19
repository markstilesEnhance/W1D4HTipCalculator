package com.example.w1d4htipcalculator

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Cheque(
    var check:BigDecimal,
    var tipPercent:BigDecimal,
    var tipAmount:BigDecimal,
    var total:BigDecimal
):Parcelable