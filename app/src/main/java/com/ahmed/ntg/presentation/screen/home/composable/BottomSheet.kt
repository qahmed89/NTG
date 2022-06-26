package com.ahmed.ntg.presentation.screen.home.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun SymbolsPickerBottomSheet(
    title: @Composable () -> Unit,
    show: Boolean,
    onItemSelected: (String: String) -> Unit,
    onDismissRequest: () -> Unit,
    symbol: List<String>,
    content: @Composable () -> Unit,
) {
    val symbols = remember { symbol }
    var selectedCountry by remember { mutableStateOf(symbols[0]) }
    //  val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val modalBottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = modalBottomSheetState
    )
    LaunchedEffect(key1 = show) {
        if (show) modalBottomSheetState.expand() else modalBottomSheetState.collapse()
    }

    LaunchedEffect(key1 = modalBottomSheetState.currentValue) {
        if (modalBottomSheetState.currentValue == BottomSheetValue.Collapsed) {
            onDismissRequest()
        }
    }

    BottomSheetScaffold(

        sheetPeekHeight = 1.dp,
        sheetContent = {
            title()
            LazyColumn(
                contentPadding = PaddingValues(16.dp)
            ) {
                items(symbols.size) { index ->
                    Column(
                        modifier = Modifier
                            .clickable {
                                selectedCountry = symbols[index]
                                onItemSelected(selectedCountry)
                            }
                            .padding(10.dp)
                    ) {
                        Text(
                            text = symbols[index]
                        )
                        Divider(color = Color.LightGray, thickness = 0.5.dp)

                    }

                }
            }

        },
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(
            topStart = 20.dp, topEnd = 20.dp
        )
    ) {
        content()
    }
}


