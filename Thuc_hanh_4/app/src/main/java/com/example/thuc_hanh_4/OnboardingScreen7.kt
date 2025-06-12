package com.example.thuc_hanh_4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import com.example.thuc_hanh_4.ui.theme.Thuc_hanh_4Theme

@Composable
fun OnboardingScreen7(
    viewModel: MainViewModel,
    onBack: () -> Unit = {},
    onSubmit: () -> Unit = {}
) {
    val oldEmail = viewModel.userEmail
    val oldOtp = viewModel.generatedOtp
    val oldPassword = viewModel.userPassword

    var email by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }
    var success by remember { mutableStateOf(false) }

    // Tự động chuyển màn hình sau 5 giây nếu xác nhận thành công
    LaunchedEffect(success) {
        if (success) {
            delay(5000)
            onSubmit()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        IconButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.Start)
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFEEF6FF))
        ) {
            Icon(Icons.Default.ArrowBack, "Back", modifier = Modifier.size(24.dp))
        }
        Spacer(Modifier.height(10.dp))
        Text("SmartTasks", color = Color(0xFF2196F3), fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(Modifier.height(18.dp))
        Text("Confirm", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Spacer(Modifier.height(6.dp))
        Text(
            "We are here to help you!",
            color = Color.Gray,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(28.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it; error = null; success = false },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
            placeholder = { Text("Email") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(Modifier.height(10.dp))

        // OTP
        OutlinedTextField(
            value = otp,
            onValueChange = { otp = it.filter { c -> c.isDigit() }.take(6); error = null; success = false },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "OTP") },
            placeholder = { Text("OTP") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(Modifier.height(10.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it; error = null; success = false },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
            placeholder = { Text("Password") },
            singleLine = true,
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(
                        if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Toggle password"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp)
        )

        if (error != null)
            Text(error!!, color = Color.Red, fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp))
        if (success)
            Text("Confirm successful!", color = Color(0xFF43A047), fontSize = 15.sp, modifier = Modifier.padding(top = 8.dp))

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                when {
                    email != oldEmail -> error = "Email does not match!"
                    otp != oldOtp -> error = "OTP does not match!"
                    password != oldPassword -> error = "Password does not match!"
                    else -> {
                        error = null
                        success = true
                    }
                }
            },
            enabled = email.isNotBlank() && otp.length == 6 && password.isNotBlank() && !success,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("Submit", fontSize = 18.sp, color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen7() {
    val fakeVM = object : MainViewModel() {
        init {
            userEmail = "uth@gmail.com"
            generatedOtp = "123456"
            userPassword = "password123"
        }
    }
    Thuc_hanh_4Theme {
        OnboardingScreen7(
            viewModel = fakeVM
        )
    }
}
