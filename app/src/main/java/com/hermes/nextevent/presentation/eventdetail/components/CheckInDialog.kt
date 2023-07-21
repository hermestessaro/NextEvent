package com.hermes.nextevent.presentation.eventdetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hermes.nextevent.util.isValidEmail

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckInDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    val nameValue = remember { mutableStateOf("") }
    val emailValue = remember { mutableStateOf("") }
    val nameError = remember { mutableStateOf(false) }
    val emailError = remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Insira os dados obrigatórios:") },
            text = {
                Column {
                    TextField(
                        value = nameValue.value,
                        onValueChange = {
                            nameValue.value = it
                            nameError.value = it.isEmpty()
                        },
                        isError = nameError.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        placeholder = { Text (text = "Nome:") }
                    )
                    if(nameError.value) {
                        Text(
                            text = "Campo obrigatório",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    TextField(
                        value = emailValue.value,
                        onValueChange = {
                            emailValue.value = it
                            emailError.value = !it.isValidEmail()
                        },
                        isError = emailError.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        placeholder = { Text (text = "E-mail:") }
                    )
                    if(emailError.value) {
                        Text(
                            text = "Campo obrigatório",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

            },
            confirmButton = {
                TextButton(onClick = {
                    if(nameValue.value.isNotEmpty() && emailValue.value.isValidEmail()) {
                        onConfirm(nameValue.value, emailValue.value)
                        onDismiss()
                    } else {
                        nameError.value = nameValue.value.isEmpty()
                        emailError.value = !emailValue.value.isValidEmail()
                    }
                }) {
                    Text("Check In")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismiss()
                }) {
                    Text(text = "Cancelar")
                }
            },
            modifier = Modifier
                .padding(4.dp)
                .height(400.dp)
        )
    }
}