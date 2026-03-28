package com.atividade.calculadora.screens

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.atividade.calculadora.ui.theme.Black100
import com.atividade.calculadora.ui.theme.Black80
import com.atividade.calculadora.ui.theme.Gray100
import com.atividade.calculadora.ui.theme.Red100
import com.atividade.calculadora.ui.theme.White100
import com.atividade.calculadora.ui.theme.Yellow100
import kotlinx.parcelize.Parcelize
import java.util.Locale

@Parcelize
data class Calculadora(
    val num1: Double,
    val num2: Double,
    var mathSymbol: String,
) : Parcelable {
    fun somar(): Double {
        this.mathSymbol = "+";
        return num1 + num2;
    }

    fun subtrair(): Double {
        this.mathSymbol = "-";
        return num1 - num2;
    }

    fun dividir(): Double {
        this.mathSymbol = "÷";
        if (num1.equals(0.0) || num1.isNaN()) {
            0
        }
        return num1 / num2;
    }

    fun multiplicar(): Double {
        this.mathSymbol = "x";
        return num1 * num2;
    }
}

@Composable
fun CalculadoraScreen(modifier: Modifier = Modifier) {
    var calculadora by rememberSaveable {
        mutableStateOf(Calculadora(1.0, 1.0, "+"))
    }
    var resultado by rememberSaveable {
        mutableStateOf(calculadora.num1 + calculadora.num2);
    }
    var campoSelecionado by rememberSaveable { mutableStateOf(1) }
    var mostrarAviso by rememberSaveable { mutableStateOf(false) }

    val validarQtdCaracteres: (Double) -> Boolean = { digito ->
        if (campoSelecionado == 1) {
            val novoValor = calculadora.num1 * 10 + digito
            if (!(novoValor < 100000000)) {
                campoSelecionado = 2
            }
            novoValor < 100000000
        } else {
            val novoValor = calculadora.num2 * 10 + digito
            if (!(novoValor < 100000000)) {
                campoSelecionado = 1
            }
            novoValor < 100000000
        }
    }

    val atualizarNumero: (Double) -> Unit = { digito ->
        if (validarQtdCaracteres(digito)) {
            if (campoSelecionado == 1) {
                calculadora = calculadora.copy(num1 = calculadora.num1 * 10 + digito)
            } else {
                calculadora = calculadora.copy(num2 = calculadora.num2 * 10 + digito)
            }
        }
    }

    if (mostrarAviso) {
        AlertDialog(
            onDismissRequest = { mostrarAviso = false },
            confirmButton = {
                TextButton (onClick = { mostrarAviso = false }) {
                    Text("Entendi")
                }
            },
            title = { Text("Recurso Indisponível") },
            text = { Text("Esta operação ainda não foi implementada nesta versão da calculadora.") }
        )
    }

    CalculadoraContent(
        calculadora = calculadora,
        resultado = resultado,
        campoSelecionado = campoSelecionado,
        onSelectCampo = { campoSelecionado = it },
        onDigitoClick = atualizarNumero,
        onResultadoChange = { newValue -> resultado = newValue },
        onLimpar = {
            calculadora = Calculadora(0.0, 0.0, "+")
            resultado = 0.0
        },
        modifier = modifier,
        onBotaoDesativadoClick = { mostrarAviso = true },
    );
}

@Composable
fun CalculadoraContent(
    calculadora: Calculadora,
    resultado: Double,
    campoSelecionado: Int,
    onSelectCampo: (Int) -> Unit,
    onDigitoClick: (Double) -> Unit,
    onResultadoChange: (Double) -> Unit,
    onLimpar: () -> Unit,
    modifier: Modifier = Modifier,
    onBotaoDesativadoClick: () -> Unit,
) {
    val spacing = 8.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp)
            .navigationBarsPadding()
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
        ) {
            Text(
                text = String.format(Locale.getDefault(), "%.0f", calculadora.num1),
                style = MaterialTheme.typography.headlineLarge,
                textDecoration = TextDecoration.Underline,
                color = if (campoSelecionado == 1) Color.Yellow else Color.White,
                modifier = Modifier.clickable { onSelectCampo(1) }
            )
            Text(
                text = calculadora.mathSymbol,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
            )
            Text(
                text = String.format(Locale.getDefault(), "%.0f", calculadora.num2),
                style = MaterialTheme.typography.headlineLarge,
                textDecoration = TextDecoration.Underline,
                color = if (campoSelecionado == 2) Color.Yellow else Color.White,
                modifier = Modifier.clickable { onSelectCampo(2) }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                12.dp, Alignment.End
            )
        ) {
            Text(
                text = String.format(Locale.getDefault(), "%.0f", resultado),
                style = MaterialTheme.typography.headlineLarge,
                color = Color.White,
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
                    onClick = { onLimpar() },
                    containerColor = Gray100,
                    contentColor = Black100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "+/-",
                    onClick = { onBotaoDesativadoClick() },
                    containerColor = Gray100,
                    contentColor = Red100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "%",
                    onClick = { onBotaoDesativadoClick() },
                    containerColor = Gray100,
                    contentColor = Red100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "÷",
                    onClick = { onResultadoChange(calculadora.dividir()) },
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
                    onClick = { onDigitoClick(7.0) },
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "8",
                    onClick = { onDigitoClick(8.0) },
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "9",
                    onClick = { onDigitoClick(9.0) },
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "X",
                    onClick = { onResultadoChange(calculadora.multiplicar()) },
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
                    onClick = { onDigitoClick(4.0) },
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "5",
                    onClick = { onDigitoClick(5.0) },
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "6",
                    onClick = { onDigitoClick(6.0) },
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "-",
                    onClick = { onResultadoChange(calculadora.subtrair()) },
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
                    onClick = { onDigitoClick(1.0) },
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "2",
                    onClick = { onDigitoClick(2.0) },
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "3",
                    onClick = { onDigitoClick(3.0) },
                    containerColor = Black80,
                    contentColor = White100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "+",
                    onClick = { onResultadoChange(calculadora.somar()) },
                    containerColor = Yellow100,
                    contentColor = White100,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { onDigitoClick(0.0) },
                    modifier = modifier
                        .weight(2f)
                        .aspectRatio(2f)
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
                    onClick = { onBotaoDesativadoClick() },
                    containerColor = Black80,
                    contentColor = Red100,
                )
                BotaoCalculadora(
                    Modifier.weight(1f),
                    "=",
                    onClick = { onBotaoDesativadoClick() },
                    containerColor = Yellow100,
                    contentColor = Red100,
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