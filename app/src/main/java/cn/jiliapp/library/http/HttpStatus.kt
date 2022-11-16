package cn.jiliapp.library.http

import java.net.HttpURLConnection

enum class HttpStatus (val   code: Int,val desc:String) {
    //200-206
    HTTP_OK(HttpURLConnection.HTTP_OK, "请求成功"),
    HTTP_CREATED(HttpURLConnection.HTTP_CREATED, "请求已被接受，等待资源响应"),
    HTTP_ACCEPTED(HttpURLConnection.HTTP_ACCEPTED, "请求已被接受，但尚未处理"),
    HTTP_NOT_AUTHORITATIVE(HttpURLConnection.HTTP_NOT_AUTHORITATIVE, "请求已成功处理，结果来自第三方拷贝"),
    HTTP_NO_CONTENT(HttpURLConnection.HTTP_NO_CONTENT, "请求已成功处理，但无返回内容"),
    HTTP_RESET(HttpURLConnection.HTTP_RESET, "请求已成功处理，但需重置内容"),
    HTTP_PARTIAL(HttpURLConnection.HTTP_PARTIAL, "请求已成功处理，但仅返回了部分内容"),

    //3xx
    HTTP_MULT_CHOICE(HttpURLConnection.HTTP_MULT_CHOICE, "返回多条重定向供选择"),
    HTTP_MOVED_PERM(HttpURLConnection.HTTP_MOVED_PERM, "永久重定向"),
    HTTP_MOVED_TEMP(HttpURLConnection.HTTP_MOVED_TEMP, "临时重定向"),
    HTTP_SEE_OTHER(HttpURLConnection.HTTP_SEE_OTHER, " 当前请求的资源在其它地址"),
    HTTP_NOT_MODIFIED(HttpURLConnection.HTTP_NOT_MODIFIED, "请求资源与本地缓存相同，未修改"),
    HTTP_USE_PROXY(HttpURLConnection.HTTP_USE_PROXY, "必须通过代理访问"),



    //4xx
    HTTP_BAD_REQUEST(HttpURLConnection.HTTP_BAD_REQUEST, "请求错误"),
    HTTP_UNAUTHORIZED(HttpURLConnection.HTTP_UNAUTHORIZED, "需要身份认证验证,请重新登录"),
    HTTP_PAYMENT_REQUIRED(HttpURLConnection.HTTP_PAYMENT_REQUIRED, "需要付费访问"),
    HTTP_FORBIDDEN(HttpURLConnection.HTTP_FORBIDDEN, "禁止访问"),
    HTTP_NOT_FOUND(HttpURLConnection.HTTP_NOT_FOUND, "请求的内容未找到或已删除"),
    HTTP_BAD_METHOD(HttpURLConnection.HTTP_BAD_METHOD, "不允许的请求方法"),
    HTTP_NOT_ACCEPTABLE(HttpURLConnection.HTTP_NOT_ACCEPTABLE, "无法响应，因资源无法满足客户端条件"),
    HTTP_PROXY_AUTH(HttpURLConnection.HTTP_PROXY_AUTH, "要求通过代理的身份认证"),
    HTTP_CLIENT_TIMEOUT(HttpURLConnection.HTTP_CLIENT_TIMEOUT, "请求超时"),
    HTTP_CONFLICT(HttpURLConnection.HTTP_CONFLICT, "存在冲突"),
    HTTP_GONE(HttpURLConnection.HTTP_GONE, "资源已经不存在(过去存在)"),
    HTTP_LENGTH_REQUIRED(HttpURLConnection.HTTP_LENGTH_REQUIRED, "无法处理该请求"),
    HTTP_PRECON_FAILED(HttpURLConnection.HTTP_PRECON_FAILED, "请求条件错误"),
    HTTP_ENTITY_TOO_LARGE(HttpURLConnection.HTTP_ENTITY_TOO_LARGE, "请求的实体过大"),
    HTTP_REQ_TOO_LONG(HttpURLConnection.HTTP_REQ_TOO_LONG, "请求的URI过长"),
    HTTP_UNSUPPORTED_TYPE(HttpURLConnection.HTTP_UNSUPPORTED_TYPE, "无法处理的媒体格式"),


    HTTP_INTERNAL_ERROR(HttpURLConnection.HTTP_INTERNAL_ERROR, "服务器端程序错误，服务器不能完成请求。"),
    HTTP_NOT_IMPLEMENTED(HttpURLConnection.HTTP_NOT_IMPLEMENTED, "服务器不支持的请求方法"),
    HTTP_BAD_GATEWAY(HttpURLConnection.HTTP_BAD_GATEWAY, "网关无响应"),
    HTTP_UNAVAILABLE(HttpURLConnection.HTTP_UNAVAILABLE, "服务器端临时错误"),
    HTTP_GATEWAY_TIMEOUT(HttpURLConnection.HTTP_GATEWAY_TIMEOUT, "网关超时"),
    HTTP_VERSION(HttpURLConnection.HTTP_VERSION, "服务器不支持的HTTP版本")
;


    companion object {

        fun getHttpDec(code: Int): String {
           val  desc=HttpStatus.values().find { it->it.code==code }?.desc
            return desc ?: ("code=$code Http Status Error")
        }
    }
}