package com.github.inrhor.pier

import com.github.inrhor.pier.server.Loader
import com.github.inrhor.pier.server.Logger
import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.platform.BukkitIO
import taboolib.platform.BukkitPlugin

/**
 * 入口
 */
object Pier : Plugin() {

    override fun onEnable() {
        Logger.outEnable()
        Loader.load()
    }

    @Config(migrate = true)
    lateinit var config: Configuration
        private set

    val plugin by lazy {
        BukkitPlugin.getInstance()
    }

    val resource by lazy {
        BukkitIO()
    }

}