package com.ajs.abetteru.ui.comman

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ajs.abetteru.ui.theme.ABetterUTheme
import com.ajs.abetteru.ui.theme.spacing
import kotlinx.coroutines.android.awaitFrame

@Composable
fun TitleComponent(
    title: String = "ABetterU"
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                MaterialTheme.spacing.medium
            ),
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurface.copy(
            alpha = 0.87f
        )
    )
}

@Composable
fun EditText(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
    textStyle: TextStyle = LocalTextStyle.current,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    readOnly: Boolean = false,
    focusRequester: FocusRequester? = null,
    onValueChange: (String) -> Unit = {},
) {
    LaunchedEffect(focusRequester) {
        awaitFrame()
        focusRequester?.requestFocus()
    }
    OutlinedTextField(
        modifier = modifier.let {
            if (focusRequester == null) it
            else it.focusRequester(focusRequester)
        },
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        placeholder = { Text(text = label) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        readOnly = readOnly,
    )
}

@Composable
fun TitleItem(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke()
            }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(
                    vertical = MaterialTheme.spacing.large,
                    horizontal = MaterialTheme.spacing.medium
                ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Start
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleComponentPreview() {
    ABetterUTheme {
        TitleItem(
            text = "ABetterU"
        )
    }
}