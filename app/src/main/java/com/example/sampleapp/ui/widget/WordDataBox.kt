package com.example.sampleapp.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sampleapp.repository.WordData
import com.example.sampleapp.ui.theme.SampleAppTheme

@Composable
fun WordDataBox(
    data: WordData,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        WordTitle(
            word = data.word,
            modifier = Modifier.padding(8.dp)
        )
        FunctionChip(
            function = data.function,
            modifier = Modifier.padding(4.dp)
        )

        data.definitions.forEachIndexed { index, definition ->
            DefinitionRow(
                position = index + 1,
                definition = definition,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WordDataBoxPreview() {
    SampleAppTheme {
        WordDataBox(
            data = WordData(
                word = "test",
                function = "noun",
                definitions = listOf(
                    "a means of testing",
                    "something for measuring the skill capacities or aptitudes of an individual or group [...]"
                )
            )
        )
    }
}

