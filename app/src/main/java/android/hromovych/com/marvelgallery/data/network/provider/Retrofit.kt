package android.hromovych.com.marvelgallery.data.network.provider

import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofit by lazy {makeRetrofit()}

private fun makeRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("http://gateway.marvel.com/v1/public/")
    .client(makeHttpClient())
    .addConverterFactory(GsonConverterFactory.create(Gson()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

private fun makeHttpClient() = OkHttpClient.Builder()
    .connectTimeout(60, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .addInterceptor(makeHeadersInterceptor())
    .addInterceptor(makeAddSecurityQueryInterceptor())
    .addInterceptor(makeLoggingInterceptor())
    .build()