package com.github.inrhor.pier.server

import com.github.inrhor.pier.api.manager.BoardManager
import com.github.inrhor.pier.api.manager.BoardManager.reloadAuto
import com.github.inrhor.pier.api.manager.DataManager
import org.bukkit.Bukkit

object Loader {

    fun load() {
        BoardManager.loadFile()
    }

    fun doReload() {
        Logger.icon()
        clear()
        load()
        Bukkit.getOnlinePlayers().forEach {
            it.reloadAuto()
        }
    }

    fun clear() {
        BoardManager.clear()
        DataManager.clear()
    }

}