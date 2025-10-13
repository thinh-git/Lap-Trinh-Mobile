package com.example.myapplication.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowLayoutScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        repeat(3) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(
                                color = Color(0xFF90CAF9),
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
        }
    }
}
