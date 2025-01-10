package com.example.expensetracker.commonClass

class CommonClass {

    private val lightHexColors = arrayListOf(
        "#FFB3B3",
        "#FFCC99",
        "#FFFF99",
        "#99FF99",
        "#99CCFF",
        "#FF99FF",
        "#FFCCCC",
        "#CCFFCC",
        "#B3B3FF",
        "#FFD700",
        "#B4E7FF",
        "#E6E6FA",
        "#F1F1F1",
        "#D9D9D9",
        "#FFFAF0",
        "#E6FFE6",
        "#E0FFFF",
        "#FFEBE8",
        "#F0F8FF",
        "#FFF5EE",
        "#F0FFF0",
        "#D3D3D3",
        "#E0E0E0",
        "#F4A300",
        "#D5E1DF",
        "#F9E4B7",
        "#C6F2E0",
        "#B7C3F3",
        "#FAD02E",
        "#D2B3F3"
    )

    fun getChartColors(size: Int): List<String> {
        if (size <= 0) return emptyList()
        return lightHexColors.take(size)
    }
}
