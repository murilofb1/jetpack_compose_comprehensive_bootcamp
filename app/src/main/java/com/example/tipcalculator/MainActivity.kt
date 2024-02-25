package com.example.tipcalculator

import android.os.Bundle

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import com.example.tipcalculator.ui.theme.cardColor
import com.example.tipcalculator.ui.theme.headerTile
import com.example.tipcalculator.ui.theme.headerValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme(darkTheme = true) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val totalPerPerson = remember { mutableFloatStateOf(0F) }
                    App(totalPerPerson)
                }
            }
        }
    }
}

@Composable
fun App(totalPerPerson: MutableFloatState) {
    Column(
        Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        TotalCard(totalPerPerson)
        Divider(
            modifier = Modifier.padding(10.dp),
            color = Color.Transparent
        )
        TipCalculator(totalPerPerson)
    }
}

@Composable
fun TotalCard(totalValue: MutableFloatState) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.total_por_pessoa),
                style = headerTile
            )
            Text(
                text = CurrencyFormatter.toBRL(totalValue.floatValue),
                style = headerValue
            )
        }

    }
}

@Composable
fun TipCalculator(
    totalPerPerson: MutableFloatState = mutableFloatStateOf(0F),
    isCollapsed: Boolean = true
) {
    var splitCount by remember { mutableIntStateOf(1) }
    var tipPercent by remember { mutableFloatStateOf(0f) }
    var totalBillFloat by remember { mutableFloatStateOf(0F) }
    var tipValue by remember { mutableFloatStateOf(0f) }
    val context = LocalContext.current

    fun resetValues() {
        splitCount = 1
        tipPercent = 0f
        tipValue = 0f
    }

    fun calculateTip() {
        totalPerPerson.floatValue =
            (totalBillFloat + (totalBillFloat * (tipPercent / 100))) / splitCount
    }

    fun updateTip() {
        tipValue = totalBillFloat * (tipPercent / 100)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(0.2.dp, Color.Gray)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            var inputText by remember { mutableStateOf("") }
            var isFieldEmpty by remember { mutableStateOf(isCollapsed) }

            TextField(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                value = inputText,
                onValueChange = {
                    val floatStr = NumberHelper.strToFloatStr(it) {
                        Toast.makeText(
                            context,
                            "The number has too many points",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    inputText = floatStr
                    totalBillFloat = if (floatStr.isBlank()) 0f else floatStr.toFloat()
                    isFieldEmpty = inputText.isBlank()
                    calculateTip()
                    updateTip()
                }, placeholder = {
                    Text(text = "Insira o valor da conta")
                },
                prefix = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_money),
                        contentDescription = "Money Icon"
                    )
                }
            )

            if (isFieldEmpty) resetValues()
            else {
                //FirstRow
                SplitCalculator(
                    counterValue = splitCount,
                    onPlusClick = {
                        splitCount++
                        calculateTip()
                    }, onMinusClick = {
                        if (splitCount > 1) {
                            splitCount--
                            calculateTip()
                        }
                    }, onMinusLongPress = {
                        if (splitCount > 1) {
                            splitCount--
                            calculateTip()
                        }
                    }
                )
//Second Row
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth()
                        .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Tip")
                    Text(
                        modifier = Modifier.width(150.dp),
                        text = CurrencyFormatter.toBRL(tipValue),
                        textAlign = TextAlign.Center
                    )
                }
                //Third Row
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "%.2f".format(tipPercent) + "%",
                        textAlign = TextAlign.Center
                    )
                }
                Slider(
                    value = tipPercent,
                    onValueChange = {
                        tipPercent = it
                        updateTip()
                        calculateTip()
                    },
                    valueRange = 0f..100f,
                    steps = 9
                )
            }
        }
    }
}

@Composable
fun SplitCalculator(
    modifier: Modifier = Modifier,
    counterValue: Int,
    onMinusClick: () -> Unit,
    onMinusLongPress: () -> Unit,
    onPlusClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Split")
        Row(
            modifier = modifier
                .width(150.dp),
            horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleButton(
                painter = painterResource(id = R.drawable.ic_minus),
                onClick = onMinusClick,
            )
            Text(text = counterValue.toString())
            CircleButton(
                onClick = onPlusClick,
            )
        }
    }
}


@Preview
@Composable
fun TipCalculatorPreview() {
    TipCalculator(isCollapsed = false)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val totalValue = remember { mutableFloatStateOf(0F) }
    TipCalculatorTheme {
        App(totalValue)
    }
}