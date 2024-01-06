package dev.sheldan.gw2.drr.models

data class CharacterInventory(
    val name: String,
    val bags: List<InventoryBag>
    )
{
}