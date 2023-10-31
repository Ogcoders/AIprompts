package com.verygoodconsulting.aiprompts

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.verygoodconsulting.aiprompts.domain.HomeScreenData
import com.verygoodconsulting.aiprompts.home.HomeViewModel
import com.verygoodconsulting.aiprompts.model.db.PromptEntity
import com.verygoodconsulting.aiprompts.ui.theme.AIPromptsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewmodel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIPromptsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state = remember(viewmodel) {
                        viewmodel.data()
                    }.collectAsState()
                    when (state.value) {
                        is HomeScreenData.Dummy -> {
                            Text(text = (state.value as HomeScreenData.Dummy).msg)
                        }

                        is HomeScreenData.Info -> {
                            LazyColumn() {
                                items((state.value as HomeScreenData.Info).items) {
                                    Prompt(promptEntity = it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Prompt(promptEntity: PromptEntity) {
    Column {
        Text(
            text = promptEntity.name,
            Modifier.padding(8.dp),
            fontSize = 16.sp
        )
        Text(
            text = promptEntity.description,
            Modifier.padding(8.dp),
            fontSize = 12.sp
        )
        Text(
            text = promptEntity.instruction,
            Modifier.padding(8.dp),
            fontSize = 12.sp
        )
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AIPromptsTheme {
        Greeting("Android")
    }
}