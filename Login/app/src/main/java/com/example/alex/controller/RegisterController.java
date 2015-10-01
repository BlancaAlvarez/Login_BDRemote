package com.example.alex.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.connection.ServerConnection;
import com.example.alex.login.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Alex on 29/09/2015.
 */
public class RegisterController extends AsyncTask<String, String, String> {

    private static final String REGISTER_URL = "http://dbremote.esy.es/login/Register.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    private EditText user;
    private EditText pass;
    private Activity activity;
    private ProgressDialog pDialog;

    public RegisterController (Activity activity) {
        this.activity = activity;
        user = (EditText) this.activity.findViewById(R.id.username);
        pass = (EditText) this.activity.findViewById(R.id.password);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Creating User...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... args) {

        StringBuilder params = new StringBuilder();
        ServerConnection serverConnection = new ServerConnection();
        JSONObject json;
        String username = user.getText().toString();
        String password = pass.getText().toString();
        int success;

        try {
            params.append("username").append("=").append(username)
                    .append("&").append("password").append("=").append(password);

            json = serverConnection.makeHttpRequestPost(REGISTER_URL, params.toString());

            success = json.getInt(TAG_SUCCESS);
            if (success == 1) {
                activity.finish();
                return json.getString(TAG_MESSAGE);
            } else {
                return json.getString(TAG_MESSAGE);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onPostExecute(String file_url) {
        pDialog.dismiss();
        if (file_url != null) {
            Toast.makeText(activity, file_url, Toast.LENGTH_LONG).show();
        }
    }
}
