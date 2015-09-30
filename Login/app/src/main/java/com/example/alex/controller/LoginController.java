package com.example.alex.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.login.JSONParser;
import com.example.alex.login.R;
import com.example.alex.login.ReadComments;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alex on 29/09/2015.
 */
public class LoginController extends AsyncTask<String, String, String> {

    private Activity activity;
    private EditText user;
    private EditText pass;
    private ProgressDialog pDialog;
    private static final String LOGIN_URL = "http://dbremote.esy.es/login/Login.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public LoginController(Activity activity) {
        this.activity = activity;
        user = (EditText) this.activity.findViewById(R.id.username);
        pass = (EditText) this.activity.findViewById(R.id.password);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Attempting login...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... args) {
        StringBuilder params = new StringBuilder();
        JSONParser jsonParser = new JSONParser();
        String username = user.getText().toString();
        String password = pass.getText().toString();
        int success;

        try {
            params.append("username").append("=").append(username)
                    .append("&").append("password").append("=").append(password);

            JSONObject json = jsonParser.makeHttpRequestPost(LOGIN_URL, params.toString());

            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                SharedPreferences sp = PreferenceManager
                        .getDefaultSharedPreferences(activity);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("username", username);
                edit.commit();

                Intent i = new Intent(activity, ReadComments.class);
                activity.finish();
                activity.startActivity(i);
                return json.getString(TAG_MESSAGE);
            } else {
                return json.getString(TAG_MESSAGE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String file_url) {
        pDialog.dismiss();
        if (file_url != null) {
            Toast.makeText(activity, file_url, Toast.LENGTH_LONG).show();
        }
    }
}
