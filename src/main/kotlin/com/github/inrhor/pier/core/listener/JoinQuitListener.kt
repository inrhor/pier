package com.github.inrhor.pier.core.listener

import com.github.inrhor.pier.api.manager.DataManager
import com.github.inrhor.pier.api.manager.BoardManager.reloadAuto
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent

object JoinQuitListener {

    @SubscribeEvent
    fun join(ev: PlayerJoinEvent) {
        ev.player.reloadAuto()
    }

    @SubscribeEvent
    fun quit(ev: PlayerQuitEvent) {
        DataManager.playerMap.remove(ev.player.uniqueId)
    }

}