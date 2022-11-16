package cn.jiliapp.library.model;

class Rsp<T>(
        val code: Int,
        val msg: String,
        val data: T?
)