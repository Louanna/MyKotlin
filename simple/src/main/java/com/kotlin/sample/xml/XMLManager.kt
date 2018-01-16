package com.kotlin.sample.xml

import android.content.res.XmlResourceParser
import android.content.Context
import com.kotlin.sample.R
import org.xmlpull.v1.XmlPullParserException
import java.io.IOException

/**
 * Created by anna on 16/01/2018.
 */
class XMLManager {

    fun getData(context: Context): List<Map<String, String>> {
        val list = ArrayList<Map<String, String>>()

        //XML Stream
//        val inputStream = ByteArrayInputStream(xmlData.toByteArray(charset("UTF-8")))
//        val xrp = Xml.newPullParser()
//        xrp.setInput(inputStream, "UTF-8")

        //XML File
        val xrp = context.getResources().getXml(R.xml.channels)
        try {
            while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
                if (xrp.getEventType() == XmlResourceParser.START_TAG) {
                    val tagName = xrp.getName()
                    if (tagName == "item") {
                        val map = HashMap<String, String>()
                        val id = xrp.getAttributeValue(null, "id")
                        map.put("id", id)
                        val url = xrp.getAttributeValue(1)
                        map.put("url", url)
                        map.put("name", xrp.nextText())
                        list.add(map)
                    }
                }
                xrp.next()
            }
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return list
    }
}