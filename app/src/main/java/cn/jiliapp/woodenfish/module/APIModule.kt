package cn.jiliapp.woodenfish.module

import cn.jiliapp.library.http.annotation.AuthRetrofit
import cn.jiliapp.library.http.annotation.BaseUrl
//import cn.jiliapp.library.http.annotation.NoAuthRetrofit
import cn.jiliapp.woodenfish.api.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class APIModule {


    @BaseUrl
    @Provides
    @Singleton
    fun baseUrl(): String {
        return "http://api.jiliapp.cn"
    }


    @Provides
    @Singleton
    fun provideAPIService(@AuthRetrofit retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java);
    }


}