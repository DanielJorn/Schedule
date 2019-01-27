package example.yuratoxa.schedule;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    SharedPreferences sharedPreferences;

    PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences("123", Context.MODE_PRIVATE);

    }


    public void saveCount(String name, float count) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(name, count);
        editor.apply();
    }

    public void saveCount (String name, int count){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(name, count);
        editor.apply();
    }

    public float getCount(String nameOfCount, float defVal) {
        return sharedPreferences.getFloat(nameOfCount, defVal);
    }

    public int getCountInt(String nameOfCount, int defVal) {
        return sharedPreferences.getInt(nameOfCount, defVal);
    }

    public void smallAddToCount(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(name, getCount(name, 0) + 0.1f);
        editor.apply();
    }

    public void addToCount(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(name, getCountInt(name, 1) +1);
        editor.apply();
    }


    public void smallSubtractCount(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(name, getCount(name, 0) - 0.1f);
        editor.apply();
    }

    public void saveStringInPrefs(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringFromPrefs(String key) {
        return sharedPreferences.getString(key, "");
    }
}