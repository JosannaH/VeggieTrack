package com.example.veggietrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddNewTypeDialog(
   onSave: (String) -> Unit,
   onCancel: () -> Unit
) {
   var typeName by remember { mutableStateOf(TextFieldValue("")) }
   var expandedCategory by remember { mutableStateOf(false) }
   val listOfCategories = listOf("Tomato", "Carrot", "Lettuce")
   var selectedCategory by remember { mutableStateOf("Select Category") }
   
   
   
   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      
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
                        expandedCategory = false
                     })
               }
            }
         }
      }
      
      OutlinedTextField(
         value = typeName,
         onValueChange = { typeName = it },
         label = { Text("Type Name") },
         singleLine = true,
         modifier = Modifier.fillMaxWidth()
      )
      
      Row(
         horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {
         Button(
            onClick = { onSave(typeName.text) },
            enabled = typeName.text.isNotBlank()
         ) {
            Text("Save")
         }
         
         OutlinedButton(
            onClick = onCancel
         ) {
            Text("Cancel")
         }
      }
   }
}

@Preview(showBackground = true)
@Composable
fun AddNewTypeDialogPreview() {
   AddNewTypeDialog(
      onSave = { categoryName -> println("Category saved: $categoryName") },
      onCancel = { println("Canceled") }
   )
}