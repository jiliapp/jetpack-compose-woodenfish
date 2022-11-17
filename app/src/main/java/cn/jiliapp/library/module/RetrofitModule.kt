package cn.jiliapp.library.module

import cn.jiliapp.library.http.annotation.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @AuthRetrofit
    @Provides
    @Singleton
    fun provideAuthRetrofitClient(@AuthInterceptorOkHttpClient okHttpClient: OkHttpClient, @BaseUrl baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build();
    }


    @NoAuthRetrofit
    @Provides
    @Singleton
    fun provideRetrofitClient(@NoAuthInterceptorOkHttpClient okHttpClient: OkHttpClient, @BaseUrl baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build();
    }



}