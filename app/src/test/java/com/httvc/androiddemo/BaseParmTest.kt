package com.httvc.androiddemo

import org.junit.Test

/**
 *Created by Android Studio
 *Project：AndroidDemo
 *Author：httvc
 *Email：jfjie2013@163.com
 *Date：2017/5/31.
 */
class BaseParmTest {
    //可变变量：
    var x = 1  // 自动推断出 `Int` 类型


    @Test
    @Throws(Exception::class)
    fun main() {

        //一次赋值（只读）的局部变量
        val a: Int = 1 //立即赋值
        val b = 2;    // 自动推断出 `Int` 类型
        val c: Int //如果没有初始值类型不能省略
        c = 3       // 明确赋值

        println(sum(a, b))
        println(by(a, b))
        printBy(a, c)
        printSum(a, c)

        println(x++)
        println(compare(a, b))
        println(compares(a, c))

        forLoop()

        whileLoop()

        println(whenDescribe(111111111111111))
    }


    //带有两个 Int 参数、返回 Int 的函数
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    //将表达式作为函数体、返回值类型自动推断的函数
    fun by(a: Int, b: Int) = a * b

    //函数返回无意义的值：
    fun printBy(a: Int, b: Int): Unit {
        println("by of $a and $b is ${a * b}")
    }

    //Unit 返回类型可以省略：
    fun printSum(a: Int, b: Int) {
        print("sum of $a and $b is ${a + b}")
    }

    //使用条件表达式
    fun compare(a: Int, b: Int): Int {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    //使用 if 作为表达式
    fun compares(a: Int, b: Int) = if (a > b) a else b

    fun forLoop() {
        //for循环
        val items = listOf("Apple", "Banana", "kiwi")
        for (item in items) {
            println(item)
        }

        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }
    }

    //while 循环
    fun whileLoop() {
        val items = listOf("apple", "orange", "banana")
        var index = 0
        while (index < items.size) {
            println("item at $index is ${items[index]}")
            index++
        }
    }

    fun whenDescribe(obj: Any): String =
            when (obj) {
                1 -> "One"
                "hello" -> "Greeting"
                is Long -> "Long"
                !is String -> "not a string"
                else -> "Unknown"
            }

    //

}