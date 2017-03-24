package com.andy.timeofisrael.api;

import android.content.Context;


import com.andy.timeofisrael.bean.GsonBuilder;
import com.andy.timeofisrael.bean.TrendingRepo;
import com.andy.timeofisrael.utils.FileUtils;

import java.io.File;
import java.util.List;

import rx.Observable;

/**
 * Created by lancelot on 2016/12/17.
 */

public class FavorApi {
    public static final String FILE_NAME = "favor";

    private FavorApi() {
    }

    public Observable<List<TrendingRepo>> getFavorList(Context context) {
        Observable<List<TrendingRepo>> observable = Observable.create(subscriber -> {
            String json = read(context);
            List<TrendingRepo> list = GsonBuilder.buildArray(json, TrendingRepo.class);
            subscriber.onNext(list);
            subscriber.onCompleted();
        });
        return observable;
    }


    private static String read(Context context) {
        File file = new File(context.getExternalCacheDir(), FILE_NAME);
        return FileUtils.readFile(file.getAbsolutePath());
    }
}
