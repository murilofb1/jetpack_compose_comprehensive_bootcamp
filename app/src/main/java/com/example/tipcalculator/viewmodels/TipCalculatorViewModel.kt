package com.example.tipcalculator.viewmodels


import androidx.lifecycle.ViewModel
import com.example.tipcalculator.helpers.NumberHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TipCalculatorViewModel : ViewModel() {
    private var totalBill = 0F

    private val _emptyBill = MutableStateFlow(true)
    val emptyBill = _emptyBill.asStateFlow()

    private val _billStr = MutableStateFlow("")
    val billStr = _billStr.asStateFlow()

    private var _totalPerPerson = MutableStateFlow(0F)
    val totalPerPerson = _totalPerPerson.asStateFlow()

    private var _tipPercent = MutableStateFlow(0F)
    val tipPercent = _tipPercent.asStateFlow()

    private var _splitCount = MutableStateFlow(1)
    val splitCount = _splitCount.asStateFlow()

    private var _tipValue = MutableStateFlow(0F)
    val tipValue = _tipValue.asStateFlow()

    fun updateBillStr(value: String) {
        _billStr.value = value
        setTotalBill(_billStr.value)
        calculateTip()
        isBillEmpty()
    }

    private fun isBillEmpty(value: Boolean? = null) {
        _emptyBill.value = value ?: _billStr.value.isBlank()
    }

    fun setSplitCount(value: Int) {
        _splitCount.value = value
    }

    fun calculateTip() {
        _totalPerPerson.value = (totalBill + _tipValue.value) / _splitCount.value
    }

    private fun updateTipValue() {
        _tipValue.value = totalBill * (_tipPercent.value / 100)
    }

    private fun setTotalBill(billStr: String) {
        totalBill = if (billStr.isBlank()) 0f else billStr.toFloat()
        updateTipValue()
    }

    fun resetValues() {
        _billStr.value = ""
        totalBill = 0f
        _totalPerPerson.value = 0f
        _splitCount.value = 1
        _tipPercent.value = 0f
        _tipValue.value = 0f
        isBillEmpty(true)
    }

    fun increasePersons() {
        _splitCount.value++
        calculateTip()
    }

    fun decreasePersons() {
        if (_splitCount.value > 1) {
            _splitCount.value--
            calculateTip()
        }
    }

    fun setTipPercent(value: Float) {
        _tipPercent.value = value
        updateTipValue()
    }
}