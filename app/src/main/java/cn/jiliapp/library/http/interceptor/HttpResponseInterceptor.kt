package cn.jiliapp.library.http.interceptor

import cn.jiliapp.library.http.HttpStatus
import cn.jiliapp.library.model.Rsp
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.net.ssl.HttpsURLConnection


/**
 * http拦截器
 */
class HttpResponseInterceptor(private val gson:Gson): Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val response = chain.proceed(chain.request())
        if (response.code == HttpsURLConnection.HTTP_OK) {
            return response
        }else if (response.code == HttpsURLConnection.HTTP_UNAUTHORIZED) {
            //1. 清除本地token
            //2. 跳转到登录页面或者自动登录
        }
        val rsp= Rsp(response.code,HttpStatus.getHttpDec(response.code),null);
        val rewriteBody: ResponseBody = gson.toJson(rsp).toResponseBody("application/json; charset=UTF-8".toMediaType());
        return response.newBuilder()
            .code(HttpsURLConnection.HTTP_OK)
            .body(rewriteBody)
            .build();
    }
}