package com.example.tipcalculator.helpers

import java.text.NumberFormat
import java.util.Currency

abstract class CurrencyFormatter {
    companion object {
        fun toBRL(value: Float): String {
            val format = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 2
            format.currency = Currency.getInstance("BRL")
            return format.format(value)
        }
    }
}