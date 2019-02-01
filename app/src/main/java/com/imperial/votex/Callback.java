package com.imperial.votex;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by emper on 10/04/2018.
 */

public class Callback {
    public static final String url = "http://192.168.1.10:80";//"https://votexsystems.heroku.com";
    private static String data = "";
    private static Context context = null;
    private static int code = 0;
    private static Object holder = null;
    private static boolean waitAsync1 = false, waitAsync2 = false;
    public static boolean waitProfile = false;
    public static boolean waitSystems = false;
    private static String profile = "";
    private static JSONArray systems = null;

    public static Object getHolder() {
        return holder;
    }

    public static void initialize(Context con, int cod, Object hold) {
        context = con;
        code = cod;
        holder = hold;
    }

    public static void execute(String dat) {
        data = dat;
        methodDecision();
    }

    public static void execute(String dat, int codeExec) {
        data = dat;
        code = codeExec;
        methodDecision();
    }

    public static void tabPopulate (boolean tab) {
        if (tab) {
            waitProfile = true;
        } else {
            waitSystems = true;
        }
        if (waitProfile && waitSystems) {
            waitAsync1 = true;
            if (waitAsync1 && waitAsync2) {
                MainActivity.globalTextView.setText(profile);
                MainActivity.globalListView.setText(systems.toString());
            }
        }
    }

    private static void methodDecision() {
        switch (code) {
            case 0:
                Toast.makeText(context, "ERROR: "+data, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (data.equals("false")) {
                    Toast.makeText(context, "User already registered",
                            Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences prefs = context.getSharedPreferences("UserPrefs",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("uname", data);
                    editor.commit();
                    Intent in = new Intent(context, MainActivity.class);
                    context.startActivity(in);
                }
                break;
            case 2:
                if (data.equals("false")) {
                    Toast.makeText(context, "User not found",
                            Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences prefs = context.getSharedPreferences("UserPrefs",
                            Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("uname", data);
                    editor.commit();
                    Intent in = new Intent(context, MainActivity.class);
                    context.startActivity(in);
                }
                break;
            case 3:
                try {
                    JSONObject obj = new JSONObject(data);
                    systems = obj.getJSONArray("systems");
                    obj.remove("systems");
                    if (obj.getString("acctv").equals("org")) {
                        obj.remove("fname");
                        obj.remove("lname");
                    } else {
                        obj.remove("orgname");
                    }
                    Iterator objKeys = obj.keys();
                    while (objKeys.hasNext()) {
                        String key = (String) objKeys.next();
                        profile += key + obj.getString(key) + "\n";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    profile = "ERROR";
                }
                waitAsync2 = true;
                if (waitAsync1 && waitAsync2) {
                    MainActivity.globalTextView.setText(profile);
                    MainActivity.globalListView.setText(systems.toString());
                }
                break;
            case 4:

                break;
            case 5:
                try {
                    if (data.equals("")) {
                        Toast.makeText(context, "System not found", Toast.LENGTH_SHORT).show();
                    } else {
                        String res = "";
                        JSONObject obj = new JSONObject(data);
                        res += obj.getString("name") + ": " + obj.getString("sysname") + "\n";
                        VoteActivity.bigName.setText(res);

                        JSONObject candidates = obj.getJSONObject("candidates");
                        Iterator it = candidates.keys();
                        int i = 0;
                        while (it.hasNext()) {
                            RadioButton button = new RadioButton(context);
                            VoteActivity.radioGroup.addView(button);
                            button.setText(it.next().toString());
                            button.setId(10000+i);
                            i++;
                        }
                        VoteActivity.submitVote.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(context, "System not found", Toast.LENGTH_SHORT).show();
                }
                break;
            case 6:
                Toast.makeText(context, "Voted :)", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
