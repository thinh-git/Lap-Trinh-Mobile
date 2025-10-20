package com.example.myapplication.library.dataflow

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun VerifyCodeScreen(viewModel: ForgotViewModel, onNext: () -> Unit) {
    val code = viewModel.code
    val focusRequesters = remember { List(6) { FocusRequester() } }

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "UTH", style = MaterialTheme.typography.headlineLarge, color = Color(0xFF00529E))
        Text(text = "SmartTasks", style = MaterialTheme.typography.titleMedium, color = Color.Gray)

        Spacer(modifier = Modifier.height(48.dp))

        Text(text = "Verify Code", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Enter the the code we just sent you on your registered Email",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            for (i in 0 until 6) {
                OutlinedTextField(
                    value = code.getOrNull(i)?.toString() ?: "",
                    onValueChange = {
                        if (it.length <= 1) {
                            val newCode = code.toMutableList()
                            if (newCode.size > i) {
                                newCode[i] = it.firstOrNull() ?: ' '
                            } else {
                                newCode.add(it.firstOrNull() ?: ' ')
                            }
                            viewModel.code = newCode.joinToString("")

                            if (it.isNotEmpty() && i < 5) {
                                focusRequesters[i + 1].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .width(48.dp)
                        .focusRequester(focusRequesters[i]),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (viewModel.verifyCode()) onNext()
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Next")
        }
        viewModel.showMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }
    }
}
