package com.github.inrhor.pier.server

import com.github.inrhor.pier.Pier
import taboolib.common.platform.function.console
import taboolib.common.platform.function.info
import taboolib.module.lang.sendLang

object Logger {

    fun icon() {
        info("\n" +
                " ___                   \n" +
                "(  _`\\  _              \n" +
                "| |_) )(_)   __   _ __ \n" +
                "| ,__/'| | /'__`\\( '__)\n" +
                "| |    | |(  ___/| |   \n" +
                "(_)    (_)`\\____)(_)   \n")
    }

    fun outEnable() {
        icon()
        val d = description()
        console().sendLang("LOADER_INFO", d.name, d.version)
    }

    fun description() = Pier.plugin.description

}