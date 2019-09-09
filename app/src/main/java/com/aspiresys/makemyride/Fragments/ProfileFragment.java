package com.aspiresys.makemyride.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspiresys.makemyride.R;

import java.util.ArrayList;
import java.util.Arrays;


public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        ArrayList profileItems = new ArrayList<>(Arrays.asList("Personal Details","My Rewards","My Bookings","Call Support","About Us","logout"));
        RecyclerView recyclerView = view.findViewById(R.id.profileDetails);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ProfilePageAdapter customAdapter = new ProfilePageAdapter(getActivity(), profileItems);
        recyclerView.setAdapter(customAdapter);
        return view;
    }
}






