package com.andy.timeofisrael.mvp.presenter;

import com.andy.timeofisrael.R;
import com.andy.timeofisrael.MyApplication;
import com.andy.timeofisrael.api.TrendingApi;
import com.andy.timeofisrael.bean.DaoSession;
import com.andy.timeofisrael.bean.TrendingRepo;
import com.andy.timeofisrael.mvp.view.TrendingView;
import com.andy.timeofisrael.utils.LogUtils;

import org.greenrobot.greendao.rx.RxDao;

import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lancelot on 2016/12/3.
 */

public class TrendsPresenter extends Presenter<TrendingView> {
    RxDao<TrendingRepo, Long> mRxDao;

    public TrendsPresenter(TrendingView view) {
        super(view);
        DaoSession session = MyApplication.get().getDaoSession();
        mRxDao = session.getTrendingRepoDao().rx();
    }

    /**
     * @param lang
     */
    public void getTrending(String lang, boolean cache) {
        view.onRequestLoading();
        Observable<List<TrendingRepo>> observable = TrendingApi.getRepos(lang);
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(repositories -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (repositories != null && repositories.size() > 0) {
                                view.onGetTrendingsFinish(repositories);
                            } else {
                                view.onRequestError(ErrorCode.ERROR_REQUEST_DATA, view.getActivity().getString(R.string.error_request_data));
                            }
                        }, error -> {
                            LogUtils.e(error);
                            view.onRequestError(ErrorCode.ERROR_OBSERVER_DATA, getRequestErrorMsg());
                        }, () -> view.onRequestFinished())
        );
    }

    public void addFavor(TrendingRepo repo) {
        if (repo == null) {
            return;
        }
        repo.setDate(new Date());
        mRxDao.insertOrReplace(repo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(trendingRepo -> {
                            if (!isViewAttached()) {
                                return;
                            }
                            if (trendingRepo.getId() > 0) {
                                view.onAddFavorFinish(trendingRepo);
                            }
                        }, throwable -> {
                            view.onRequestError(ErrorCode.ERROR_SAVE_DATA, throwable.getMessage());
                        },
                        () -> view.onRequestFinished());
    }
}
