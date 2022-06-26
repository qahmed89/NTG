package com.ahmed.ntg.data.remote.dto

import com.ahmed.ntg.domain.model.Symbols

data class SymbolsDto(
    val success: Boolean,
    val symbols: Map<String, String>
)


fun SymbolsDto.toSymbols(): Symbols {
    return Symbols(
        success = success,
        symbols = symbols.keys.toList()
    )
}