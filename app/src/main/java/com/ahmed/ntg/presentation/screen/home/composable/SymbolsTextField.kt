package com.ahmed.ntg.presentation.screen.home.composable


import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope

@Composable
fun SymbolsTextField(
    label: String = "",
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    expanded: Boolean = false,
    selectedSymbol: String? = null,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Color.Magenta,
        unfocusedBorderColor = Color.Magenta
    ),
    onExpandedChange: () -> Unit
) {

    OutlinedTextField(
        value = if (selectedSymbol == null) "" else "${selectedSymbol}",
        textStyle = TextStyle(color = Color.Black),
        onValueChange = {},
        modifier = modifier
            .expandable(menuLabel = label, onExpandedChange = onExpandedChange) ,
        readOnly = true,
        isError = isError,
        label = {
            Text(text = label , color = Color.Black)
        },
        colors = colors,
        shape = shape,
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "Spinner arrow",
                tint = Color.Black,
                modifier = Modifier
                    .rotate(
                        if (expanded) 180f else 0f
                    )
            )
        }
    )
}

@Composable
fun Modifier.expandable(
    onExpandedChange: () -> Unit,
    menuLabel: String
) = pointerInput(Unit) {
    forEachGesture {
        coroutineScope {
            awaitPointerEventScope {
                var event: PointerEvent
                do {
                    event = awaitPointerEvent(PointerEventPass.Initial)
                } while (!event.changes.all {
                        it.changedToUp()
                    })
                onExpandedChange.invoke()
            }
        }
    }
}.semantics {
    contentDescription = menuLabel
    onClick {
        onExpandedChange()
        true
    }
}















