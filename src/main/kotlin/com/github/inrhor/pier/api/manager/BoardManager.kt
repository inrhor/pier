package com.github.inrhor.pier.api.manager

import com.github.inrhor.pier.api.BoardFrame
import com.github.inrhor.pier.api.manager.DataManager.addBoard
import com.github.inrhor.pier.api.manager.DataManager.getData
import com.github.inrhor.pier.core.data.PlayerData
import com.github.inrhor.pier.core.script.kether.runEval
import com.github.inrhor.pier.util.getFile
import com.github.inrhor.pier.util.getList
import org.bukkit.entity.Player
import taboolib.module.configuration.Configuration
import taboolib.module.configuration.Configuration.Companion.getObject
import taboolib.module.nms.NMSScoreboardImpl

object BoardManager {

    val boardMap: MutableMap<String, BoardFrame> = mutableMapOf()

    val autoBoard: MutableSet<String> = mutableSetOf()

    val tags = NMSScoreboardImpl().uniqueColors

    fun BoardFrame.regBoard() {
        boardMap[id] = this
        if (mode.auto) {
            autoBoard.add(id)
        }
    }

    fun String.getBoard(): BoardFrame? {
        return boardMap[this]
    }

    fun clear() {
        boardMap.clear()
        autoBoard.clear()
    }

    fun loadFile() {
        val folder = getFile("template/", "")
        folder.getList().forEach {
            val yaml = Configuration.loadFromFile(it)
            yaml.getObject<BoardFrame>("board", false).regBoard()
        }
        autoBoard.sortedByDescending {
            it.getBoard()!!.mode.priority
        }
    }

    fun Player.reloadAuto() {
        if (!DataManager.playerMap.containsKey(uniqueId)) DataManager.playerMap[uniqueId] = PlayerData()
        val l = autoBoard
        for (i in l) {
            val b = i.getBoard()?: continue
            if (!runEval(this, b.condition)) continue
            addBoard(i)
            getData().updateSelect(this, i)
            return
        }
    }

}