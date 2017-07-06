package com.liwanwan.topnews;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 李婉婉 on 2017/7/5.20:43
 */
public class BaseApplicate extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
