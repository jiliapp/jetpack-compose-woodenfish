package cn.jiliapp.woodenfish.model

import com.google.gson.annotations.SerializedName

class AuthVO(
     val accessToken: String,
     val expiresIn: Int,
     val refreshExpiresIn: Int,
     val refreshToken: String,
     val tokenType: String,
     val idToken: String,
     val notBeforePolicy: Int,
     val sessionState: String,
     val scope: String,
)
