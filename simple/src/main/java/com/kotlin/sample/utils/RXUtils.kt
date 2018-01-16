package com.kotlin.sample.utils

import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Response

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import java.io.File

/**
 * Created by anna on 2017/12/21.
 */
object RXUtils {

    fun downloadZipFiles(zipFileName: List<String>?, downloadURL: String) {
        if (null != zipFileName && zipFileName.isNotEmpty()) {
            val zipFileCount = zipFileName.size
            val result = Array(zipFileCount, { ObservableSource::class.java }) as Array<ObservableSource<RequestZipFileResult>>
            for (i in 0 until zipFileCount) {
                result[i] = requestZipFile(zipFileName[i], downloadURL)
            }

            Observable.merge(result.asIterable())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<RequestZipFileResult> {

                        internal var successFlag = 0

                        override fun onSubscribe(d: Disposable) {}

                        override fun onNext(requestZipFileResult: RequestZipFileResult) {
                            successFlag++
                            if (zipFileCount == successFlag) {
                            }
                        }

                        override fun onError(t: Throwable) {}

                        override fun onComplete() {}
                    })
        }
    }

    private fun requestZipFile(zipFileName: String, downloadURL: String): ObservableSource<RequestZipFileResult> {
        return Observable.create({ subscriber ->
            val zipFileURL = downloadURL + File.separator + zipFileName
            HttpUtils.downloadFile(zipFileURL, object : FileCallback("filePath", zipFileName) {
                override fun onSuccess(response: Response<File>) {
                    val requestZipFileResult = RequestZipFileResult()
                    requestZipFileResult.isSuccess = true
                    subscriber.onNext(requestZipFileResult)
                }
            }, zipFileName)
        })
    }

    private class RequestZipFileResult {
        var isSuccess: Boolean = false
    }
}