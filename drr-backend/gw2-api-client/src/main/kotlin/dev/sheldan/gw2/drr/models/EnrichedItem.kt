package dev.sheldan.gw2.drr.models

import io.github.kryszak.gwatlin.api.items.model.item.Item

data class EnrichedItem(
    val id: Int,
    val count: Int,
    val boundTo: String? = null,

    var iconUrl: String? = null,
    var name: String? = null,
    var description: String? = null,
    var type: DrrItemType? = null,
    var level: Int? = null,
    var rarity: DrrItemRarity? = null
) {
    fun setValuesFromItem(item: Item) {
        this.name = item.name
        this.level = item.level
        this.description = item.description
        this.type = DrrItemType.convertFromAPI(item.type)
        this.rarity = DrrItemRarity.convertFromAPI(item.rarity)
        this.iconUrl = item.icon
    }
}