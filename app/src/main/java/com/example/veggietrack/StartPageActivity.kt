package com.example.veggietrack

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.veggietrack.ui.theme.VeggieTrackTheme

class StartPageActivity : AppCompatActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         VeggieTrackTheme {
            Surface(color = MaterialTheme.colorScheme.background) {
               StartPageScreen()
            }
         }
      }
   }
}


@Composable
fun StartPageScreen()
{
   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
   ) {
      Text(
         "HarvestTracker",
         style = MaterialTheme.typography.headlineLarge
      )

      Button(onClick = { }) {
         Text(text = "Register Plants")
      }

      Button(onClick = {  }) {
         Text(text = "Register Harvest")
      }
   }


}