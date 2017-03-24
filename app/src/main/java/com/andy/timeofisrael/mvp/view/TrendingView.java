package com.andy.timeofisrael.mvp.view;

import com.andy.timeofisrael.bean.TrendingRepo;

import java.util.List;

/**
 * Created by lancelot on 2016/12/3.
 */

public interface TrendingView extends BaseView {

    void onGetTrendingsFinish(List<TrendingRepo> repos);

    void onAddFavorFinish(TrendingRepo repo);

}
