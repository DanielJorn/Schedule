package example.yuratoxa.schedule;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    SharedPreferences sharedPreferences;

    PreferencesManager (Context context){
        sharedPreferences = context.getSharedPreferences("123", Context.MODE_PRIVATE);

    }


    public void saveCount(String name, float count){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(name, count);
        editor.apply();
    }

    public float getCount (String nameOfCount, float defVal) {
        return sharedPreferences.getFloat(nameOfCount, defVal);
    }
    public void smallAddToCount(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(name, getCount(name, 0) + 0.1f);
        editor.apply();
    }

    public void AddToCount(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(name, getCount(name, 0) + 1);
        editor.apply();
    }
    public void smallSubtractCount(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(name, getCount(name, 0) - 0.1f);
        editor.apply();
    }
}