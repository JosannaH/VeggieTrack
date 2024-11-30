package com.example.veggietrack

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun AddNewCategoryDialog(
   onSave: (String) -> Unit,
   onCancel: () -> Unit
) {
   var categoryName by remember { mutableStateOf(TextFieldValue("")) }
   
   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {
      
      OutlinedTextField(
         value = categoryName,
         onValueChange = { categoryName = it },
         label = { Text("Category Name") },
         singleLine = true,
         modifier = Modifier.fillMaxWidth()
      )
      
      Row(
         horizontalArrangement = Arrangement.spacedBy(16.dp)
      ) {
         Button(
            onClick = { onSave(categoryName.text) },
            enabled = categoryName.text.isNotBlank()
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
fun AddNewCategoryDialogPreview() {
   AddNewCategoryDialog(
      onSave = { categoryName -> println("Category saved: $categoryName") },
      onCancel = { println("Canceled") }
   )
}

