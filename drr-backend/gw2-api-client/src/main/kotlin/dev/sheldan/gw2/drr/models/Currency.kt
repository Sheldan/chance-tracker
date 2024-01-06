package dev.sheldan.gw2.drr.models

class Currency(
    val id: Int,
    val amount: Int,
    val name: String,
    val description: String,
    val iconUrl: String,
    val order: Int
) {
}