package com.example.veggietrack

import android.graphics.drawable.Icon
import android.inputmethodservice.Keyboard.Row
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.veggietrack.ui.theme.VeggieTrackTheme

class RegisterPlantsActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         VeggieTrackTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
               RegisterPlantsScreen()
            }
         }
      }
   }
}

@Preview
@Composable
fun RegisterPlantsScreen() {
   var vegetableType by remember { mutableStateOf("") }
   var specificType by remember { mutableStateOf("") }
   var amount by remember { mutableStateOf("") }
   
   val vegetableOptions = listOf("Tomato", "Carrot", "Lettuce")
   val specificOptions = when (vegetableType) {
      "Tomato" -> listOf("Cherry", "Beefsteak", "Heirloom")
      "Carrot" -> listOf("Nantes", "Imperator", "Chantenay")
      "Lettuce" -> listOf("Romaine", "Iceberg", "Butterhead")
      else -> emptyList()
   }
   
   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
   ) {
      Text(
         "Register your plants",
         style = MaterialTheme.typography.headlineLarge
      )
      
      Row {
         DropdownMenuWithTextField(
            label = "Type of vegetable",
            options = vegetableOptions,
            selectedOption = vegetableType,
            onOptionSelected = { vegetableType = it }
         )
         IconButton(
            onClick = { /*TODO*/ },
            content = { Icon(Icons.Default.Add, contentDescription = "Add") },
            modifier = Modifier.align(Alignment.CenterVertically)
         )
      }
      
      Row {
         // TODO selected option should be reset if you change the value in first dropdown
         DropdownMenuWithTextField(
            label = "${
               if (vegetableType.isNotEmpty()) "Type of " + vegetableType
               else "Specify " + "type"
            }",
            options = specificOptions,
            selectedOption = specificType,
            onOptionSelected = { specificType = it }
         )
         IconButton(
            onClick = { /*TODO*/ },
            content = { Icon(Icons.Default.Add, contentDescription = "Add") },
            modifier = Modifier
               .align(Alignment.CenterVertically)
         )
      }
      
      OutlinedTextField(
         value = amount,
         onValueChange = { amount = it },
         label = { Text("Amount") },
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
      )
      
      Button(
         onClick = { /* TODO save registration */ },
         modifier = Modifier.align(Alignment.CenterHorizontally)
      ) {
         Text("Save")
      }
   }
}

// TODO dropdown should work when you click somewhere in the textfield
@Composable
fun DropdownMenuWithTextField(
   label: String,
   options: List<String>,
   selectedOption: String,
   onOptionSelected: (String) -> Unit,
) {
   var expanded by remember { mutableStateOf(false) }
   val currentText = if (selectedOption.isEmpty()) label else selectedOption
   
   Box {
      OutlinedTextField(
         value = currentText,
         onValueChange = {},
         label = { Text(label) },
         readOnly = true,
         modifier = Modifier
            //.fillMaxWidth()
            .clickable(
               interactionSource = remember { MutableInteractionSource() },
               indication = null,
            ) { expanded = true }
      )
      DropdownMenu(
         expanded = expanded,
         onDismissRequest = { expanded = false },
      ) {
         options.forEach { option ->
            DropdownMenuItem(
               text = { Text(option) },
               onClick = {
                  onOptionSelected(option)
                  expanded = false
               }
            )
         }
      }
   }
}