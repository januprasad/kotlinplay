package com.github.handler_looper.ui

import android.content.Context

enum class DemoPhase(val title: String) {
    BASICS("Phase A: Basics (Q1-Q5)"),
    INTERMEDIATE("Phase B: Intermediate (Q6-Q12)"),
    ADVANCED("Phase C: Advanced (Q13-Q23)"),
    SCENARIO("Phase D: Scenarios (Q24-Q27)")
}

data class DemoItem(
    val qNumber: Int,
    val title: String,
    val phase: DemoPhase,
    val questionText: String,
    val explanationText: String,
    val runDemo: (context: Context) -> Unit
)
