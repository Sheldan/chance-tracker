package dev.sheldan.gw2.drr.models

import io.github.kryszak.gwatlin.api.items.model.item.ItemType

enum class DrrItemType {
    OPENABLE,
    DIFFERENT;

    companion object {
        fun convertFromAPI(type: ItemType): DrrItemType = when (type) {
            ItemType.CONTAINER -> OPENABLE
            ItemType.ARMOR -> DIFFERENT
            ItemType.BACK -> DIFFERENT
            ItemType.BAG -> DIFFERENT
            ItemType.CONSUMABLE -> DIFFERENT
            ItemType.CRAFTING_MATERIAL -> DIFFERENT
            ItemType.GATHERING -> DIFFERENT
            ItemType.GIZMO -> DIFFERENT
            ItemType.JADE_TECH_MODULE -> DIFFERENT
            ItemType.KEY -> DIFFERENT
            ItemType.MINI_PET -> DIFFERENT
            ItemType.POWER_CORE -> DIFFERENT
            ItemType.TOOL -> DIFFERENT
            ItemType.TRAIT -> DIFFERENT
            ItemType.TRINKET -> DIFFERENT
            ItemType.TROPHY -> DIFFERENT
            ItemType.UPGRADE_COMPONENT -> DIFFERENT
            ItemType.WEAPON -> DIFFERENT
            ItemType.RELIC -> DIFFERENT
        }
    }

}

