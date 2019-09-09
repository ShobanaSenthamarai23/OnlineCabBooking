package com.aspiresys.makemyride.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aspiresys.makemyride.Helper.DatabaseHelper;
import com.aspiresys.makemyride.Helper.InputValidateHelper;
import com.aspiresys.makemyride.Helper.SharedPreferenceManager;
import com.aspiresys.makemyride.ModelClass.BookingDetails;
import com.aspiresys.makemyride.ModelClass.UserDetails;
import com.aspiresys.makemyride.R;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Objects;

import static java.lang.String.*;

public class BookingFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private EditText mBoardingPoint, mDroppingPoint, mPayment;
    private TextView mTimeOfRide, mDateOfRide;
    private Button buttonBook;
    private String mSource, mDestination, time, date, price;
    private BookingDetails bookingDetails = new BookingDetails();
    private DatabaseHelper databaseHelper;
    private InputValidateHelper inputValidateHelper;
    private final Calendar c = Calendar.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.booking_fragment, container, false);
        initViews();
        buttonBook.setOnClickListener(this);
        mDateOfRide.setOnClickListener(this);
        mTimeOfRide.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(getContext());
        inputValidateHelper = new InputValidateHelper();
        UserDetails user = new UserDetails();
//        Log.d("Android", user.getEmail());
        return mView;

    }

    private void initViews() {
        mBoardingPoint = mView.findViewById(R.id.from);
        mDroppingPoint = mView.findViewById(R.id.to);
        mPayment = mView.findViewById(R.id.taxi_cost);
        buttonBook = mView.findViewById(R.id.ride_booking);
        mTimeOfRide = mView.findViewById(R.id.timing);
        mDateOfRide = mView.findViewById(R.id.date);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ride_booking:
                if (getUserBookingValues()) {
                    bookingDetails.setBoardingPoint(mSource);
                    bookingDetails.setDroppingPoint(mDestination);
                    bookingDetails.setDate(date);
                    bookingDetails.setTime(time);
                    bookingDetails.setPrice(price);
                    saveBookingDetails(mSource, mDestination, date, time, price);
                    Boolean isInserted = databaseHelper.insertBookingDetails(bookingDetails);
                    if (isInserted) {
                        Toast.makeText(getActivity(), "Booked Successfully ..", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Not Booked !! Please check it Once!", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.date:
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDate = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        mDateOfRide.setText(MessageFormat.format("{0}-{1}-{2}", date, month + 1, year));
                    }
                }, mYear, mMonth, mDate);
                datePickerDialog.show();
                break;
            case R.id.timing:
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("DefaultLocale")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        mTimeOfRide.setText(MessageFormat.format("{0}{1}", format("%02d:%02d", hourOfDay, minutes), amPm));
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
                break;

        }
    }

    private void saveBookingDetails(String mSource, String mDestination, String date, String time, String price) {
        new SharedPreferenceManager(getActivity()).saveBookingDetail(mSource, mDestination, date, time, price);
    }

    private boolean getUserBookingValues() {
        int flag = 0;
        mSource = mBoardingPoint.getText().toString().trim();
        mDestination = mDroppingPoint.getText().toString().trim();
        if (!inputValidateHelper.ValidateTravelingPoints(mSource, mDestination)) {
            mBoardingPoint.setError("Enter a valid Place name");
            mDroppingPoint.setError("Enter a valid Place name");
            flag = 1;
        }
        date = mDateOfRide.getText().toString().trim();
        if (!inputValidateHelper.isValidateDate(date)) {
            mDateOfRide.setError("Please provide the date");
            flag = 1;
        }
        time = mTimeOfRide.getText().toString().trim();
        if (!inputValidateHelper.isValidateTime(time)) {
            mTimeOfRide.setError("Please provide the time");
            flag = 1;
        }
        price = mPayment.getText().toString().trim();

        return flag == 0;
    }
}
