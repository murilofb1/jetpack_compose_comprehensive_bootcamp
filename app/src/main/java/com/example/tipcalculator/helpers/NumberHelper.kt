package com.example.tipcalculator.helpers

class NumberHelper {
    companion object {
        fun canConvertToFloat(str: String, errorCallback: (() -> Unit)? = null): Boolean {
            var isFloat = true
            try {
                str.toFloat()
            } catch (e: Exception) {
                errorCallback?.invoke()
                isFloat = false
            }
            return isFloat
        }
    }

}