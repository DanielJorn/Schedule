package example.yuratoxa.schedule;

import android.app.Application;

public class CustomApplication extends Application {

    private static PreferencesManager preferencesManager;


    public static PreferencesManager getPreferencesManager() {
        return preferencesManager;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        preferencesManager = new PreferencesManager(this);
    }

}