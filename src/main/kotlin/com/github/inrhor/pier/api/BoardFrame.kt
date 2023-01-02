package com.github.inrhor.pier.api

import cn.inrhor.questengine.api.manager.DataManager.questData
import cn.inrhor.questengine.api.manager.DataManager.trackingData
import cn.inrhor.questengine.common.quest.manager.QuestManager.getQuestFrame
import cn.inrhor.questengine.common.quest.manager.QuestManager.getTargetFrame
import com.github.inrhor.pier.api.manager.BoardManager.tags
import com.github.inrhor.pier.core.script.kether.textEval
import com.github.inrhor.pier.util.toList
import org.bukkit.entity.Player
import taboolib.module.chat.colored
import taboolib.platform.compat.replacePlaceholder

enum class BoardHook {
    NONE, QUEST_ENGINE
}

class BoardFrame(val id: String = "", val head: String = "", val content: String = "",
                 val mode: ModeAddon = ModeAddon(), val condition: String = "", val refresh: Int = 0,
        val hook: BoardHook = BoardHook.NONE) {

    fun getList(player: Player, from: Int = 0): List<String> {
        var max = 1
        val list = mutableListOf(tags[max]+ textEval(player, head.replacePlaceholder(player).colored(), hook))
        val qli = mutableListOf<String>()
        val tli = mutableListOf<String>()
        var cont = content
        if (hook == BoardHook.QUEST_ENGINE) {
            val track = player.trackingData()
            val questID = track.questID
            if (player.questData(questID) == null) {
                cont = cont.replace("{{quest", "NULL")
            }
            questID.getQuestFrame()?.note?.forEach {
                qli.add(it)
            }
            track.targetID.getTargetFrame(questID)?.description?.forEach {
                tli.add(it)
            }
            // list转string，并\nh换行
            cont = cont.replace("__QenQuestNote__", qli.joinToString("\n"))
            cont = cont.replace("__QenTargetNote__", tli.joinToString("\n"))
        }
        val c = textEval(player, cont.replacePlaceholder(player).colored(), hook).toList()
        for (i in from until c.size) {
            if (max >= 28) break
            list.add(tags[max]+c[i])
            max++
        }
        return list
    }

    fun getSize() = content.toList().size

}