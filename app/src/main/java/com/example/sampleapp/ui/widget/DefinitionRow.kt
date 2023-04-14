package com.example.sampleapp.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sampleapp.ui.theme.SampleAppTheme

@Composable
fun DefinitionRow(
    position: Int,
    definition: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "$position.",
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.End,
            modifier = Modifier
                .defaultMinSize(minWidth = 40.dp)
                .padding(horizontal = 4.dp)
        )

        Text(
            text = definition,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview
@Composable
fun DefinitionRowPreview() {
    SampleAppTheme {
        DefinitionRow(
            position = 1,
            definition = "Noun"
        )
    }
}
