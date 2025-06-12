package com.example.thuc_hanh_4

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thuc_hanh_4.ui.theme.Thuc_hanh_4Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
@Composable
fun OnboardingScreen4(
    viewModel: MainViewModel,
    onNext: () -> Unit = {}
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var sending by remember { mutableStateOf(false) }
    var sentSuccess by remember { mutableStateOf(false) }
    var sendError by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.uth_logo),
            contentDescription = "UTH Logo",
            modifier = Modifier
                .height(120.dp)
                .width(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "SmartTasks",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF006EE9)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Forget Password?",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Enter your Email, we will send you a verification code.",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Email input
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                sendError = null
                sentSuccess = false
            },
            placeholder = { Text("Your Email") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.mail_24px),
                    contentDescription = "Email Icon"
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color(0xFF006EE9),
                unfocusedIndicatorColor = Color.LightGray,
                cursorColor = Color(0xFF006EE9)
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Send OTP Button
        Button(
            onClick = {
                val targetEmail = email.text.trim()
                if (targetEmail.isEmpty()) {
                    sendError = "Email cannot be empty"
                    return@Button
                }

                sending = true
                sendError = null

                val otp = (100_000..999_999).random().toString()
                viewModel.userEmail = targetEmail
                viewModel.generatedOtp = otp

                scope.launch {
                    try {
                        withContext(Dispatchers.IO) {
                            GmailSender(
                                user = "anhhao1654@gmail.com",
                                password = "keutsqbkqyhvduvi" // App password
                            ).sendMail(
                                subject = "SmartTasks OTP Verification",
                                body = "Your OTP is: $otp \nplease use it to reset your password.",
                                toEmail = targetEmail
                            )
                        }
                        sentSuccess = true
                        onNext()
                    } catch (e: Exception) {
                        sendError = "Failed to send OTP: ${e.localizedMessage}"
                    } finally {
                        sending = false
                    }
                }
            },
            enabled = email.text.isNotBlank() && !sending,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text(
                text = if (sending) "Sending..." else "Next",
                color = Color.White,
                fontSize = 16.sp
            )
        }

        // Status messages
        if (sentSuccess) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "OTP sent to ${email.text}",
                color = Color(0xFF43A047),
                fontSize = 14.sp
            )
        }
        sendError?.let {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = it,
                color = Color.Red,
                fontSize = 14.sp
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOnboardingScreen4() {
    Thuc_hanh_4Theme {
        OnboardingScreen4(
            viewModel = MainViewModel(),
            onNext = {}
        )
    }
}