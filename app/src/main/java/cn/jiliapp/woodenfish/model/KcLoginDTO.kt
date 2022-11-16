package cn.jiliapp.woodenfish.model

import com.google.gson.annotations.SerializedName


class KcLoginDTO(
    @SerializedName("username")
    val userName: String,
    val password: String,
    val scope: String,
    @SerializedName("grant_type")
    val grantType: String,
    @SerializedName("client_id")
    val clientId: String,
    @SerializedName("client_secret")
    val clientSecret: String,

)