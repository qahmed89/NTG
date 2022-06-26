package com.ahmed.ntg

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
fun main (){
    val x = listOf(
        "ADD",
        "ASS",
        "GGZ",
        "KLS",
        "ADN"
    )
    val xxx = "ADD"

    val xx = x.filter { !it.contains(xxx) }
    println(xx)
}