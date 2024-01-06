package dev.sheldan.gw2.drr.api

import dev.sheldan.gw2.drr.loader.InventoryLoader
import dev.sheldan.gw2.drr.models.AccountInventory
import dev.sheldan.gw2.drr.models.AccountInventoryView
import dev.sheldan.gw2.drr.models.CharacterInventory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.annotation.RequestScope

@RestController
@RequestScope
class InventoryController(@Autowired var inventoryLoader: InventoryLoader) {

    @GetMapping("/inventory/{name}")
    fun characterInventory(@PathVariable("name") characterName: String): CharacterInventory {
        return inventoryLoader.getFullCharacterInventory(characterName)
    }

    @GetMapping("/inventory")
    fun completeCharacterInventory(): AccountInventoryView {
        return inventoryLoader.getAccountInventory()
    }

    @GetMapping("/sharedInventory")
    fun accountInventory(): AccountInventory {
        return inventoryLoader.getSharedInventory()
    }

}