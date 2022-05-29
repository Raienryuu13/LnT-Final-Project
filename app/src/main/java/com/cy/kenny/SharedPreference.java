package com.cy.kenny;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Set;

public class SharedPreference {

    private SharedPreferences prefs;

    public SharedPreference(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUser(String user) {
        prefs.edit().putString("user", user).commit();
    }

    public String getUser() {
        String user = prefs.getString("user",null);
        return user;
    }

    public void setCounter(int counter) {
        prefs.edit().putInt("counter", counter).commit();
    }

    public int getCounter() {
        int counter = prefs.getInt("counter",0);
        return counter;
    }
}
