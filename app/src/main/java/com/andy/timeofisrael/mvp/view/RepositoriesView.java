package com.andy.timeofisrael.mvp.view;

import com.andy.timeofisrael.bean.Repos;
import com.andy.timeofisrael.bean.TrendingRepo;

/**
 * Created by lancelot on 2016/11/13.
 */

public interface RepositoriesView extends BaseView {

    void onGetRepositories(Repos repos);

    void onAddFavorFinish(TrendingRepo repo);
}
