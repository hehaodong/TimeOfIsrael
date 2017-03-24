package com.andy.timeofisrael.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.andy.timeofisrael.R;
import com.andy.timeofisrael.bean.Langs;
import com.andy.timeofisrael.mvp.presenter.LangPresenter;
import com.andy.timeofisrael.mvp.view.LangView;
import com.andy.timeofisrael.ui.adapter.LangAdapter;
import com.andy.timeofisrael.ui.component.LangGridLayoutManager;
import com.andy.timeofisrael.ui.component.drag.ItemDragHelperCallback;
import com.andy.timeofisrael.utils.ToastUtils;

import butterknife.BindView;

/**
 * 设置语言
 * Created by lancelot on 2016/12/9.
 */

public class LanguagesActivity extends BaseActivity implements LangView {
    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;
    LangAdapter mAdapter;
    LangPresenter mPresenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_languages;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mPresenter = new LangPresenter(this);
        mPresenter.getLangs();
    }

    private void init() {

        ItemDragHelperCallback callback = new ItemDragHelperCallback();
        final ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecycleView);

        mAdapter = new LangAdapter(helper);

        LangGridLayoutManager manager = new LangGridLayoutManager(this, mAdapter, 4);

        mRecycleView.setLayoutManager(manager);
        mRecycleView.setAdapter(mAdapter);

    }

    @Override
    public void onGetLangsFinished(Langs langs) {
        mAdapter.setLangs(langs);
    }

    @Override
    public void onSetLangsFinished(Langs langs) {

    }

    @Override
    protected void onPause() {
        if (mAdapter.isEdited()) {
            mPresenter.setLangs(mAdapter.getLangs());
        }
        super.onPause();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onRequestLoading() {
        showLoading();
    }

    @Override
    public void onRequestFinished() {
        dismissLoading();
    }

    @Override
    public void onRequestError(int code, String message) {
        ToastUtils.show(getApplicationContext(), message);
        dismissLoading();
    }
}
