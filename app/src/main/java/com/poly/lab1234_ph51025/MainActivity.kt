package com.poly.lab1234_ph51025

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalContext
import com.poly.lab1234_ph51025.Lab6.CinemaSeatBookingScreen
import com.poly.lab1234_ph51025.Lab6.MainScreen1
import com.poly.lab1234_ph51025.Lab6.aisleColumn
import com.poly.lab1234_ph51025.Lab6.aisleRow
import com.poly.lab1234_ph51025.Lab6.createTheaterSeating
import com.poly.lab1234_ph51025.Lab6.totalRows
import com.poly.lab1234_ph51025.Lab6.totalSeatsPerRow
import com.poly.lab1234_ph51025.ui.theme.Lab1234_PH51025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
//                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                //MainScreen()

                //Lab5
//                LoginScreen1()
//                LightSwitch()
//                CategoryApp()


                MainScreen1()
//                CinemaSeatBookingScreen(
//                    seats = createTheaterSeating(totalRows, totalSeatsPerRow, aisleRow, aisleColumn),
//                    totalSeatsPerRow = totalSeatsPerRow
//                )

            }

        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            val intent = Intent(context, Login::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(context, ImageComponents::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Image List")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(context, JetpackCompose::class.java)
            context.startActivity(intent)
        }) {
            Text(text = "Notes")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab1234_PH51025Theme {
        Greeting("Android")
    }
}