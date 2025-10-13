package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class Component(val title: String, val subtitle: String, val route: String? = null)

@Composable
fun ComponentsListScreen(navController: NavHostController) {
    val components = listOf(
        "Display" to listOf(
            Component("Text", "Displays text", "text_detail"),
            Component("Image", "Displays an image", "images")
        ),
        "Input" to listOf(
            Component("TextField", "Input field for text", "textfield"),
            Component("PasswordField", "Input field for passwords", "passwordfield")
        ),
        "Layout" to listOf(
            Component("Column", "Arranges elements vertically", "columnlayout"),
            Component("Row", "Arranges elements horizontally", "rowlayout")
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        components.forEach { (category, items) ->
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            items.forEach { component ->
                ComponentItem(component, navController)
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        SpecialComponentItem()
    }
}

@Composable
private fun ComponentItem(component: Component, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = component.route != null) { component.route?.let { navController.navigate(it) } },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = component.title, fontWeight = FontWeight.Bold)
            Text(text = component.subtitle, fontSize = 14.sp)
        }
    }
}

@Composable
private fun SpecialComponentItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Red.copy(alpha = 0.8f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Tự tìm hiểu", fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = "Tìm ra tất cả các thành phần UI Cơ bản", fontSize = 14.sp, color = Color.White)
        }
    }
}
