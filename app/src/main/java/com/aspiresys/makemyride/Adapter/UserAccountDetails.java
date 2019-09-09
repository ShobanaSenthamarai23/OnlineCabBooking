package com.aspiresys.makemyride.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.aspiresys.makemyride.ModelClass.UserDetails;
import com.aspiresys.makemyride.R;

import java.util.List;

public class UserAccountDetails extends ArrayAdapter<UserDetails> {
    Context mCtx;
    int listLayoutRes;
    List<UserDetails> userDetailsList;
    SQLiteDatabase mDatabase;

    public UserAccountDetails(Context context, int listLayoutRes, List<UserDetails> userDetailsList, SQLiteDatabase mDatabase) {
        super(context, listLayoutRes, userDetailsList);

        this.mCtx = context;
        this.listLayoutRes = listLayoutRes;
        this.userDetailsList = userDetailsList;
        this.mDatabase = mDatabase;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);
       // getting user of specified position
        UserDetails userDetails=userDetailsList.get(position);
        //getting views
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        TextView textViewMobileNumber = view.findViewById(R.id.textViewMobile);
        TextView textViewAddress = view.findViewById(R.id.textViewAddress);
        //adding data to views
        textViewName.setText(userDetails.getName());
        textViewEmail.setText(userDetails.getEmail());
        textViewMobileNumber.setText(String.valueOf(userDetails.getMobilenumber()));
        textViewAddress.setText(userDetails.getResidentialAddress());
        //getting button views
        Button buttonDelete = view.findViewById(R.id.buttonDeleteAccount);
        Button buttonEdit = view.findViewById(R.id.buttonEditAccount);
        return view;
    }


}
