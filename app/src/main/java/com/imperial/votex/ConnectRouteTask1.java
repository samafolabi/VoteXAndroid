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

public class ConnectRouteTask1 /*extends AsyncTask<ArrayList, Void, ArrayList>*/ {
    /*@Override
    protected ArrayList doInBackground(final ArrayList... params) {
        /*final ArrayList list = params[0];
        String urlStr = (String) list.get(0);
        final int[] callbackCode = {(int) list.get(2)};
        final Context context = (Context) list.get(3);
        ArrayList next = new ArrayList(6);

        //url connection, data send, callback functions

        *//*try {
            HostnameVerifier verifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            URL url = new URL(urlStr);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            HttpURLConnection.setFollowRedirects(true);
            conn.setInstanceFollowRedirects(false);
            conn.setDoOutput(true);
            conn.setHostnameVerifier(verifier);

            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes("UTF-8"));
            out.close();

            if (conn.getResponseCode() == 200
                    && conn.getResponseMessage() == "OK") {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String buffer = "";
                while ((buffer = reader.readLine()) != null) {
                    res += buffer;
                }
                reader.close();
                in.close();
            } else {
                res = "ERROR";
                callbackCode = 0;
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            res = e.getMessage();
            callbackCode = 0;
        }*//*

        Callback.initialize(context, callbackCode[0], list.get(1));

        try {
            disableSSLCertificateChecking();
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
                    return data;
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Callback.execute(e.getMessage(), 0);
        }




        return next;
    }

    @Override
    protected void onPostExecute(ArrayList list) {
    }

    private static void disableSSLCertificateChecking() {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {

                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                        // not implemented
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] x509Certificates, String s) throws java.security.cert.CertificateException {
                        // not implemented
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                }
        };

        try {

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }

            });
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
*/
    /*private void callBack(String res, int callbackCode, Context context) {
        ArrayList next = new ArrayList(3);
        next.add(res);
        next.add(callbackCode);
        next.add(context);

        Callback callback = new Callback(next);
    }*/

}
