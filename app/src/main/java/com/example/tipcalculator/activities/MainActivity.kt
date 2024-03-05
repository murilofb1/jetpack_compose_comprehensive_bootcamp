package com.example.tipcalculator.activities

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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipcalculator.R
import com.example.tipcalculator.components.BodyText
import com.example.tipcalculator.components.CircleButton
import com.example.tipcalculator.components.CardHeader
import com.example.tipcalculator.helpers.CurrencyFormatter
import com.example.tipcalculator.helpers.NumberHelper
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.collectAsState
import com.example.tipcalculator.components.OutlinedTextDecimal
import com.example.tipcalculator.viewmodels.TipCalculatorViewModel

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<TipCalculatorViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            TipCalculatorTheme(darkTheme = true) {
                MyApp {
                    App(viewModel)
                }
            }
        }
    }
}

@Composable
fun App(viewModel: TipCalculatorViewModel) {
    val totalPerPerson by viewModel.totalPerPerson.collectAsState()
    Column(
        Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        CardHeader(
            titleText = stringResource(R.string.total_por_pessoa),
            valueText = CurrencyFormatter.toBRL(totalPerPerson),
        )
        Spacer(modifier = Modifier.height(10.dp))
        TipCalculator(viewModel)
    }


}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        content()
    }
}

@Composable
fun TipCalculator(
    viewModel: TipCalculatorViewModel,
    isCollapsed: Boolean? = null
) {
    val context = LocalContext.current
    val tipValue by viewModel.tipValue.collectAsState()
    val splitCount by viewModel.splitCount.collectAsState()
    val tipPercent by viewModel.tipPercent.collectAsState()
    val billStr by viewModel.billStr.collectAsState()
    val emptyBill by viewModel.emptyBill.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize { _, _ -> },
        border = BorderStroke(0.2.dp, Color.Gray)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            OutlinedTextDecimal(
                text = billStr,
                hint = "Insira o valor da conta",
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_money),
                        contentDescription = "Money Icon"
                    )
                },
                onValueChange = {
                    when {
                        it.isBlank() -> viewModel.resetValues()
                        NumberHelper.canConvertToFloat(it) {
                            Toast.makeText(
                                context,
                                "Numero com formatação incorreta",
                                Toast.LENGTH_SHORT
                            ).show()
                        } -> viewModel.updateBillStr(it)

                    }

                })

            if (isCollapsed ?: emptyBill) viewModel.resetValues()
            else {
                SplitCalculator(
                    counterValue = splitCount,
                    onPlusClick = { viewModel.increasePersons() },
                    onMinusClick = { viewModel.decreasePersons() },
                )
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth()
                        .height(30.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    BodyText(text = "Tip")
                    BodyText(
                        modifier = Modifier.width(150.dp),
                        text = CurrencyFormatter.toBRL(tipValue),
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    contentAlignment = Alignment.Center
                ) {
                    BodyText(text = "%.2f".format(tipPercent) + "%")
                }
                Slider(
                    value = tipPercent,
                    onValueChange = {
                        viewModel.setTipPercent(it)
                        viewModel.calculateTip()
                    },
                    valueRange = 0f..100f,
                    steps = 99
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
    onPlusClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BodyText(text = "Split")
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
    TipCalculator(viewModel = TipCalculatorViewModel(), isCollapsed = false)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val vm = TipCalculatorViewModel()
    vm.setSplitCount(2)
    vm.setTipPercent(40f)
    vm.updateBillStr("100")
    TipCalculatorTheme {
        MyApp {
            App(viewModel = vm)
        }
    }
}