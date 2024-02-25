package com.example.tipcalculator

import android.util.Log

class NumberHelper {
    companion object {
        fun strToFloatStr(str: String, pasteErrorCallback: (() -> Unit)? = null): String {
            // Caso 1 Verificar se está vazio
            if (str.isBlank()) return ""

            // Caso 2 trocar ',' por '.'
            val nStr =
                if (str.contains(',')) str.replace(',', '.')
                else str

            // Caso 3 Verificar se tem mais de 1 ponto ou virgula
            var dotCount = 0
            for (char in nStr) {
                if (char == '.') dotCount++
            }
            Log.d("NumHelperTag", "strToFloat: $dotCount")
            // Final: retornando a String correta
            val lastChar = nStr.substring(nStr.length - 1, nStr.length)
            return if (dotCount >= 2 && lastChar != ".") {
                pasteErrorCallback?.invoke()
                ""
            } else if (dotCount == 2 && lastChar == ".") nStr.substring(0, nStr.length - 1)
            else
                nStr


//
//            val lastChar = it.subSequence(it.length-1, it.length)
//            Log.d("main_act_log", "TipCalculator: $isDecimal")
//            Log.d("main_act_log", "TipCalculator: $lastChar")
//            inputText = if (isDecimal && (lastChar == "." || lastChar == ",")) {
//                it.substring(0, it.length - 1)
//            } else {
//                it
//            }
//            isDecimal = it.contains(',') || it.contains('.')
//
//            totalBillFloat = when {
//                inputText.isBlank() -> 0F
//                inputText.contains(',') -> inputText.replace(',', '.').toFloat()
//                else -> inputText.toFloat()
//            }
//        }
        }
    }

}