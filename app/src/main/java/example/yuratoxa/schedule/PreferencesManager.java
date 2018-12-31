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
    }}