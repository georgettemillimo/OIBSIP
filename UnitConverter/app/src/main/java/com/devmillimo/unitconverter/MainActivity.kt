package com.devmillimo.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devmillimo.unitconverter.ui.theme.UnitConverterTheme
import com.devmillimo.unitconverter.ui.theme.main_screen.MainScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ){
                    MainScreen()
                    UnitDropdown()
                }

            }
        }
    }
}
data class ConversionState(var value: String = "", var selectedUnit: String = "Kilograms")

@Composable
fun UnitDropdown() {
    var conversionState by remember { mutableStateOf(ConversionState()) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Value Input
        BasicTextField(
            value = conversionState.value,
            onValueChange = {
                conversionState.value = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = androidx.compose.ui.text.input.ImeAction.Done
            ),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown
        OutlinedTextField(
            value = conversionState.selectedUnit,
            onValueChange = {},
            label = { Text("Select Unit") },
            leadingIcon = { Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null) },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expanded = true
                }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            UnitType.values().forEach { unit ->
                DropdownMenuItem(onClick = {
                    conversionState.selectedUnit = unit.displayName
                    expanded = false
                }) {
                    Text(unit.displayName)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Convert Button
        Button(
            onClick = { /* Perform conversion if needed */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Convert", fontSize = 18.sp)
            }
        }
    }
}
enum class UnitType(val displayName: String) {
    KILOGRAMS("Kilograms"),
    TONNES("Tonnes"),
    GRAMS("Grams"),
    POUNDS("Pounds")
}