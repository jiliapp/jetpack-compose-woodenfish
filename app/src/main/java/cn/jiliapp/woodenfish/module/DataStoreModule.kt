package cn.jiliapp.woodenfish.module

import android.content.Context
import cn.jiliapp.library.http.annotation.AuthRetrofit
import cn.jiliapp.library.http.annotation.BaseUrl
//import cn.jiliapp.library.http.annotation.NoAuthRetrofit
import cn.jiliapp.woodenfish.api.APIService
import cn.jiliapp.woodenfish.repository.DatastoreExtend
import cn.jiliapp.woodenfish.repository.DatastoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {


   /* @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DatastoreRepository {
        return DatastoreExtend(context)
    }*/

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) = DatastoreExtend(context)


}