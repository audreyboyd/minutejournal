package com.example.minutejournal.ui.main

import java.time.LocalDate

data class EntriesDTO(var title: String, var text: String, var date: LocalDate) {
    override fun toString(): String {
        return "$title $date $text"
    }
}