package com.andy.timeofisrael.mvp.view;

import com.andy.timeofisrael.bean.AppUpdate;
import com.andy.timeofisrael.bean.Langs;

/**
 * Created by lancelot on 2016/12/17.
 */

public interface MainView extends BaseView {

    void onGetLangsFinished(Langs langs);

    void onCheckAppUpdateFinish(AppUpdate appUpdate);
}
