package com.example.thuc_hanh_4

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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

//Encapsulation Đóng gói toàn bộ dữ liệu của một trang Onboarding vào một class riêng biệt
data class OnboardingPageData2(
    val imageResId: Int,
    val title: String,
    val description: String,
    val pageIndex: Int,
    val totalPages: Int,
    val backIconResId: Int
)

//Abstraction Tách phần hiển thị giao diện ra thành một hàm composable riêng biệt
@Composable
fun OnboardingContent2(
    data: OnboardingPageData2,
    onBack: () -> Unit = {},
    onNext: () -> Unit = {},
    onSkip: () -> Unit = {}
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

        //Abstraction Hiển thị nội dung chính từ dữ liệu được truyền vào
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

        //Encapsulation Hành động (Back, Next) được truyền từ bên ngoài
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onBack,
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = data.backIconResId),
                    contentDescription = "Back Icon",
                    modifier = Modifier.size(48.dp)
                )
            }

            Button(
                onClick = onNext,
                modifier = Modifier
                    .height(48.dp)
                    .width(250.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006EE9))
            ) {
                Text(text = "Next", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun OnboardingScreen2(
    onBack: () -> Unit = {},
    onNext: () -> Unit = {},
    onSkip: () -> Unit = {}
) {
    val data = OnboardingPageData2(
        imageResId = R.drawable.increase_efficiency,
        title = "Increase Work Effectiveness",
        description = "Time management and the determination of more important tasks will give your job statistics better and always improve",
        pageIndex = 1,
        totalPages = 3,
        backIconResId = R.drawable.page3_image
    )

    OnboardingContent2(
        data = data,
        onBack = onBack,
        onNext = onNext,
        onSkip = onSkip
    )
}

@Preview(showBackground = true, name = "Onboarding Screen 2 Preview")
@Composable
fun PreviewOnboardingScreen2() {
    Thuc_hanh_4Theme {
        OnboardingScreen2()
    }
}
