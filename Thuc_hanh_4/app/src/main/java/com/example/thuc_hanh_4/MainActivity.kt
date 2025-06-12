package com.example.thuc_hanh_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.thuc_hanh_4.ui.theme.Thuc_hanh_4Theme


class AppStateManager {
    var showSplash by mutableStateOf(true)
        private set

    var currentPage by mutableStateOf(0)
        private set

    fun hideSplash() {
        showSplash = false
        currentPage = 0
    }

    fun goToPage(page: Int) {
        currentPage = page
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Thuc_hanh_4Theme {
                val appState = remember { AppStateManager() }
                val viewModel: MainViewModel = viewModel()

                AppNavigation(appState, viewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(appState: AppStateManager, viewModel: MainViewModel) {
    when {
        appState.showSplash -> {
            SplashScreen {
                appState.hideSplash()
            }
        }
        else -> {
            when (appState.currentPage) {
                0 -> OnboardingScreen(
                    onSkip = { appState.goToPage(3) },
                    onNext = { appState.goToPage(1) }
                )
                1 -> OnboardingScreen2(
                    onBack = { appState.goToPage(0) },
                    onSkip = { appState.goToPage(3) },
                    onNext = { appState.goToPage(2) }
                )
                2 -> OnboardingScreen3(
                    onBack = { appState.goToPage(1) },
                    onSkip = { appState.goToPage(3) },
                    onGetStarted = { appState.goToPage(4) }
                )
                3 -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Congratulation!", fontSize = 32.sp)
                }
                4 -> OnboardingScreen4(
                    viewModel = viewModel,
                    onNext = { appState.goToPage(5) }
                )
                5 -> OnboardingScreen5(
                    userEmail = viewModel.userEmail,
                    generatedOtp = viewModel.generatedOtp,
                    onBack = { appState.goToPage(4) },
                    onVerified = { appState.goToPage(6) }
                )
                6 -> OnboardingScreen6(
                    viewModel = viewModel,
                    onBack = { appState.goToPage(5) },
                    onNext = { appState.goToPage(7) }
                )
                7 -> OnboardingScreen7(
                    viewModel = viewModel,
                    onBack = { appState.goToPage(6) },
                    onSubmit = { appState.goToPage(3) }
                )
            }
        }
    }
}
