package com.example.food_mvp.data.source.remote.fetchjson

import android.os.AsyncTask
import com.example.food_mvp.data.source.remote.OnFetchDataJsonListener
import org.json.JSONObject
import java.lang.Exception

class GetJsonFromUrl<T> constructor(
    private val keyEntity: String, private val listener: OnFetchDataJsonListener<T>
) : AsyncTask<String?, Void?, String?>() {
    private var exception: Exception? = null

    override fun doInBackground(vararg params: String?): String {
        var data = ""
        try {
            val parseDataWithJson = ParseDataWithJson()
            data = parseDataWithJson.getJsonFromUrl(params[0]).toString()
        } catch (e: Exception) {
            exception = e
        }
        return data
    }

    override fun onPostExecute(data: String?) {
        super.onPostExecute(data)
        if (data != null && !data.isBlank()) {
            val jsonObject = JSONObject(data)
            @Suppress()
            listener.onSuccess(
                data = ParseDataWithJson().parseJsonToData(
                    jsonObject,
                    keyEntity
                ) as T
            )
        } else listener.onError(exception)
    }
}
