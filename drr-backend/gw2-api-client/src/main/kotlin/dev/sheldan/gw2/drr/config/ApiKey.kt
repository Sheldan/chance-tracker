package dev.sheldan.gw2.drr.config

interface ApiKey {
    fun getApiKey(): String
    fun setApiKey(key: String)
}