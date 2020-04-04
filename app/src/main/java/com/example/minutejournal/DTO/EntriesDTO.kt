package com.example.minutejournal.DTO

data class EntriesDTO(var title: String, var text: String, var date: String) {
    override fun toString(): String {
        return "$title $text $date"
    }

}