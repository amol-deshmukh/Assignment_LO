package com.assignment.lotusassignment;

import android.app.Application;


/**
 * Created by
 *
 * @author Amol Deshmukh
 * @since 05/03/19
 */
public class LotusApplication extends Application {

    private static LotusApplication lotusApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        lotusApplication=this;
    }

    public static LotusApplication getInstance() {
        return lotusApplication;
    }

}
