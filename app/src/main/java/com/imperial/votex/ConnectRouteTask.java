package com.imperial.votex;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by emper on 10/04/2018.
 */

public class ConnectRouteTask extends AsyncTask<ArrayList, Void, Void> {
    @Override
    protected Void doInBackground(final ArrayList... params) {
        final ArrayList list = params[0];
        String urlStr = (String) list.get(0);
        final int[] callbackCode = {(int) list.get(2)};
        final Context context = (Context) list.get(3);

        Callback.initialize(context, callbackCode[0], list.get(1));

        try {
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest postRequest = new StringRequest(
                    Request.Method.POST, urlStr,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Callback.execute(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Callback.execute(error.getMessage(), 0);
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> data = (Map<String, String>) Callback.getHolder();
                    return ((data == null) ? null : data);
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Callback.execute(e.getMessage(), 0);
        }


        return null;
    }



}
