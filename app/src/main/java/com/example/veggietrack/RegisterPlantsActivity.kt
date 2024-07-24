package com.example.veggietrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
   val vegetableOptions = listOf("Tomato", "Carrot", "Lettuce") // Example options
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
      Text("Register your plants", style = MaterialTheme.typography.headlineLarge)
      
      DropdownMenuWithTextField(
         label = "Type of vegetable",
         options = vegetableOptions,
         // Show first list item TODO might need to change when db i configured
         selectedOption = "hej", //vegetableOptions.get(0),
         onOptionSelected = { vegetableType = it }
      )
      
      DropdownMenuWithTextField(
         label = "Type of ${if (vegetableType.isNotEmpty()) vegetableType else "vegetable"}",
         options = specificOptions,
         // Show first list item TODO might need to change when db i configured
         selectedOption = "hej",
         onOptionSelected = { specificType = it }
      )
      
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

@Composable
fun DropdownMenuWithTextField(
   label: String,
   options: List<String>,
   selectedOption: String,
   onOptionSelected: (String) -> Unit,
) {
   var expanded by remember { mutableStateOf(false) }
   val currentText = if (selectedOption.isEmpty()) label else selectedOption

   Box{
      TextField(
         value = currentText,
         onValueChange = {},
         label = { Text(label) },
         readOnly = true,
         modifier = Modifier
            .fillMaxWidth()
            .clickable(
               interactionSource = remember { MutableInteractionSource() },
               indication = null
            ) { expanded = true }
      )
      DropdownMenu(
         expanded = expanded,
         onDismissRequest = { expanded = false }
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