package com.aspiresys.makemyride.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.FragmentActivity;

import com.aspiresys.makemyride.ModelClass.BookingDetails;

import java.util.ArrayList;

public class SharedPreferenceManager {
    Activity mActivity;
    SharedPreferences mSharedPreferences;

    public SharedPreferenceManager(FragmentActivity activity) {
        this.mActivity=activity;
    }

    public ArrayList<BookingDetails> getBookingDetails() {
        ArrayList<BookingDetails> list=new ArrayList<>();
        String sp=mSharedPreferences.getString("Starting point"," ");
        String dst=mSharedPreferences.getString("Dropping point"," ");
        String dt=mSharedPreferences.getString("Date of Journey"," ");
        String time=mSharedPreferences.getString("Time"," ");
        String rate=mSharedPreferences.getString("price"," ");
        BookingDetails bookingDetails=new BookingDetails(sp,dst,dt,time,rate);
        list.add(bookingDetails);
        return list;
    }

    public void saveLoginDetails(String email) {
        mSharedPreferences=mActivity.getSharedPreferences("AuthorizedUserdetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("Email", email);
        editor.putBoolean("logged",true).apply();
        editor.commit();

    }

    public void saveBookingDetail(String mSource, String mDestination, String date, String time, String price) {
        mSharedPreferences=mActivity.getSharedPreferences("AuthorizedUserdetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("Starting point",mSource);
        editor.putString("Dropping point",mDestination);
        editor.putString("Date of Journey",date);
        editor.putString("Time",time);
        editor.putString("price",price);
        editor.apply();
    }

//    public void saveUserDetails(String name, String email, String mobileNumber, String residentialAddress) {
//        mSharedPreferences=mActivity.getSharedPreferences("AuthorizedUserdetails", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = mSharedPreferences.edit();
//        editor.putString("Name",name);
//        editor.putString("Email",email);
//        editor.putString("mobile Number",mobileNumber);
//        editor.putString("Address",residentialAddress);
//        editor.commit();
//    }
}
