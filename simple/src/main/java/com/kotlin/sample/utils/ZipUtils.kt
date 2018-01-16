package com.kotlin.sample.utils

import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

/**
 * Created by anna on 16/01/2018.
 */
object ZipUtils {

    fun unzipFile(zipFilePath: String, unzipFilePath: String, matches: Array<String>): Boolean {
        var result = true
        var inZip: ZipInputStream? = null
        try {
            inZip = ZipInputStream(FileInputStream(zipFilePath))
            do {
                val zipEntry = inZip.nextEntry
                var entryName = zipEntry.name

                var isMatch = false
                for (text in matches) {
                    if (entryName.toLowerCase().contains(text.toLowerCase())) {
                        isMatch = true
                        break
                    }
                }

                if (!isMatch) {
                    continue
                }

                if (zipEntry.isDirectory) {
                    // get the folder name of the widget
                    entryName = entryName.substring(0, entryName.length - 1)
                    val folder = File(unzipFilePath + File.separator + entryName)
                    folder.mkdirs()
                } else {
                    if (entryName.contains("/")) {
                        val strings = entryName.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        if (strings.size > 1) {
                            entryName = strings[strings.size - 1]
                        }
                    }
                    val file = File(unzipFilePath + File.separator + entryName)
                    file.createNewFile()
                    val out = FileOutputStream(file)
                    val buffer = ByteArray(1024)
                    do {
                        var len = inZip.read(buffer)
                        out.write(buffer, 0, len)
                        out.flush()
                    } while (len > 0)
                    out.close()
                }

            } while (null != zipEntry)

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            result = false
        } catch (e: IOException) {
            e.printStackTrace()
            result = false
        } finally {
            if (null != inZip) {
                inZip.close()
            }
        }
        return result
    }

    fun zipFiles(srcFiles: List<File>, zipFilePath: String): Boolean {
        var result = true
        val buffer = ByteArray(1024)
        try {
            val fos = FileOutputStream(zipFilePath)
            val zos = ZipOutputStream(fos)
            for (file in srcFiles) {
                zos.putNextEntry(ZipEntry(file.name))
                val inputStream = FileInputStream(file.path)
                do {
                    var len = inputStream.read(buffer)
                    zos.write(buffer, 0, len)
                } while (len > 0)
                inputStream.close()
            }
            zos.closeEntry()
            zos.close()
            fos.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
            result = false
        }
        return result
    }
}