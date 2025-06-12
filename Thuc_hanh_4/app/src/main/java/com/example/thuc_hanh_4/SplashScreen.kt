package com.example.thuc_hanh_4

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.ui.tooling.preview.Preview
import com.example.thuc_hanh_4.ui.theme.Thuc_hanh_4Theme

//Encapsulation Font được đóng gói trong biến riêng để dễ quản lý
val righteousFontFamily = FontFamily(
    Font(R.font.righteous_regular, FontWeight.Normal)
)

//Abstraction Giao diện splash được gói gọn trong một hàm @Composable có tên rõ ràng, chỉ cần gọi SplashScreen(onTimeout = {...})
@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(key1 = true) {
        delay(4000L)
        onTimeout()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "UTH Logo",
            modifier = Modifier.size(width = 150.dp, height = 120.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "UTH SmartTasks",
            fontSize = 30.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = righteousFontFamily,
            color = Color(0xFF006EE9),
            textAlign = TextAlign.Center
        )
    }
}
@Preview(showBackground = true, name = "Splash Screen Preview")
@Composable
fun PreviewSplashScreen() {
    Thuc_hanh_4Theme {
        SplashScreen(onTimeout = {})
    }
}