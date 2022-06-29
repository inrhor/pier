package com.github.inrhor.pier.api

import com.github.inrhor.pier.api.manager.BoardManager.tags
import com.github.inrhor.pier.util.toList

class BoardFrame(val id: String = "", val head: String = "", val content: String = "",
                 val mode: ModeAddon = ModeAddon(), val condition: String = "") {

    fun getList(from: Int = 0): List<String> {
        var max = 1
        val list = mutableListOf(tags[max]+head)
        val c = content.toList()
        for (i in from until c.size) {
            if (max >= 28) break
            list.add(tags[max]+c[i])
            max++
        }
        return list
    }

    fun getSize() = content.toList().size

}