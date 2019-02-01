package com.imperial.votex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import static com.imperial.votex.LoginActivity.fields;

public class LogInSlidePageFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_login_screen,
                container, false);

        fields[8] = (EditText) rootView.findViewById(R.id.user);
        fields[9] = (EditText) rootView.findViewById(R.id.pass);

        return rootView;

    }
}
