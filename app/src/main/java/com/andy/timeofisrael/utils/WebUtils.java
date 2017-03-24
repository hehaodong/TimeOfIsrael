package com.andy.timeofisrael.utils;

import android.app.Activity;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;

import com.andy.timeofisrael.R;
import com.andy.timeofisrael.ui.component.CustomTabActivityHelper;
import com.andy.timeofisrael.ui.component.WebViewFallback;

/**
 * Created by lancelot on 2016/12/4.
 */

public class WebUtils {
    public static void openUrl(Activity context, String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(context, R.color.primary));
        CustomTabsIntent customTabsIntent = builder.build();
        CustomTabActivityHelper.openCustomTab(context, customTabsIntent, Uri.parse(url), new WebViewFallback());
    }
}
