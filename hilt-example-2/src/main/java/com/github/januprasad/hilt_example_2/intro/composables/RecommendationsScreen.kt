package com.github.januprasad.hilt_example_2.intro.composables

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.januprasad.hilt_example_2.NavRoutes
import com.github.januprasad.hilt_example_2.R
import com.github.januprasad.hilt_example_2.app.AppEvent
import com.github.januprasad.hilt_example_2.app.ui.previews.AllScreenPreview

@Composable
fun RecommendationScreen(
    navController: NavController,
    onEvent: (appEvent: AppEvent) -> Unit,
) = IntroCompose(
    navController = navController,
    text = "Recommendation",
    buttonText = R.string.start_app,
) {
    onEvent(AppEvent.FinishOnboarding)
    navController.navigate(NavRoutes.MainRoute.name) {
        popUpTo(NavRoutes.IntroRoute.name)
    }
}

@AllScreenPreview
@Composable
fun RecommendationScreenPreview() {
    val navController = rememberNavController()
    RecommendationScreen(navController = navController) {}
}
