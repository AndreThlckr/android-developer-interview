package com.example.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.sampleapp.ui.theme.Dimens
import com.example.sampleapp.ui.theme.SampleAppTheme
import com.example.sampleapp.ui.widget.WordDataBox
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MainScreen(
        state = state,
        onQueryChange = viewModel::notifyQueryChanged,
        onSearch = viewModel::loadWordData
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    state: MainState,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = Dimens.elevationNormal
            ) {
                Text(
                    text = "Dictionary",
                    style = SampleAppTheme.typography.h3,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.paddingLarge),
                horizontalArrangement = Arrangement.spacedBy(Dimens.paddingSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = state.query,
                    onValueChange = onQueryChange,
                    textStyle = TextStyle(fontSize = 14.sp),
                    label = { Text("Enter word") },
                    singleLine = true,
                    maxLines = 1,
                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp)
                )
                Button(
                    onClick = onSearch,
                    modifier = Modifier
                        .height(intrinsicSize = IntrinsicSize.Max)
                        .padding(top = Dimens.paddingSmall)
                ) {
                    Text(text = "Search")
                }
            }

            AnimatedContent(
                targetState = state,
                modifier = Modifier.weight(1F)
            ) { state ->
                when {
                    state.data != null -> WordDataBox(data = state.data)
                    state.error == null -> EmptyMessage()
                    else -> ErrorMessage(error = state.error)
                }
            }
        }
    }
}

@Composable
fun ColumnScope.EmptyMessage() {
    InfoMessage(message = stringResource(R.string.empty_list_message))
}

@Composable
fun ColumnScope.ErrorMessage(
    @StringRes error: Int
) {
    InfoMessage(message = stringResource(error))
}

@Composable
fun ColumnScope.InfoMessage(
    message: String
) {
    Box(
        modifier = Modifier.weight(1F),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SampleAppTheme {
        MainScreen()
    }
}
