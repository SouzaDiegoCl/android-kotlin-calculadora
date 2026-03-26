package com.atividade.calculadora.screens

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.atividade.calculadora.ui.theme.Black100
import com.atividade.calculadora.ui.theme.Black80
import com.atividade.calculadora.ui.theme.Gray100
import com.atividade.calculadora.ui.theme.White100
import com.atividade.calculadora.ui.theme.Yellow100
import kotlinx.parcelize.Parcelize

@Parcelize
data class Calculadora(
    val num1: Double,
    val num2: Double,
) : Parcelable {
    fun somar(): Double {
        return num1 + num2;
    }

    fun subtrair(): Double {
        return num1 - num2;
    }

    fun dividir(): Double {
        if (num1.equals(0.0) || num1.isNaN()) {
            0
        }
        return num1 / num2;
    }

    fun mult(): Double {
        return num1 * num2;
    }
}

@Composable
fun CalculadoraScreen(modifier: Modifier = Modifier) {
    var calculadora by rememberSaveable {
        mutableStateOf(Calculadora(1.0, 1.0))
    }
    var resultado by rememberSaveable {
        mutableStateOf(calculadora.num1 + calculadora.num2);
    }
    CalculadoraContent(
        calculadora = calculadora,
        resultado = resultado,
        onNumberChange = { newValue ->
            calculadora = calculadora.copy(num1 = newValue)
        },
        onResultadoChange = { newValue ->
            resultado = newValue
        },
        modifier = modifier
    );
}

@Composable
fun CalculadoraContent(
    calculadora: Calculadora,
    resultado: Double,
    onNumberChange: (Double) -> Unit,
    onResultadoChange: (Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = 8.dp

    //Coluna principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp)
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        //Visor contendo os números
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
        ) {
            Text(
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                text = "${calculadora.num1}"
            )
            Text(
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                text = "+"
            )
            Text(
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                text = "${calculadora.num2}"
            )
            Text(
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
                text = "= $resultado"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "AC",
                    onClick = {},
                    containerColor = Gray100,
                    contentColor = Black100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "+/-",
                    onClick = {},
                    containerColor = Gray100,
                    contentColor = Black100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "%",
                    onClick = {},
                    containerColor = Gray100,
                    contentColor = Black100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "÷",
                    onClick = {},
                    containerColor = Yellow100,
                    contentColor = White100,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "7",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "8",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "9",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "X",
                    onClick = {},
                    containerColor = Yellow100,
                    contentColor = White100,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "4",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "5",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "6",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "-",
                    onClick = {},
                    containerColor = Yellow100,
                    contentColor = White100,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "1",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "2",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "3",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "+",
                    onClick = {},
                    containerColor = Yellow100,
                    contentColor = White100,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {},
                    modifier = modifier
                        .weight(2f)
                        .padding(6.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Black80,
                        contentColor = White100
                    )
                ) {
                    Text(
                        text = "0",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
                BotaoCalculadora(
                    Modifier.weight(1f),
                    ",",
                    onClick = {},
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "=",
                    onClick = {},
                    containerColor = Yellow100,
                    contentColor = White100,
                )
            }

        }
    }
}

@Composable
fun BotaoCalculadora(
    modifier: Modifier = Modifier,
    texto: String,
    onClick: () -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .padding(6.dp),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = texto,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}