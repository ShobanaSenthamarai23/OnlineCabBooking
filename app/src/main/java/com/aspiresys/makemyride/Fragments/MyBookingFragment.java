package com.aspiresys.makemyride.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspiresys.makemyride.Helper.SharedPreferenceManager;
import com.aspiresys.makemyride.ModelClass.BookingDetails;
import com.aspiresys.makemyride.R;

import java.util.ArrayList;
import java.util.List;


public class MyBookingFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyBookingListAdapter myBookingListAdapter;
    List<BookingDetails> bookingDetails;

    View mView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my_booking, container, false);
        bookingDetails = new ArrayList<>();
        recyclerView = mView.findViewById(R.id.bookingDetails);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        myBookingListAdapter = new MyBookingListAdapter(getActivity(), bookingDetails);
        recyclerView.setAdapter(myBookingListAdapter);
//        prepareBookingDetails();
        return mView;
    }

//    private void prepareBookingDetails() {
//        List res = new SharedPreferenceManager(getActivity()).getBookingDetails();
//        bookingDetails = new BookingDetails("True Romance", 13, covers[0]);
//        albumList.add(a);
//    }
}
