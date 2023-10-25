package com.example.lovelycats_android_sample_app.View

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lovelycats_android_sample_app.Helpers.BottomBarScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginView(navController: NavController) {
    var presentMainFlow =  mutableStateOf(false)
    Scaffold(content = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TextField(value = "", onValueChange = {

            }, label = {
                Text("Email")
            },
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(value = "", onValueChange = {

            }, label = {
                Text("Password")
            }
                ,leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )

            Spacer(modifier = Modifier.height(18.dp))

            Button(onClick = {
                presentMainFlow.value = true
            }) {
                Text(text = "Login")
            }

            if (presentMainFlow.value) {
                navController.popBackStack()
                navController.navigate(BottomBarScreen.Home.route)
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginView(navController = rememberNavController())
}