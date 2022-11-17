package cn.jiliapp.library.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LibraryAppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return  Gson();
    }
}