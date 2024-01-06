package dev.sheldan.gw2.drr.models

import io.github.kryszak.gwatlin.api.items.model.item.ItemRarity

enum class DrrItemRarity {
    JUNK,
    BASIC,
    FINE,
    MASTERWORK,
    RARE,
    EXOTIC,
    ASCENDED,
    LEGENDARY;

    companion object {
        fun convertFromAPI(rarity: ItemRarity): DrrItemRarity = when (rarity) {
            ItemRarity.FINE -> FINE
            ItemRarity.MASTERWORK -> MASTERWORK
            ItemRarity.RARE -> RARE
            ItemRarity.EXOTIC -> EXOTIC
            ItemRarity.JUNK -> JUNK
            ItemRarity.BASIC -> BASIC
            ItemRarity.ASCENDED -> ASCENDED
            ItemRarity.LEGENDARY -> LEGENDARY
        }
    }

}