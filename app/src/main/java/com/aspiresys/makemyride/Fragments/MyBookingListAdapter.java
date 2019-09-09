package com.aspiresys.makemyride.Fragments;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aspiresys.makemyride.ModelClass.BookingDetails;
import com.aspiresys.makemyride.R;

import java.util.ArrayList;
import java.util.List;

public class MyBookingListAdapter extends RecyclerView.Adapter<MyBookingListAdapter.MyViewHolder> {
    private final Activity mActivity;
    private ArrayList mMyBookingDetails;
    public int mPosition;


    public MyBookingListAdapter(Activity activity, List<BookingDetails> bookingDetails) {
        this.mActivity = activity;
        this.mMyBookingDetails = (ArrayList) bookingDetails;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_booking_list_fragment, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView starting_point, destination_point, time, date, rate;

        public MyViewHolder(View view) {
            super(view);
            starting_point = view.findViewById(R.id.textViewStartingPoint);
            destination_point = view.findViewById(R.id.textView_destination);
            time = view.findViewById(R.id.textViewTime);
            date = view.findViewById(R.id.textViewDate);
            rate = view.findViewById(R.id.textViewAmount);

        }
    }
}
