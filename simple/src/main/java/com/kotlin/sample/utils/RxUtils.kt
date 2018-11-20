package com.kotlin.sample.utils

import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import java.io.File

import com.lzy.okgo.OkGo
import com.lzy.okgo.model.HttpHeaders
import com.lzy.okgo.callback.FileCallback
import com.lzy.okgo.model.Response

import com.lzy.okgo.convert.FileConvert
import android.graphics.Bitmap
import com.lzy.okgo.convert.BitmapConvert
import com.lzy.okgo.convert.StringConvert
import com.lzy.okrx2.adapter.ObservableResponse

/**
 * Created by anna on 2017/12/21.
 */
object RxUtils {

//    fun getString(header: String, param: String) {
//
//        val headers = HttpHeaders()
//        headers.put("aaa", header)
//        val params = com.lzy.okgo.model.HttpParams()
//        params.put("bbb", param)
//
//        getString(header)
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe {
//                    //            showLoading()
//                }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(Observer<Response<String>> {
//
//                })
//
//    }

    fun getString(url: String): Observable<Response<String>> {
        return OkGo.get<String>(url)
                .converter(StringConvert())
                .adapt(ObservableResponse<String>())
    }

    fun getString(url: String, headers: HttpHeaders, params: com.lzy.okgo.model.HttpParams): Observable<Response<String>> {
        return OkGo.get<String>(url)
                .headers(headers)
                .params(params)
                .converter(StringConvert())
                .adapt(ObservableResponse<String>())
    }

    fun getBitmap(url: String): Observable<Response<Bitmap>> {
        return OkGo.post<Bitmap>(url)
                .converter(BitmapConvert())
                .adapt(ObservableResponse())
    }

    fun getFile(url: String): Observable<Response<File>> {
        return OkGo.get<File>(url)
                .converter(FileConvert())
                .adapt(ObservableResponse())
    }

    fun downloadZipFiles(zipFileName: List<String>?, downloadURL: String) {
        if (null != zipFileName && zipFileName.isNotEmpty()) {
            val zipFileCount = zipFileName.size
            val result = Array(zipFileCount) {
                ObservableSource::class.java } as Array<ObservableSource<RequestZipFileResult>>
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
        return Observable.create { subscriber ->
            val zipFileURL = downloadURL + File.separator + zipFileName
            OkGoUtils.downloadFile(zipFileURL, object : FileCallback("filePath", zipFileName) {
                override fun onSuccess(response: Response<File>) {
                    val requestZipFileResult = RequestZipFileResult()
                    requestZipFileResult.isSuccess = true
                    subscriber.onNext(requestZipFileResult)
                }
            }, zipFileName)
        }
    }

    private class RequestZipFileResult {
        var isSuccess: Boolean = false
    }
}