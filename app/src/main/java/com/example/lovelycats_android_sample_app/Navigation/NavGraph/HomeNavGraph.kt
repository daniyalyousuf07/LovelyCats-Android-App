import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lovelycats_android_sample_app.Helpers.BottomBarScreen
import com.example.lovelycats_android_sample_app.Managers.PreferencesManager
import com.example.lovelycats_android_sample_app.Navigation.NavGraph.AuthScreen
import com.example.lovelycats_android_sample_app.Navigation.NavGraph.Graph
import com.example.lovelycats_android_sample_app.View.TabbarView

fun NavGraphBuilder.homeNavGraph(navController: NavHostController,
                                 preferencesManager: PreferencesManager) {
       navigation(
           startDestination = BottomBarScreen.Home.route,
           route = Graph.HOME
       ) {
           composable(route = BottomBarScreen.Home.route) {
               TabbarView(navController = navController, popToRoot = {
                   navController.popBackStack()
                   navController.navigate(AuthScreen.Login.route)
                   preferencesManager.save("hasSeenOnboarding", false)
               })
           }
       }
}

sealed class DetailsScreen(val route: String) {
    object CatDetail: DetailsScreen (
        route = "cat-detail={model}",
    )
}
