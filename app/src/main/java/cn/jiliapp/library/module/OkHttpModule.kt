package cn.jiliapp.library.module

import androidx.datastore.preferences.protobuf.Duration
import cn.jiliapp.library.http.HttpClientConfig
import cn.jiliapp.library.http.annotation.AuthInterceptorOkHttpClient
import cn.jiliapp.library.http.annotation.NoAuthInterceptorOkHttpClient
import cn.jiliapp.library.http.interceptor.HttpRequestAuthInterceptor
import cn.jiliapp.library.http.interceptor.HttpResponseInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OkHttpModule {

    @AuthInterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideAuthInterceptorOkHttpClient(
          gson: Gson): OkHttpClient {
        return OkHttpClient.Builder()
            //.connectTimeout()
            .addInterceptor(HttpRequestAuthInterceptor())
            .addInterceptor(HttpResponseInterceptor(gson))
            .addNetworkInterceptor(HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()
    }

    @NoAuthInterceptorOkHttpClient
    @Provides
    @Singleton
    fun provideNoAuthInterceptorOkHttpClient(
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }
}