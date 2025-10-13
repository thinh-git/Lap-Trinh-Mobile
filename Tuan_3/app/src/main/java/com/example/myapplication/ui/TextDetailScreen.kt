package com.example.myapplication.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextDetailScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            fontSize = 30.sp,
            lineHeight = 45.sp,
            text = buildAnnotatedString {
                append("The ")
                withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                    append("quick")
                }
                append(" ")
                withStyle(style = SpanStyle(fontSize = 38.sp, color = Color(0xFF8B4513), fontWeight = FontWeight.Bold)) {
                    append("B")
                }
                withStyle(style = SpanStyle(fontSize = 30.sp, color = Color(0xFF8B4513))) {
                    append("rown\n")
                }
                append("fox j u m p s ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("over\n")
                }
                withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                    append("the")
                }
                append(" ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("lazy")
                }
                append(" dog.")
            }
        )
    }
}
