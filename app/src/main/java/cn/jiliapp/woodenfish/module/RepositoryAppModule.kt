package cn.jiliapp.woodenfish.module

import cn.jiliapp.woodenfish.api.APIService
import cn.jiliapp.woodenfish.repository.DatastoreExtend
import cn.jiliapp.woodenfish.repository.DatastoreRepository
import cn.jiliapp.woodenfish.repository.UserRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryAppModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiService: APIService,gson: Gson): UserRepository {
        return  UserRepository(apiService,gson);
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(datastoreExtend: DatastoreExtend): DatastoreRepository {
        return  DatastoreRepository(datastoreExtend);
    }


}