package com.pedro.data_remote.factory

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

object ServiceClientFactory {

    inline fun <reified T> createClient(
        url: String, okHttpClient: OkHttpClient?, coroutineAdapter: CoroutineCallAdapterFactory
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addClient(okHttpClient)
            .addConverterFactory(UnitConverterFactory)
            .addCallAdapterFactory(coroutineAdapter)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(T::class.java)
    }

    fun Retrofit.Builder.addClient(okHttpClient: OkHttpClient?): Retrofit.Builder =
        when (okHttpClient != null) {
            true -> client(okHttpClient)
            else -> this
        }

    fun createOkHttpClient(
        requestInterceptor: RequestInterceptor, httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        with(clientBuilder) {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(requestInterceptor)

            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }

        return clientBuilder.build()
    }

    fun createHttpLoggingInterceptor() = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        this
    }

    object UnitConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type, annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *>? {
            return if (type == Unit::class.java) UnitConverter else null
        }

        private object UnitConverter : Converter<ResponseBody, Unit> {
            override fun convert(value: ResponseBody) {
                value.close()
            }
        }
    }

}