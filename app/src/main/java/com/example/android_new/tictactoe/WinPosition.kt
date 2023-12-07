package com.example.android_new.tictactoe

sealed class WinPosition(val places: List<Int>) {
    data object H1 : WinPosition(listOf(1, 2, 3))
    data object H2 : WinPosition(listOf(4, 5, 6))
    data object H3 : WinPosition(listOf(7, 8, 9))

    data object V1 : WinPosition(listOf(1, 4, 7))
    data object V2 : WinPosition(listOf(2, 5, 8))
    data object V3 : WinPosition(listOf(3, 6, 9))

    data object D1 : WinPosition(listOf(1, 5, 9))
    data object D2 : WinPosition(listOf(3, 5, 7))
}
