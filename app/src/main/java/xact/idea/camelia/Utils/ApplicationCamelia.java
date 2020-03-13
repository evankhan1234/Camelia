package xact.idea.camelia.Utils;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import xact.idea.camelia.Helper.LocaleHelper;

public class ApplicationCamelia extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base,"en"));
        MultiDex.install(this);
    }

}