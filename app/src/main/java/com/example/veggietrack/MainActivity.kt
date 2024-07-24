package com.example.veggietrack

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.veggietrack.ui.theme.VeggieTrackTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      enableEdgeToEdge()
      setContent {
         VeggieTrackTheme {
            val intent = Intent(this, RegisterPlantsActivity::class.java)
            startActivity(intent)
         }
      }
   }
}
