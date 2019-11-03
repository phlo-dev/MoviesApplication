package com.pedro.data_remote.factory

import com.pedro.data_remote.API_KEY_QUERY
import com.pedro.data_remote.API_QUERY_VALUE
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val baseRequest = chain.request()

        val url = baseRequest.url
            .newBuilder()
            .addQueryParameter(API_KEY_QUERY, API_QUERY_VALUE)
            .build()

        val requestBuilder = baseRequest.newBuilder()
            .url(url)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }

}