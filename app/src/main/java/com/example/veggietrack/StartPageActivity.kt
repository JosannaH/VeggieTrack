package com.example.veggietrack

import android.content.Intent
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
               StartPageScreen(
                  onNavigateToRegisterPlants = {
                     startActivity(Intent(this, RegisterPlantsActivity::class.java))
                  },
                  onNavigateToRegisterHarvest = {
                     startActivity(Intent(this, RegisterPlantsActivity::class.java))
                  },
                  onNavigateToStatistics = {
                     startActivity(Intent(this, StatisticsActivity::class.java))
                     
                  }
               )
            }
         }
      }
   }
}


@Composable
fun StartPageScreen(
   onNavigateToRegisterPlants: () -> Unit,
   onNavigateToRegisterHarvest: () -> Unit,
   onNavigateToStatistics: () -> Unit
)
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

      Button(onClick = onNavigateToRegisterPlants) {
         Text(text = "Register Plants")
      }

      Button(onClick = onNavigateToRegisterHarvest) {
         Text(text = "Register Harvest")
      }
      
      Button(onClick = onNavigateToStatistics) {
         Text(text = "Statistics")
      }
   }


}