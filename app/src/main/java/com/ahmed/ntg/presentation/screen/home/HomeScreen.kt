package com.ahmed.ntg.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ahmed.ntg.presentation.screen.home.HomeViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val state by viewModel.state.collectAsState()
    var showDropDownList by remember {
        mutableStateOf(false)
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




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()
        ) {
            CountrySelection(
                label = "From",
                textx = state.fromSelectedValue,
                list = state.fromList ?: emptyList()
            ) {
                viewModel.onFromSelectedChanged(it)
                viewModel.getLatestForFromValue(it)
            }
            Button(onClick = {
                viewModel.updateBoth(state.fromSelectedValue, state.ToSelectedValue)
                viewModel.onFromValueChanged(
                    state.fromValue,
                    rates = state.latest?.rates?.get(state.fromSelectedValue)!!

                )
                viewModel.getLatestForFromValue(state.fromSelectedValue)

            }) {
                Text(text = "Switch")
            }
            CountrySelection(
                label = "To",
                textx = state.ToSelectedValue,
                list = state.toList ?: emptyList()) {
                viewModel.onFromValueChanged(
                    if (state.fromValue != 0.0) {
                        state.fromValue
                    } else 0.0,
                    rates = state.latest?.rates?.get(it)!!
                )
                viewModel.onToSelectedChanged(it)

            }

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(3f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Magenta,
                    unfocusedBorderColor = Color.Magenta
                ),
                value = state.fromValue.toString(),
                textStyle = TextStyle(Color.Black),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {

                    viewModel.onFromValueChanged(
                        if (it.isNotBlank()) {
                            it.toDoubleOrNull() ?: it.toDouble()
                        } else 0.0,
                        rates = state.latest?.rates?.get(state.fromSelectedValue)!!
                    )
                    //new value

                },
                enabled = state.fromSelectedValue.isNotBlank() && state.ToSelectedValue.isNotBlank()

            )
            Spacer(modifier = Modifier.weight(4f))
            OutlinedTextField(
                modifier = Modifier.weight(3f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Magenta,
                    unfocusedBorderColor = Color.Magenta
                ),
                textStyle = TextStyle(Color.Black),
                value = state.toValue.toString(),
                readOnly = true,
                onValueChange = {})

        }

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Details", color = Color.Black)
        }


    }


}


@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier,
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEach {
            DropdownMenuItem(
                modifier = Modifier,
                onClick = {
                    request(false)
                    selectedString(it)
                }
            ) {
                Text(it, modifier = Modifier.wrapContentWidth())
            }
        }
    }
}

@Composable
fun CountrySelection(
    label: String,
    textx: String,
    list: List<String>,
    onValueChanged: (String) -> Unit
) {
    val countryList = remember {
     mutableStateOf<List<String>>(emptyList())
    }
    countryList.value = list
    val text = remember { mutableStateOf("") }
    text.value = textx// initial value
    val isOpen = remember { mutableStateOf(false) } // initial value
    val openCloseOfDropDownList: (Boolean) -> Unit = {
        isOpen.value = it
    }
    val userSelectedString: (String) -> Unit = {
        text.value = it
        onValueChanged(it)
    }
    Box(
        modifier = Modifier
            .padding(16.dp)
            .width(100.dp)
    ) {
        Column {
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Magenta,
                    unfocusedBorderColor = Color.Magenta
                ),
                value = text.value,
                textStyle = TextStyle(Color.Black),
                singleLine = true,
                onValueChange = {
                    text.value = it
                },
                label = { Text(text = label, color = Color.Black) },
                modifier = Modifier,
                readOnly = true
            )
            DropDownList(
                requestToOpen = isOpen.value,
                list = countryList.value,
                openCloseOfDropDownList,
                userSelectedString
            )
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }
}



