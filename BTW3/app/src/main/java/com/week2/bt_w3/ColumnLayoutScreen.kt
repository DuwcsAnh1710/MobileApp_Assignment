package com.week2.bt_w3

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview

data class QuoteItem(val id: Int, val text: String)

val sampleQuotes = List(10000) {
    QuoteItem(
        id = it + 1,
        text = "The only way to do great work is to love what you do."
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ColumnLayoutScreenPreview() {
    ColumnLayoutContent(
        dataList = sampleQuotes,
        onBack = {},
        onItemClick = {}
    )
}

@Composable
fun ColumnLayoutScreen(navController: NavHostController) {
    ColumnLayoutContent(
        dataList = sampleQuotes,
        onBack = { navController.popBackStack() },
        onItemClick = { id -> navController.navigate("detail/$id") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnLayoutContent(
    dataList: List<QuoteItem>,
    onBack: () -> Unit = {},
    onItemClick: (Int) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "LazyColumn",
                        color = Color(0xFF2196F3),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF2196F3)
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(dataList) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .background(
                            color = if (item.id % 2 == 0) Color(0xFF9AC2EC) else Color(0xFF4973EC),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable(enabled = false) {},
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${item.id} | ${item.text}",
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = { onItemClick(item.id) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowRight,
                                contentDescription = "Go to Detail",
                                tint = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
