package com.example.thuc_hanh_4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import com.example.thuc_hanh_4.ui.theme.Thuc_hanh_4Theme

@Composable
fun OnboardingScreen5(
    userEmail: String,
    generatedOtp: String,
    onBack: () -> Unit = {},
    onVerified: () -> Unit = {},
    onResendOtp: (() -> Unit)? = null
) {
    val focusList = remember { List(6) { FocusRequester() } }
    var otpInput by remember { mutableStateOf(List(6) { "" }) }
    var isError by remember { mutableStateOf(false) }
    var timer by remember { mutableStateOf(60) }
    var resendEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(generatedOtp) {
        timer = 60
        resendEnabled = false
        while (timer > 0) {
            delay(1000)
            timer--
        }
        resendEnabled = true
    }
    LaunchedEffect(Unit) { focusList[0].requestFocus() }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth()) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back") }
            Spacer(Modifier.weight(1f))
        }
        Spacer(Modifier.height(8.dp))
        Text("SmartTasks", fontWeight = FontWeight.Bold, color = Color(0xFF006EE9), fontSize = 22.sp)
        Spacer(Modifier.height(20.dp))
        Text("Verify Code", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
        Spacer(Modifier.height(8.dp))
        Text(
            "Enter the code we just sent to\n$userEmail",
            fontSize = 14.sp, color = Color.Gray, textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                if (!resendEnabled) "Resend code in ${timer}s" else "Didn't receive the code?",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(Modifier.width(8.dp))
            TextButton(
                enabled = resendEnabled,
                onClick = { onResendOtp?.invoke() }
            ) { Text("Resend code", color = if (resendEnabled) Color(0xFF006EE9) else Color.Gray) }
        }
        Spacer(Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            otpInput.forEachIndexed { idx, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = { input ->
                        val filtered = input.filter { it.isDigit() }
                        var newOtp = otpInput.toMutableList()

                        when {
                            // Dán nhiều ký tự một lúc
                            filtered.length > 1 -> {
                                filtered.take(6 - idx).forEachIndexed { pasteIdx, c ->
                                    newOtp[idx + pasteIdx] = c.toString()
                                }
                                otpInput = newOtp
                                // focus vào ô cuối cùng được paste
                                focusList.getOrNull(idx + filtered.length - 1)?.requestFocus()
                            }
                            // Nhập một ký tự số
                            filtered.length == 1 -> {
                                newOtp[idx] = filtered
                                otpInput = newOtp
                                if (idx < 5) focusList[idx + 1].requestFocus()
                            }
                            // Xoá ô hiện tại
                            filtered.isEmpty() -> {
                                newOtp[idx] = ""
                                otpInput = newOtp
                                if (idx > 0) focusList[idx - 1].requestFocus()
                            }
                        }
                    },
                    singleLine = true,
                    modifier = Modifier
                        .width(48.dp)
                        .focusRequester(focusList[idx]),
                    textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 20.sp),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }

        if (isError)
            Text("Incorrect code. Please try again.", color = Color.Red, fontSize = 14.sp, modifier = Modifier.padding(top = 12.dp))
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                if (otpInput.joinToString("") == generatedOtp) {
                    isError = false; onVerified()
                } else isError = true
            },
            modifier = Modifier.fillMaxWidth().height(55.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) { Text("Verify", color = Color.White, fontSize = 16.sp) }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen5() {
    Thuc_hanh_4Theme {
        OnboardingScreen5(
            userEmail = "test@example.com",
            generatedOtp = "123456",
            onBack = {},
            onVerified = {},
            onResendOtp = {}
        )
    }
}
