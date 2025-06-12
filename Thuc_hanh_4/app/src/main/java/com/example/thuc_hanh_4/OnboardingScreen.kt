package com.example.thuc_hanh_4

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thuc_hanh_4.ui.theme.Thuc_hanh_4Theme

//  Encapsulation: dữ liệu của một trang onboarding
data class OnboardingData(
    val imageResId: Int,
    val title: String,
    val description: String,
    val pageIndex: Int,
    val totalPages: Int
)

// Abstraction + Reusability: giao diện hiển thị một trang onboarding
@Composable
fun OnboardingContent(
    data: OnboardingData,
    onSkip: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

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

        Button(
            onClick = { onNext() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomCenter),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
        ) {
            Text(text = "Next", color = Color.White, fontSize = 16.sp)
        }
    }
}

@Composable
fun OnboardingScreen(
    onSkip: () -> Unit = {},
    onNext: () -> Unit = {}
) {
    val data = OnboardingData(
        imageResId = R.drawable.task_management,
        title = "Easy Time Management",
        description = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first",
        pageIndex = 0,
        totalPages = 3
    )

    OnboardingContent(
        data = data,
        onSkip = onSkip,
        onNext = onNext
    )
}
@Preview(showBackground = true, name = "Onboarding Screen 1 Preview")
@Composable
fun PreviewOnboardingScreen() {
    Thuc_hanh_4Theme {
        OnboardingScreen()
    }
}
