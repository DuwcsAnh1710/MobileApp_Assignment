package com.week2.bt_w3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("components") { UIComponentsListScreen(navController) }
        composable("text_detail") { TextDetailScreen(navController) }
        composable("images") { ImagesScreen(navController) }
        composable("textfield") { TextFieldScreen(navController) }
        composable("row_layout") { RowLayoutScreen(navController) }
        composable("column_layout") { ColumnLayoutScreen(navController) }
    }
} 