package xact.idea.camelia.Helper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.annotation.RequiresApi;

import java.util.Locale;

public class LocaleHelper {

    private static final String SELECTED_LANGUAGE="Locale.Helper.Selected.Language";
    public static Context onAttach(Context context){
        String lang=getPersistedData(context, Locale.getDefault().getLanguage());
        return setLocale(context,lang);
    }
    public static Context onAttach(Context context,String defaultLanguage){
        String lang=getPersistedData(context, defaultLanguage);
        return setLocale(context,lang);
    }

    public static Context setLocale(Context context, String lang) {
        persist(context,lang);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q)
            return updateResources(context,lang);

        return updateResourcesLegacy(context,lang);
    }


    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources=context.getResources();
        Configuration configuration =context.getResources().getConfiguration();
        configuration.locale=locale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            configuration.setLayoutDirection(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        return context;
    }


    @TargetApi(Build.VERSION_CODES.Q)
    private static Context updateResources(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration =context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        return context.createConfigurationContext(configuration);
    }

    private static void persist(Context context, String lang) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString(SELECTED_LANGUAGE,lang);
        editor.apply();
    }

    private static String getPersistedData(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE,language);
    }
}
