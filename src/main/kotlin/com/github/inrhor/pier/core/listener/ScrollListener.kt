package com.github.inrhor.pier.core.listener

import com.github.inrhor.pier.core.data.PlayerData
import com.github.inrhor.pier.api.manager.DataManager.getData
import org.bukkit.event.player.PlayerItemHeldEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent

object ScrollListener {

    @SubscribeEvent(EventPriority.HIGHEST, ignoreCancelled = true)
    fun scroll(ev: PlayerItemHeldEvent) {
        val p = ev.player
        val pData = p.getData()
        val newSlot = ev.newSlot
        val oldSlot = ev.previousSlot
        if (newSlot == oldSlot - 1 || oldSlot == 0 && newSlot == 8) {
            pData.scroll(p, PlayerData.ScrollType.UP)
        }else if (newSlot == oldSlot + 1 || oldSlot == 8 && newSlot == 0) {
            pData.scroll(p)
        }
    }

}