package com.github.handler_looper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.github.handler_looper.demos.AdvancedDemo
import com.github.handler_looper.demos.BasicsDemo
import com.github.handler_looper.demos.IntermediateDemo
import com.github.handler_looper.demos.ScenarioDemo
import com.github.handler_looper.ui.DemoCard
import com.github.handler_looper.ui.DemoItem
import com.github.handler_looper.ui.DemoPhase
import com.github.handler_looper.ui.ExplanationDialog
import com.github.handler_looper.ui.LogPanel
import com.github.handler_looper.ui.PhaseFilterRow
import com.github.handler_looper.ui.theme.KotlinTryoutsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val allDemos = BasicsDemo.demos +
                IntermediateDemo.demos +
                AdvancedDemo.demos +
                ScenarioDemo.demos

        setContent {
            KotlinTryoutsTheme {
                MainScreen(allDemos = allDemos)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(allDemos: List<DemoItem>) {
    var selectedPhase by remember { mutableStateOf<DemoPhase?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var activeExplanation by remember { mutableStateOf<DemoItem?>(null) }

    val filteredDemos = remember(selectedPhase, searchQuery) {
        allDemos.filter { demo ->
            val matchesPhase = selectedPhase == null || demo.phase == selectedPhase
            val matchesQuery = searchQuery.isBlank() ||
                    demo.title.contains(searchQuery, ignoreCase = true) ||
                    demo.questionText.contains(searchQuery, ignoreCase = true) ||
                    "q${demo.qNumber}".contains(searchQuery.trim().lowercase())
            matchesPhase && matchesQuery
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Handler & Looper Lab",
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "27 Observable Interview Demos",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Filter and Search Row
            PhaseFilterRow(
                selectedPhase = selectedPhase,
                onPhaseSelected = { selectedPhase = it },
                searchQuery = searchQuery,
                onSearchQueryChange = { searchQuery = it }
            )

            // Split View: Top 60% Demo List, Bottom 40% Live Log Panel
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(filteredDemos, key = { it.qNumber }) { demo ->
                        DemoCard(
                            demo = demo,
                            onExplainClick = { activeExplanation = it }
                        )
                    }
                }
            }

            // Live Log Panel
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
            ) {
                LogPanel()
            }
        }
    }

    // Explanation Dialog
    activeExplanation?.let { demo ->
        ExplanationDialog(
            demo = demo,
            onDismiss = { activeExplanation = null }
        )
    }
}
