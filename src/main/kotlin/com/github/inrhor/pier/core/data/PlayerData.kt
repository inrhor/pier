package com.github.inrhor.pier.core.data

import com.github.inrhor.pier.api.BoardFrame
import com.github.inrhor.pier.api.manager.BoardManager.getBoard
import com.github.inrhor.pier.util.sendBoard
import org.bukkit.entity.Player
import taboolib.common.platform.function.submit
import taboolib.common.platform.service.PlatformExecutor
import taboolib.module.nms.sendScoreboard

data class PlayerData(var select: String = "",
                      val boardMap: MutableMap<String, BoardData> = mutableMapOf()) {

    private var task: PlatformExecutor.PlatformTask? = null

    /**
     * @return 获取当前选择面板的数据
     */
    fun getSelectBoard(): BoardData? {
        return boardMap[select]
    }

    /**
     * 选择面板
     */
    fun updateSelect(player: Player, id: String = "") {
        select = id
        if (id.isEmpty()) {
            player.sendScoreboard()
        }else {
            val b = id.getBoard()?: return
            sendBoard(player, b)
        }
    }

    private fun sendBoard(player: Player, b: BoardFrame, index: Int = 0) {
        if (b.refresh > 0) {
            task?.cancel()
            task = submit(async = true, period = b.refresh.toLong()) {
                if (!player.isOnline) {
                    cancel();return@submit
                }
                player.sendBoard(b.getList(player, index))
            }
        }else player.sendBoard(b.getList(player, index))
    }

    /**
     * 滚动面板
     */
    fun scroll(player: Player, scrollType: ScrollType = ScrollType.DOWN) {
        val boardData = getSelectBoard()?: return
        val b = boardData.id.getBoard()?: return
        if (scrollType == ScrollType.DOWN) {
            if (b.getSize() - boardData.scroll < 16) return
            boardData.scroll++
        }else {
            if (boardData.scroll <= 0) {
                boardData.scroll = 0
                return
            }else boardData.scroll--
        }
        sendBoard(player, b, boardData.scroll)
    }

    enum class ScrollType {
        UP,DOWN
    }

}
