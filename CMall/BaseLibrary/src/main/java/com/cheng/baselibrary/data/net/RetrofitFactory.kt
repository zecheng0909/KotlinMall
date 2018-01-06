package com.cheng.baselibrary.data.net

import com.cheng.baselibrary.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * User: Cheng
 * Date: 2018-01-03
 * Time: 20:27
 * Describe: Retrofit构建工厂
 */

class RetrofitFactory private constructor() {

    private val retrofit: Retrofit

    companion object {

        val retrofitFactory: RetrofitFactory by lazy { RetrofitFactory() }
    }

    /**
     * 初始化Retrofit
     * 设置baseUrl
     * 添加数据解析工厂 Gson
     * 添加回调适配器工厂 RxJava
     * 添加OkHttpClient
     * */
    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.SERVER_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    /**
     * 构建OkHttp客户端
     * 设置超时时间 10s
     * 添加拦截器
     * connectTimeout : 意思是用来建立连接的时间。如果到了指定的时间，还没建立连接，则报异常。
     * readTimeout : 意思是已经建立连接，并开始读取服务端资源。如果到了指定的时间，没有可能的
     * 数据被客户端读取，则报异常
     * */
    private fun initClient(): OkHttpClient {

        return OkHttpClient().newBuilder()
                .addInterceptor(initHeaderInterceptor())
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    /**
     * 请求头拦截器,初始化一些访问参数
     * */
    private fun initHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build()

            chain.proceed(request)
        }
    }

    /**
     * 构建日志拦截器,级别为BODY
     * */
    private fun initLogInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * 对外暴露的方法,接收一个具体Api接口 并将其返回
     * */
    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}