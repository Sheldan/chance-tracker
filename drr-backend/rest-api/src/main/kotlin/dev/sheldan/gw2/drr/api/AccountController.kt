package dev.sheldan.gw2.drr.api

import dev.sheldan.gw2.drr.loader.AccountLoader
import dev.sheldan.gw2.drr.models.AccountMaterialView
import dev.sheldan.gw2.drr.models.AccountVaultView
import dev.sheldan.gw2.drr.models.AccountWalletView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.annotation.RequestScope

@RestController
@RequestScope
class AccountController(@Autowired var accountLoader: AccountLoader) {
    @GetMapping("/characters")
    fun inventory(): List<String>? {
        return accountLoader.getCharacters()
    }

    @GetMapping("/wallet")
    fun wallet(): AccountWalletView {
        return accountLoader.getWallet()
    }

    @GetMapping("/bank")
    fun bank(): AccountVaultView {
        return accountLoader.getBank()
    }

    @GetMapping("/materials")
    fun materials(): AccountMaterialView {
        return accountLoader.getMaterials()
    }
}