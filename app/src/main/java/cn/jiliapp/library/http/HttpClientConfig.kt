package cn.jiliapp.library.http

class HttpClientConfig {
    companion object {
        const val HTTP_TIMEOUT = 10
        const val HTTP_CONNECT_TIMEOUT = 10
        const val HTTP_READ_TIMEOUT = 10
        const val HTTP_WRITE_TIMEOUT = 10
        const val HTTP_MAX_RETRY = 3
        const val HTTP_RETRY_WAIT_TIME = 1000
        const val HTTP_MAX_RETRY_WAIT_TIME = 5000
    }
}