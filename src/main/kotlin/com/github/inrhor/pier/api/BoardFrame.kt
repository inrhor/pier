package com.github.inrhor.pier.api

import com.github.inrhor.pier.api.manager.BoardManager.tags
import com.github.inrhor.pier.core.script.kether.textEval
import com.github.inrhor.pier.util.toList
import org.bukkit.entity.Player
import taboolib.module.chat.colored
import taboolib.platform.compat.replacePlaceholder

class BoardFrame(val id: String = "", val head: String = "", val content: String = "",
                 val mode: ModeAddon = ModeAddon(), val condition: String = "", val refresh: Int = 0) {

    fun getList(player: Player, from: Int = 0): List<String> {
        var max = 1
        val list = mutableListOf(tags[max]+ textEval(player, head.replacePlaceholder(player).colored()))
        val c = textEval(player, content.replacePlaceholder(player).colored()).toList()
        for (i in from until c.size) {
            if (max >= 28) break
            list.add(tags[max]+c[i])
            max++
        }
        return list
    }

    fun getSize() = content.toList().size

}