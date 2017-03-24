package com.andy.timeofisrael.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.andy.timeofisrael.R;
import com.andy.timeofisrael.ui.fragment.ReposFragment;
import com.andy.timeofisrael.ui.fragment.TagCacheFragment;
import com.andy.timeofisrael.utils.TagPrefUtils;

/**
 * Search
 * Created by lancelot on 2016/12/3.
 */

public class SearchActivity extends BaseActivity {
    SearchView mSearchView;
    ReposFragment mFragment;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTagFrag();
    }

    void initTagFrag() {
        TagCacheFragment fragment = new TagCacheFragment();
        fragment.setOnTagClickListener(tag -> mSearchView.setQuery(tag, true));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.search_container, fragment)
                .commitAllowingStateLoss();
    }

    void initSearch() {
        mSearchView.setIconified(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                query = query.trim();
                if (mFragment == null) {
                    mFragment = ReposFragment.newInstance(query);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.search_container, mFragment)
                            .commitAllowingStateLoss();
                } else {
                    mFragment.search(query);
                }

                TagPrefUtils.appendTags(getApplicationContext(), query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void clearFocus() {
        if (mSearchView != null) {
            mSearchView.clearFocus();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search_view);
        mSearchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        initSearch();
        MenuItemCompat.collapseActionView(menuItem);
        MenuItemCompat.expandActionView(menuItem);
        return true;
    }

    public static void launch(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }
}
