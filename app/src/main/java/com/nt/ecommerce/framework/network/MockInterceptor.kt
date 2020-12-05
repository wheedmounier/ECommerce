package com.nt.ecommerce.framework.network

import com.nt.ecommerce.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONArray

class MockInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val newUrl = "https://www.google.com"

        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.contains("search") -> productsJsonArray.toString()
                else -> ""
            }
            requestBuilder.url(newUrl);
            val response = chain.proceed(requestBuilder.build())
            response.newBuilder().body(
                responseString.toByteArray().toResponseBody("application/json".toMediaTypeOrNull())
            )
            return response

        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }

}



const val getListOfProductsJson = """{[
    "somestring1",
    "somestring2",
    "somestring3",
    "somestring4",
    "somestring5",
    "somestring6",
    "somestring7",
    "somestring8",
    "somestring9",
    "somestring10",
    "somestring11",
    "somestring12",
    "somestring13",
    "somestring14",
    "somestring15",
    "somestring16",
    "somestring17",
    "somestring18",
    "somestring19",
    "somestring20",
    "somestring21",
    "somestring22",
    "somestring23",
    "somestring24",
    "somestring25",
    "somestring26",
    "somestring27",
    "somestring28",
    "somestring29",
    "somestring30",
    "somestring31",
    "somestring32",
    "somestring33",
    "somestring34",
    "somestring35",
    "somestring36",
    "somestring37",
    "somestring38",
    "somestring39",
    "somestring40",
    "somestring41",
    "somestring42",
    "somestring43",
    "somestring44"]}
"""

val productsJsonArray = JSONArray(listOf("somestring1",
    "somestring2",
    "somestring3",
    "somestring4",
    "somestring5",
    "somestring6",
    "somestring7",
    "somestring8",
    "somestring9",
    "somestring10",
    "somestring11",
    "somestring12",
    "somestring13",
    "somestring14",
    "somestring15",
    "somestring16",
    "somestring17",
    "somestring18",
    "somestring19",
    "somestring20",
    "somestring21",
    "somestring22",
    "somestring23",
    "somestring24",
    "somestring25",
    "somestring26",
    "somestring27",
    "somestring28",
    "somestring29",
    "somestring30",
    "somestring31",
    "somestring32",
    "somestring33",
    "somestring34",
    "somestring35",
    "somestring36",
    "somestring37",
    "somestring38",
    "somestring39",
    "somestring40",
    "somestring41",
    "somestring42",
    "somestring43",
    "somestring44"))
