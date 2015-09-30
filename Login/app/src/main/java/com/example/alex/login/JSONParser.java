package com.example.alex.login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alex on 28/09/2015.
 * Alex comment: neet to convert to absctract class
 */
public class JSONParser {

    public final String POST = "POST";
    public final String GET = "GET";

    /**
     * @private
     * @param webUrl
     * @param method
     * @param params
     * @return
     */
    public JSONObject makeHttpRequest(String webUrl, String method, String params) {
        URL url;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;
        JSONObject jsonObject = null;
        String line = "";

        try {
            url = new URL(webUrl);
            connection = (HttpURLConnection) url.openConnection();

            if (method == GET) {
                connection.setRequestMethod(GET);
            } else if (method == POST) {
                DataOutputStream outputStream;
                connection.setRequestMethod(POST);

                // Send post request
                connection.setDoOutput(true);
                outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(params);
                outputStream.flush();
                outputStream.close();
            }

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            buffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            jsonObject = new JSONObject(buffer.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * @private
     * @param webUrl
     * @return
     */
    public JSONObject makeHttpRequestGet(String webUrl) {
        URL url;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;
        JSONObject jsonObject = null;
        String line = "";

        try {
            url = new URL(webUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(GET);

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            buffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            jsonObject = new JSONObject(buffer.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * @private
     * @param webUrl
     * @param params
     * @return
     */
    public JSONObject makeHttpRequestPost(String webUrl, String params) {
        URL url;
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;
        JSONObject jsonObject = null;
        DataOutputStream outputStream;
        String line = "";

        try {
            url = new URL(webUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(POST);

            // Send post request
            connection.setDoOutput(true);
            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(params);
            outputStream.flush();
            outputStream.close();

            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            buffer = new StringBuffer();

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            jsonObject = new JSONObject(buffer.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
