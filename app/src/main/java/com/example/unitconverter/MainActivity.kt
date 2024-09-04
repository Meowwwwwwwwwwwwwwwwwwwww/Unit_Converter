package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                      UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){

     var inputValue by remember { mutableStateOf("") }
     var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("meters") }
    var outputUnit by remember { mutableStateOf("meters") }
     var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
     val oConversionFactor = remember { mutableStateOf(1.0) }
    fun convertUnits(){
      val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
      val result =(inputValueDouble *conversionFactor.value *100/oConversionFactor.value).roundToInt()/100.0
        outputValue= result.toString()
    }

    Column(modifier=Modifier.fillMaxSize(),
           verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
         Text(text = "Unit Converter",
             style = MaterialTheme.typography.headlineLarge)
         Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value =  inputValue, onValueChange ={
            inputValue= it
            convertUnits()
        })
        Row {
            Box {
                Button(onClick = { iExpanded=true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription =" " )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "centimeters") }, onClick = {
                        iExpanded=false
                        inputUnit="centimeters"
                        conversionFactor.value=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded=false
                        inputUnit="meters"
                        conversionFactor.value=1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "millimeters") }, onClick = {
                        iExpanded=false
                        inputUnit="centimeters"
                        conversionFactor.value=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "inches") }, onClick = {
                        iExpanded=false
                        inputUnit="centimeters"
                        conversionFactor.value=0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "feet") }, onClick = {
                        iExpanded=false
                        inputUnit="centimeters"
                        conversionFactor.value=0.01
                        convertUnits()
                    })
                }
            }
            Box{
                Button(onClick = { oExpanded=true }) {
                    Text(text=outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = " ")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(text = { Text(text = "centimeters") }, onClick = {
                        oExpanded=false
                        outputUnit="centimeters"
                        oConversionFactor.value=0.01

                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded=false
                        outputUnit="meters"
                        oConversionFactor.value=1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "millimeters") }, onClick = {
                        oExpanded=false
                       outputUnit="millimeters"
                        oConversionFactor.value=0.001
                    })
                    DropdownMenuItem(text = { Text(text = "inches") }, onClick = {
                        oExpanded=false
                        outputUnit="inches"
                        oConversionFactor.value=0.0254

                    })
                    DropdownMenuItem(text = { Text(text = "feet") }, onClick = {  oExpanded=false
                        outputUnit="false"
                        oConversionFactor.value=0.3048
                        oExpanded=false

                    })
                }
            }
            }
        Spacer(Modifier.height(16.dp))
            Text("results: $outputValue $outputUnit",
            style=MaterialTheme.typography.headlineMedium
            )

        }
    }



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterTheme {
        UnitConverter()
        
    }
}