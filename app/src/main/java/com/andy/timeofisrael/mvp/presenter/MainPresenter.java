package com.andy.timeofisrael.mvp.presenter;

import com.andy.timeofisrael.BuildConfig;
import com.andy.timeofisrael.R;
import com.andy.timeofisrael.api.LangApi;
import com.andy.timeofisrael.api.TrendingApi;
import com.andy.timeofisrael.bean.AppUpdate;
import com.andy.timeofisrael.bean.Langs;
import com.andy.timeofisrael.mvp.view.MainView;
import com.andy.timeofisrael.utils.LogUtils;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lancelot on 2016/12/17.
 */

public class MainPresenter extends Presenter<MainView> {
    public MainPresenter(MainView view) {
        super(view);
    }

    public void getLangs() {
        Observable<Langs> observable = LangApi.getLangs(getActivity());
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(langs -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (langs != null) {
                                view.onGetLangsFinished(langs);
                            } else {
                                view.onRequestError(ErrorCode.ERROR_REQUEST_DATA, view.getActivity().getString(R.string.error_request_data));

                            }
                        }, error -> {
                            LogUtils.e(error);
                            view.onRequestError(ErrorCode.ERROR_OBSERVER_DATA, error.getMessage());
                        }, () -> view.onRequestFinished())
        );

    }

    public void checkAppUpdate() {
        Observable<AppUpdate> observable = TrendingApi.checkAppUpdate(BuildConfig.VERSION_NAME);
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(appUpdate -> {
                                    if (!isViewAttached()) {
                                        return;
                                    }
                                    if (appUpdate.getCode() > 0) {
                                        view.onCheckAppUpdateFinish(appUpdate);
                                    }
                                }, throwable -> {
                                    LogUtils.e(throwable);
                                    view.onRequestError(ErrorCode.ERROR_SAVE_DATA, view.getActivity().getString(R.string.error_request_data));
                                },
                                () -> view.onRequestFinished())
        );
    }
}
