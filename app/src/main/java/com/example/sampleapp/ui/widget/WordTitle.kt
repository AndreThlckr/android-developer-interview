package com.example.sampleapp.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.sampleapp.ui.theme.SampleAppTheme

@Composable
fun WordTitle(
    word: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = word,
        style = MaterialTheme.typography.h3,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun WordTitlePreview() {
    SampleAppTheme {
        WordTitle(word = "Noun")
    }
}

