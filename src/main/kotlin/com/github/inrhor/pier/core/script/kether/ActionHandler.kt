package com.github.inrhor.pier.core.script.kether

import cn.inrhor.questengine.api.manager.DataManager.trackingData
import com.github.inrhor.pier.api.BoardHook
import org.bukkit.entity.Player
import taboolib.common.platform.function.adaptPlayer
import taboolib.common.platform.function.console
import taboolib.common5.Coerce
import taboolib.module.chat.colored
import taboolib.module.kether.KetherShell
import taboolib.module.kether.printKetherErrorMessage

fun runEval(player: Player, script: String): Boolean {
    if (script.isEmpty()) return true
    return try {
        KetherShell.eval(script, sender = adaptPlayer(player), namespace = listOf("Pier", "QuestEngine")).thenApply {
            Coerce.toBoolean(it)
        }.getNow(true)
    } catch (ex: Throwable) {
        console().sendMessage("&cError Script: $script".colored())
        ex.printKetherErrorMessage()
        false
    }
}

fun textEval(player: Player, script: String, hook: BoardHook): String {
    if (script.isEmpty()) return ""
    return try {
        KetherShell.eval("inline '$script'", sender = adaptPlayer(player),
            namespace = listOf("Pier", "QuestEngine")) {
            if (hook == BoardHook.QUEST_ENGINE) {
                val a = player.trackingData()
                rootFrame().variables().set("@QenQuestID", a.questID)
                rootFrame().variables().set("@QenTargetID", a.targetID)
            }
        }.thenApply {
            Coerce.toString(it)
        }.getNow("")
    } catch (ex: Throwable) {
        ex.printKetherErrorMessage()
        ""
    }
}