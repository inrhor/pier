package com.github.inrhor.pier.util

fun String.toList(): List<String> {
    return split("\n").toList()
}