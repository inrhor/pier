package com.github.inrhor.pier.api.manager

import com.github.inrhor.pier.core.data.BoardData
import com.github.inrhor.pier.core.data.PlayerData
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.module.nms.sendScoreboard
import java.util.*

object DataManager {

    val playerMap: MutableMap<UUID, PlayerData> = mutableMapOf()

    private fun getPlayerData(uuid: UUID): PlayerData {
        if (!playerMap.containsKey(uuid)) {
            playerMap[uuid] = PlayerData()
        }
        return playerMap[uuid]!!
    }

    fun Player.getData() = getPlayerData(uniqueId)

    fun Player.addBoard(id: String) {
        val b = getData()
        if (!b.boardMap.containsKey(id) && BoardManager.boardMap.containsKey(id)) {
            b.boardMap[id] = BoardData(id)
        }
    }

    fun Player.removeBoard(id: String) {
        val b = getData()
        if (b.boardMap.containsKey(id)) {
            b.updateSelect(this)
        }
    }

    fun clear() {
        playerMap.keys.forEach {
            val p = Bukkit.getPlayer(it)
            if (p?.isOnline == true) {
                p.sendScoreboard()
            }
        }
    }

}