package com.androidbegin.fragmenttabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;

import com.androidbegin.fragmenttabstutorial.R;

public class FragmentTab2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmenttab2, container, false);
        return rootView;
    }
 
}