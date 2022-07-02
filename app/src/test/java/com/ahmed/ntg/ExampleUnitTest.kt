package com.ahmed.ntg

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
fun main (){
    val x = mutableMapOf<String,Map<String,Double>>(
       "s" to mapOf("ADD" to 1.2),
        "x" to mapOf("x" to 0.900),
        "v" to mapOf("kkk" to 92.50142424) ,
        "k" to mapOf("mmm" to 1.2),
        "sss" to mapOf("ADD" to 1.2)
    )
    val xxx = ArrayList<Float>()

    val xx : List<Double> = x.values.filter { it.containsKey("x") }.flatMap { it.values }
    val xxxx = x.keys
    val from = 1f

    xxxx.zip(xx) { date, value ->
        xxx.add(String.format("%.2f", value).toFloat())


}
    val asd = 500.011594f

   println(xxx)
}