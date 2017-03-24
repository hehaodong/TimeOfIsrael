package com.andy.timeofisrael.api;

import android.util.Log;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lancelot on 2016/12/2.
 */

public class BaseTrendApi {
    public final static String API_SERVER = "https://angrycode.leanapp.cn/api/github/";
    private final static OkHttpClient mOkHttpClient;
    private static Retrofit sRetrofit;

    public BaseTrendApi() {


    }

    static {
        Cache cache = new Cache(new File("/data/data/com.andy.timeofisrael/cache"), 1024 * 1024);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .addNetworkInterceptor(new CacheInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    protected static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            //构建Retrofit
            sRetrofit = new Retrofit.Builder()
                    //配置服务器路径
                    .baseUrl(API_SERVER)
                    //设置日期解析格式，这样可以直接解析Date类型
//                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    //配置转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置OKHttpClient为网络客户端
                    .client(mOkHttpClient)
                    .build();
        }
        return sRetrofit;
    }

}
