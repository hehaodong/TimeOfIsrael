package com.andy.timeofisrael.mvp.view;

import com.andy.timeofisrael.bean.TrendingRepo;

import java.util.List;

/**
 * Created by lancelot on 2016/12/18.
 */

public interface FavorView extends BaseView {

    void onGetFavorListFinish(List<TrendingRepo> repoList);

    void onDelFavorFinish(TrendingRepo repo);
}
