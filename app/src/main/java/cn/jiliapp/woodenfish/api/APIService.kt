package cn.jiliapp.woodenfish.api

import cn.jiliapp.library.model.Rsp
import cn.jiliapp.woodenfish.model.AuthVO
import cn.jiliapp.woodenfish.model.KcAuthVO
import cn.jiliapp.woodenfish.model.LoginDTO
import retrofit2.http.*

/**
 * 远程api
 */
interface  APIService{

    @POST("/user/v1/oauth2/token")
    suspend fun auth(@Body loginDTO: LoginDTO): Rsp<AuthVO?>


    @POST("http://keycloak.jiliapp.cn/realms/platform/protocol/openid-connect/token")
    @FormUrlEncoded()
    suspend fun kcAuth(@FieldMap loginDTO: MutableMap<String, kotlin.Any>): KcAuthVO



}