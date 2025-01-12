package com.qualia.pengenalanprofesi;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManager() {
    }

    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUser(String username, String password, String name, String birthDate, String email) {
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_PASSWORD, password);
        editor.putString("name", name);
        editor.putString("birthDate", birthDate);
        editor.putString("email", email);
        editor.apply();
    }

    public String getUserData(String key) {
        return sharedPreferences.getString(key, null);
    }

    public String getUsername() {
        return sharedPreferences.getString(KEY_USERNAME, null);
    }

    public String getEmail() {
        return sharedPreferences.getString("email", null); // Retrieve the email
    }

    public boolean checkUser(String username, String password) {
        String savedUsername = sharedPreferences.getString(KEY_USERNAME, null);
        String savedPassword = sharedPreferences.getString(KEY_PASSWORD, null);

        return username.equals(savedUsername) && password.equals(savedPassword);
    }
}