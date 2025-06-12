package com.example.thuc_hanh_4

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.thuc_hanh_4.ui.theme.Thuc_hanh_4Theme

@Composable
fun OnboardingScreen6(
    viewModel: MainViewModel,
    onBack: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirm by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    val passwordFocusRequester = remember { FocusRequester() }
    val confirmFocusRequester = remember { FocusRequester() }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 40.dp),
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
        Spacer(Modifier.height(16.dp))
        Text("SmartTasks", color = Color(0xFF2196F3), fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(Modifier.height(24.dp))
        Text("Create new password", fontWeight = FontWeight.Bold, fontSize = 22.sp)
        Spacer(Modifier.height(8.dp))
        Text(
            "Your new password must be different from previously used password",
            color = Color.Gray, fontSize = 14.sp, textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Spacer(Modifier.height(28.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                error = null
            },
            placeholder = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, null) },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(if (showPassword) Icons.Default.VisibilityOff else Icons.Default.Visibility, null)
                }
            },
            isError = error != null && password != confirm,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocusRequester)
                .onKeyEvent {
                    if (it.type == KeyEventType.KeyDown && (it.key == Key.Enter || it.key == Key.Tab)) {
                        confirmFocusRequester.requestFocus()
                        true
                    } else {
                        false
                    }
                },
            shape = RoundedCornerShape(12.dp),
        )
        Spacer(Modifier.height(14.dp))

        // Confirm Password field
        OutlinedTextField(
            value = confirm,
            onValueChange = {
                confirm = it
                error = null
            },
            placeholder = { Text("Confirm Password") },
            leadingIcon = { Icon(Icons.Default.Lock, null) },
            visualTransformation = if (showConfirm) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { showConfirm = !showConfirm }) {
                    Icon(if (showConfirm) Icons.Default.VisibilityOff else Icons.Default.Visibility, null)
                }
            },
            isError = error != null && password != confirm,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(confirmFocusRequester),
            shape = RoundedCornerShape(12.dp),
        )

        if (error != null)
            Text(error!!, color = Color.Red, fontSize = 14.sp, modifier = Modifier.padding(top = 8.dp))

        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                when {
                    password.length < 6 -> error = "Password must be at least 6 characters"
                    password != confirm  -> error = "Passwords do not match!"
                    else -> {
                        viewModel.userPassword = password
                        onNext()
                    }
                }
            },
            enabled = password.length >= 6 && confirm.length >= 6,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) { Text("Next", fontSize = 18.sp, color = Color.White) }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen6() {
    val fakeVM = object : MainViewModel() {
        init { userEmail = "test@example.com" }
    }
    Thuc_hanh_4Theme {
        OnboardingScreen6(
            viewModel = fakeVM,
            onBack = {},
            onNext = {}
        )
    }
}
