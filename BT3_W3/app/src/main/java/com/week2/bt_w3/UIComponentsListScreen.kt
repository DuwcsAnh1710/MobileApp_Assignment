package com.week2.bt_w3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UIComponentsListPreview() {
    UIComponentsListContent()
}

@Composable
fun UIComponentsListScreen(navController: NavHostController) {
    UIComponentsListContent { route ->
        navController.navigate(route)
    }
}

@Composable
fun UIComponentsListContent(onNavigate: (String) -> Unit = {}) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "UI Components List",
            color = Color(0xFF5B9EFF),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(16.dp))

        Text("Display", fontWeight = FontWeight.Bold)
        ComponentItem("Text", "Displays text") { onNavigate("text_detail") }
        ComponentItem("Image", "Displays an image") { onNavigate("images") }

        Spacer(Modifier.height(8.dp))

        Text("Input", fontWeight = FontWeight.Bold)
        ComponentItem("TextField", "Input field for text") { onNavigate("textfield") }
        ComponentItem("PasswordField", "Input field for passwords") { /* Chưa định nghĩa */ }

        Spacer(Modifier.height(8.dp))

        Text("Layout", fontWeight = FontWeight.Bold)
        ComponentItem("Column", "Arranges elements vertically") { onNavigate("column_layout") }
        ComponentItem("Row", "Arranges elements horizontally") { onNavigate("row_layout") }

        ComponentItem(
            "Tự tìm hiểu", "Tìm ra tất cả các thành phần UI Cơ bản",
            backgroundColor = Color(0xFFFFD6D6),
            textColor = Color(0xFFB00020)
        ) { /* Chưa định nghĩa */ }
    }
}


@Composable
fun ComponentItem(
    title: String,
    desc: String,
    backgroundColor: Color = Color(0xFFD6E6FF),
    textColor: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .clickable(enabled = onClick != {}, onClick = onClick)
            .padding(12.dp)
    ) {
        Text(title, fontWeight = FontWeight.Bold, color = textColor)
        Text(desc, color = textColor)
    }
}


