package com.example.thuc_hanh_4

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thuc_hanh_4.ui.theme.Thuc_hanh_4Theme

// ✅ [Encapsulation]
// Toàn bộ dữ liệu UI cho Onboarding 3 được gói trong một data class
data class OnboardingPageData3(
    val imageResId: Int,
    val title: String,
    val description: String,
    val pageIndex: Int,
    val totalPages: Int,
    val backIconResId: Int
)

// ✅ [Abstraction]
// UI được tách riêng, chỉ nhận dữ liệu + callback → không xử lý điều hướng bên trong
@Composable
fun OnboardingContent3(
    data: OnboardingPageData3,
    onBack: () -> Unit = {},
    onGetStarted: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DotsIndicator(totalDots = data.totalPages, selectedIndex = data.pageIndex)

            Text(
                text = "Skip",
                color = Color(0xFF006EE9),
                fontSize = 14.sp,
                modifier = Modifier.clickable { onSkip() }
            )
        }

        // Main content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = data.imageResId),
                contentDescription = data.title,
                modifier = Modifier.size(260.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = data.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = data.description,
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        }

        // Bottom row: back + get started
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color.White, shape = CircleShape)
                    .clickable { onBack() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = data.backIconResId),
                    contentDescription = "Back Icon",
                    modifier = Modifier.size(50.dp)
                )
            }

            Button(
                onClick = onGetStarted,
                modifier = Modifier
                    .height(48.dp)
                    .width(250.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
            ) {
                Text(text = "Get Started", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

// ✅ [Polymorphism]
// Composable có thể nhận các hành vi khác nhau qua các callback
@Composable
fun OnboardingScreen3(
    onBack: () -> Unit = {},
    onGetStarted: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    val data = OnboardingPageData3(
        imageResId = R.drawable.reminder_notification,
        title = "Reminder Notification",
        description = "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set",
        pageIndex = 2,
        totalPages = 3,
        backIconResId = R.drawable.page3_image
    )

    OnboardingContent3(
        data = data,
        onBack = onBack,
        onGetStarted = onGetStarted,
        onSkip = onSkip
    )
}

// ✅ [Inheritance]
// Thể hiện trong việc kế thừa nền tảng @Composable từ Jetpack Compose
@Preview(showBackground = true, name = "Onboarding Screen 3 Preview")
@Composable
fun PreviewOnboardingScreen3() {
    Thuc_hanh_4Theme {
        OnboardingScreen3()
    }
}
