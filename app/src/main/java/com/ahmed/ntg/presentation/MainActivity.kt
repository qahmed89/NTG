package com.ahmed.ntg.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmed.ntg.presentation.screen.HomeScreen
import com.ahmed.ntg.presentation.screen.NAV_ROUTE_HOME_SCREEN
import com.ahmed.ntg.presentation.screen.details.Details
import com.ahmed.ntg.presentation.screen.details.DetailsScreen
import com.ahmed.ntg.presentation.screen.details.DetailsType
import com.ahmed.ntg.presentation.screen.details.NAV_ROUTE_DETAILS_SCREEN
import com.ahmed.ntg.presentation.ui.theme.NTGTheme
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.O)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NTGTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NAV_ROUTE_HOME_SCREEN
                    ) {
                        composable(route = NAV_ROUTE_HOME_SCREEN) {
                            HomeScreen(navHostController = navController)
                        }

                        composable(
                            route = "${NAV_ROUTE_DETAILS_SCREEN}/{details}",
                            arguments = listOf(
                                navArgument("details") {
                                    type = DetailsType()
                                },
                            )
                        ) {

                            DetailsScreen(
                                navHostController = navController,
                                baseDetails = it.arguments?.getParcelable<Details>("details")
                            )
                        }
                    }
                }
            }
        }
    }

}