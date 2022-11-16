package cn.jiliapp.woodenfish.repository

import cn.jiliapp.woodenfish.api.APIService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryAppModule {

    @Provides
    fun provideUserRepository(apiService: APIService,gson: Gson): UserRepository {
        return  UserRepository(apiService,gson);
    }

}