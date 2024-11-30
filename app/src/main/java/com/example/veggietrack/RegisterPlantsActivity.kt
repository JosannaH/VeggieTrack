package com.example.veggietrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.veggietrack.ui.theme.VeggieTrackTheme

class RegisterPlantsActivity : ComponentActivity() {
   // TODO localize all strings
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
   
   
   var expandedCategory by remember { mutableStateOf(false) }
   var selectedCategory by remember { mutableStateOf("Select Category") }
   var expandedType by remember { mutableStateOf(false) }
   var selectedType by remember { mutableStateOf("Select Type") }
   var amount by remember { mutableStateOf("") }
   var showDialogCategory by remember { mutableStateOf(false) }
   var showDialogType by remember { mutableStateOf(false) }
   
   val listOfCategories = listOf("Tomato", "Carrot", "Lettuce")
   val listOfTypes = when (selectedCategory) {
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
         Box {
            Button(onClick = { expandedCategory = true }) {
               Text(text = selectedCategory)
               Icon(
                  imageVector = Icons.Default.ArrowDropDown,
                  contentDescription = "Dropdown Arrow"
               )
            }
            DropdownMenu(
               expanded = expandedCategory,
               onDismissRequest = { expandedCategory = false }) {
               
               listOfCategories.forEach { item ->
                  DropdownMenuItem(
                     text = { Text(text = item) },
                     onClick = {
                        selectedCategory = item
                        selectedType = "Select Type"
                        expandedCategory = false
                     })
               }
            }
         }
         IconButton(
            onClick = { showDialogCategory = true },
            content = { Icon(Icons.Default.Add, contentDescription = "Add") },
            modifier = Modifier.align(Alignment.CenterVertically)
         )
      }
      Row {
         Box {
            Button(onClick = { expandedType = true }) {
               Text(text = selectedType)
               Icon(
                  imageVector = Icons.Default.ArrowDropDown,
                  contentDescription = "Dropdown Arrow"
               )
            }
            DropdownMenu(
               expanded = expandedType,
               onDismissRequest = { expandedType = false }) {
               
               listOfTypes.forEach { item ->
                  DropdownMenuItem(
                     text = { Text(text = item) },
                     onClick = {
                        selectedType = item
                        expandedType = false
                     })
               }
            }
         }
         IconButton(
            onClick = { showDialogType = true },
            content = { Icon(Icons.Default.Add, contentDescription = "Add") },
            modifier = Modifier.align(Alignment.CenterVertically)
         )
      }
      OutlinedTextField(
         value = amount,
         onValueChange = { amount = it },
         label = { Text("Number of plants") },
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
      )
      Button(
         onClick = { /* TODO save registration */ },
         modifier = Modifier.align(Alignment.CenterHorizontally)
      ) {
         Text("Save")
      }
      
      if (showDialogCategory) {
         AlertDialog(
            onDismissRequest = { showDialogCategory = false },
            title = { Text("Add New Category") },
            text = {
               AddNewCategoryDialog(
                  onSave = { categoryName ->
                     println("Category saved: $categoryName")
                     showDialogCategory = false
                  },
                  onCancel = {
                     showDialogCategory = false
                  }
               )
            },
            dismissButton = {},
            confirmButton = {}
         )
      }
      
      if (showDialogType) {
         AlertDialog(
            onDismissRequest = { showDialogType = false },
            title = { Text("Add New Type") },
            text = {
               AddNewTypeDialog(
                  onSave = { typeName ->
                     println("Type saved: $typeName")
                     showDialogType = false
                  },
                  onCancel = {
                     showDialogType = false
                  }
               )
            },
            dismissButton = {},
            confirmButton = {}
         )
      }
   }
}






