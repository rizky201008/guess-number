package com.vixiloc.guessnumber.presentation.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ConfirmationDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    confirmButton: @Composable () -> Unit = {},
    title: String,
    message: String,
    dismissText: String = ""
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = confirmButton,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        dismissButton = {
            if (dismissText.isNotBlank()) {
                TextButton(onClick = onDismiss) {
                    Text(text = dismissText)
                }
            }
        })
}