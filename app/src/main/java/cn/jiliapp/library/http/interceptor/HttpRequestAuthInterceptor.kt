package cn.jiliapp.library.http.interceptor

import cn.jiliapp.library.http.Constants
import okhttp3.Interceptor
import okhttp3.Response

/**
 * http请求添加token
 */
class HttpRequestAuthInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var bearerToken=null;
        if (bearerToken!=null){
            var  token= Constants.bearerPrefix + "bearerToken";
            var request=chain.request()
            var newRequest=request.newBuilder()
                .addHeader(Constants.authorization,token)
                .build()
        }
        return chain.proceed(chain.request())
    }
}