package com.github.inrhor.pier.util

import org.bukkit.entity.Player
import taboolib.module.nms.sendScoreboard

fun Player.sendBoard(list: List<String>) {
    sendScoreboard(*list.toTypedArray())
}