package cn.jiliapp.woodenfish.api.module

import cn.jiliapp.library.http.annotation.AuthRetrofit
import cn.jiliapp.library.http.annotation.BaseUrl
//import cn.jiliapp.library.http.annotation.NoAuthRetrofit
import cn.jiliapp.woodenfish.api.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class APIModule {


    @BaseUrl
    @Provides
    fun baseUrl(): String {
        return "http://api.jiliapp.cn"
    }


    @Provides
    fun provideAPIService(@AuthRetrofit retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java);
    }


}