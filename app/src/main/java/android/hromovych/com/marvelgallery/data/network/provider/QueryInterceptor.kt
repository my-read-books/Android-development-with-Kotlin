package android.hromovych.com.marvelgallery.data.network.provider

import android.hromovych.com.marvelgallery.BuildConfig
import okhttp3.Interceptor

fun makeAddSecurityQueryInterceptor() = Interceptor{chain ->
    val originalRequest = chain.request()
    val timeStamp = System.currentTimeMillis()

    val url = originalRequest.url.newBuilder()
        .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
        .addQueryParameter("ts", "$timeStamp")
        .addQueryParameter("hash", calculateMd5(timeStamp.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY))
        .build()

    val request = originalRequest
        .newBuilder()
        .url(url)
        .build()

    chain.proceed(request)
}