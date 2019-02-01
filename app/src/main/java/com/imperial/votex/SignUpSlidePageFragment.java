package com.imperial.votex;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import static com.imperial.votex.LoginActivity.fields;

public class SignUpSlidePageFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_signup_screen,
                container, false);

        fields[0] = (EditText) rootView.findViewById(R.id.orgname);
        fields[1] = (EditText) rootView.findViewById(R.id.fname);
        fields[2] = (EditText) rootView.findViewById(R.id.lname);
        fields[3] = (EditText) rootView.findViewById(R.id.email);
        fields[4] = (EditText) rootView.findViewById(R.id.cemail);
        fields[5] = (EditText) rootView.findViewById(R.id.uname);
        fields[6] = (EditText) rootView.findViewById(R.id.pword);
        fields[7] = (EditText) rootView.findViewById(R.id.cpword);

        return rootView;

    }


}
