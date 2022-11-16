package cn.jiliapp.woodenfish.repository

import cn.jiliapp.library.model.Rsp
import cn.jiliapp.woodenfish.api.APIService
import cn.jiliapp.woodenfish.model.AuthVO
import cn.jiliapp.woodenfish.model.KcAuthVO
import cn.jiliapp.woodenfish.model.KcLoginDTO
import cn.jiliapp.woodenfish.model.LoginDTO
import com.google.gson.Gson
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

class UserRepository (private val apiService: APIService,private val  gson:Gson){

    suspend fun login(loginDTO: LoginDTO):Rsp<AuthVO?>{
        //其他可能需要查询本地数据库等操作
        return apiService.auth(loginDTO);
    }


   suspend fun kcLogin(loginDTO: KcLoginDTO) : KcAuthVO?{
        //其他可能需要查询本地数据库等操作
        //参数转换
       val loginJson= gson.toJson(loginDTO);
       val loginMap: MutableMap<String, kotlin.Any> = gson.fromJson(loginJson, MutableMap::class.java) as MutableMap<String, kotlin.Any>
       return apiService.kcAuth(loginMap);
    }


}
