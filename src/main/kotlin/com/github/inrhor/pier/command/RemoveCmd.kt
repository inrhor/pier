package com.github.inrhor.pier.command

import com.github.inrhor.pier.api.manager.BoardManager
import com.github.inrhor.pier.api.manager.DataManager.removeBoard
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.subCommand

object RemoveCmd {

    @CommandBody
    val remove = subCommand {
        dynamic {
            suggestion<ProxyCommandSender> { _, _ ->
                Bukkit.getOnlinePlayers().map { it.name }
            }
            dynamic {
                suggestion<ProxyCommandSender> { _, _ ->
                    BoardManager.boardMap.keys.map { it }
                }
                execute<ProxyPlayer> { sender, _, argument ->
                    val p = sender.cast<Player>()
                    p.removeBoard(argument.split(" ")[0])
                }
            }
        }
    }

}