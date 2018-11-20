package com.kotlin.sample.utils

import android.graphics.Bitmap
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.BitmapCallback
import com.lzy.okgo.callback.FileCallback
import java.io.File


/**
 * Created by anna on 2017/12/21.
 */
object OkGoUtils {

    fun getRequest(url: String, callback: StringCallback, tag: Object) {
        OkGo.get<String>(url).tag(tag).execute(callback)
    }

    fun postRequest(url: String, params: Map<String, String>, callback: StringCallback, tag: Object) {
        OkGo.post<String>(url).params(params).tag(tag).execute(callback)
    }

    fun uploadFile(url: String, fileKey: String, file: File, fileName: String, callback: StringCallback, tag: Object) {
        OkGo.post<String>(url).params(fileKey, file, fileName).tag(tag).execute(callback)
    }

    fun uploadMultiFile(url: String, fileKey: String, params: List<File>, callback: StringCallback, tag: Object) {
        OkGo.post<String>(url).addFileParams(fileKey, params).tag(tag).execute(callback)
    }

    fun uploadMultiFile(url: String, params: Map<String, File>, callback: StringCallback, tag: Object) {
        val postRequest = OkGo.post<String>(url).tag(tag)
        if (null != params) {
            for (fileKey in params.keys) {
                postRequest.params(fileKey, params[fileKey])
            }
        }
        postRequest.execute(callback)
    }

    fun downloadFile(url: String, callback: FileCallback, tag: String) {
        OkGo.get<File>(url).tag(tag).execute(callback)
    }

    fun downloadBitmap(url: String, callback: BitmapCallback, tag: Object) {
        OkGo.get<Bitmap>(url).tag(tag).execute(callback)
    }

    fun cancelTag(tag: Object) {
        OkGo.getInstance().cancelTag(tag)
    }

    fun cancelAll() {
        OkGo.getInstance().cancelAll()
    }
}