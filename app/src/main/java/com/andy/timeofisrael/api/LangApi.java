package com.andy.timeofisrael.api;

import android.content.Context;

import com.andy.timeofisrael.bean.GsonBuilder;
import com.andy.timeofisrael.bean.Langs;
import com.andy.timeofisrael.utils.LangsUtils;

import rx.Observable;

/**
 * Created by lancelot on 2016/12/17.
 */

public class LangApi {
    private LangApi() {
    }

    public static Observable<Langs> getLangs(Context context) {
        Observable<Langs> observable = Observable.create(subscriber -> {
            String json = LangsUtils.readLangJson(context);
            Langs langs = GsonBuilder.parseJson(json, Langs.class);
            subscriber.onNext(langs);
            subscriber.onCompleted();
        });
        return observable;
    }

    public static Observable<Langs> setLangs(Context context, Langs langs) {
        Observable<Langs> observable = Observable.create(subscriber -> {
            LangsUtils.write2sdcard(context, langs);
            subscriber.onNext(langs);
            subscriber.onCompleted();
        });
        return observable;
    }
}
