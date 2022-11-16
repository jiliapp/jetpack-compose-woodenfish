package cn.jiliapp.woodenfish.model


class LoginDTO(
    val userName: String,
    val password: String,
    val clientId: String,
    val clientSecret: String,
    val grantType: String,
    val realm: String,
    )