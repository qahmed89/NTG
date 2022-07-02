package com.ahmed.ntg.presentation.screen.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ahmed.ntg.presentation.screen.ErrorScreen
import com.ahmed.ntg.presentation.screen.details.compose.LineChart

const val NAV_ROUTE_DETAILS_SCREEN = "DetailsScreen"

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navHostController: NavHostController,
    baseDetails: Details?
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getTimeSeries(baseDetails)
    }


    if (state.isLoading) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        }
        return
    }

    if (state.isNetworkError) {
        ErrorScreen(navHostController = navHostController) {
            viewModel.getTimeSeries(baseDetails)
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Details")
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {


            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                item {
                    LineChart(
                        lineChartData = state.graphData,
                        modifier = Modifier
                            .height(250.dp)
                            .padding(10.dp)
                    )

                }
                item {
                    Text(
                        text = "Historical Rates",
                        modifier = Modifier.padding(vertical = 10.dp),
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                state.timeSeries?.rates?.forEach { (date, map) ->
                    stickyHeader {
                        Text(
                            text = date, color = Color.Black, modifier = Modifier
                                .background(Color.Gray)
                                .padding(5.dp)
                                .fillMaxWidth()
                        )
                    }

                    items(map.toList().filter { it.first == baseDetails?.toBase }) { item ->
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "${baseDetails?.fromBase} = ${baseDetails?.fromValue} ",
                                color = Color.Black
                            )
                            Text(
                                text = "${baseDetails?.toBase} = ${item.second * baseDetails?.fromValue!!}",
                                color = Color.Black
                            )
                            Divider(modifier = Modifier.fillMaxWidth(), color = Color.Gray)
                        }
                    }
                }

                item {
                    Text(
                        text = "Other Currency",
                        modifier = Modifier.padding(vertical = 10.dp),
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(state.otherCountery.toList()) { items ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)) {
                        Text(text = "${items.first} = ", color = Color.Black)
                        Text(text = items.second.toString(), color = Color.Black)
                    }

                }

            }
        }
    }
}
