package com.atividade.calculadora.screens

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.parcelize.Parcelize
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

@Parcelize
data class Calculadora(
    val num1: Double,
    val num2: Double,
) : Parcelable {
    fun somar() : Double {
        return num1 + num2;
    }

    fun subtrair() : Double {
        return num1 - num2;
    }

    fun dividir() : Double {
        if(num1.equals(0.0) || num1.isNaN()){
            0
        }
        return num1 / num2;
    }

    fun mult() : Double {
        return num1 * num2;
    }
}

@Composable
fun CalculadoraScreen(modifier: Modifier = Modifier){
    var calculadora by rememberSaveable{
        mutableStateOf(Calculadora(1.0, 1.0))
    }
}

@Composable
fun CalculadoraContent(
    calculadora: Calculadora,
    onNumberChange:(Double) -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ){
        Text(
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            text = "Número 1: $calculadora.num1"
        )
        Text(
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
            text = "Número 2: $calculadora.num2"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        calculadora.somar()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                    ) {
                    Text("Somar")
                }
                Button(
                    onClick = {
                        calculadora.subtrair()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text("Subtraitr")
                }
            }
        }
        Column() {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        calculadora.dividir()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text("Dividir")
                }
                Button(
                    onClick = {
                        calculadora.mult()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text("Multiplicar")
                }
            }
        }
    }
}