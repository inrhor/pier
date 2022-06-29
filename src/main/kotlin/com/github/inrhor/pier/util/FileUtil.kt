package com.github.inrhor.pier.util

import com.github.inrhor.pier.Pier
import java.io.File

/**
 * 返回文件夹的内容
 */
fun getFile(child: String, say: String, mkdirs: Boolean = true): File {
    val file = File(Pier.plugin.dataFolder, child)
    if (!file.exists() && mkdirs) { // 如果 <child> 文件夹不存在就给示例配置
        Pier.resource.releaseResourceFile(child+"example.yml", true)
    }
    return file
}

fun File.getList(): List<File> =
    mutableListOf<File>().let { files ->
        if (isDirectory) {
            listFiles()!!.forEach { files.addAll(it.getList()) }
        }else if (name.endsWith(".yml", true)) {
            files.add(this)
        }
        return@let files
    }