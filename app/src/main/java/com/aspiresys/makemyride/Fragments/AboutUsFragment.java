package com.aspiresys.makemyride.Fragments;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.aspiresys.makemyride.R;


public class AboutUsFragment extends Fragment {
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
           // Inflate the layout for this fragment
       mView= inflater.inflate(R.layout.fragment_about_us, container, false);
//       TextView textView=mView.findViewById(R.id.about_us);
        return mView;
}


}
