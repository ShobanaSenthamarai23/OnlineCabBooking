package com.aspiresys.makemyride.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.aspiresys.makemyride.ModelClass.BookingDetails;
import com.aspiresys.makemyride.ModelClass.UserDetails;
import com.aspiresys.makemyride.Utils.Constants;

import java.util.Arrays;

import static com.aspiresys.makemyride.Utils.Constants.TABLE_BOOKING_DETAILS;
import static com.aspiresys.makemyride.Utils.Constants.TABLE_LOCATION_DETAILS;
import static com.aspiresys.makemyride.Utils.Constants.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    String email;
    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Constants.CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(Constants.CREATE_BOOKING_TABLE);
        sqLiteDatabase.execSQL(Constants.CREATE_TABLE_LOCATIONS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TABLE_BOOKING_DETAILS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  " + TABLE_LOCATION_DETAILS);
        onCreate(sqLiteDatabase);
    }

    public boolean insertUserDetails(UserDetails user) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_USER_NAME, user.getName());
        values.put(Constants.COLUMN_USER_EMAIL, user.getEmail());
        values.put(Constants.COLUMN_USER_PASSWORD, user.getPassword());
        values.put(Constants.COLUMN_USER_MOBILE, user.getMobilenumber());
        values.put(Constants.COLUMN_USER_RESIDENTIAL_ADDRESS, user.getResidentialAddress());
        long result = sqLiteDatabase.insert(TABLE_NAME, null, values);
        return result != -1;

    }

    public boolean checkUser(UserDetails user) {
        String userPassword = user.getPassword();
        String userEmail = user.getEmail();
        email=userEmail;
        sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT userEmail,userPassword FROM  " + TABLE_NAME;
        try (Cursor cursor = sqLiteDatabase.rawQuery(query, null)) {
            String userEmailId;
//        password = "Not found";
            if (cursor.moveToFirst()) {
                do {
                    userEmailId = cursor.getString(0);
                    if (userEmailId.contentEquals(userEmail) && userPassword.contentEquals(cursor.getString(1))) {
                        return true;
                    }
                } while (cursor.moveToNext());
            }
        }
        return false;
    }

//
//    public List<UserDetails> getAllDetails() {
//        sqLiteDatabase = this.getWritableDatabase();
//        List<UserDetails> userDetailsList = new ArrayList<UserDetails>();
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + Constants.COLUMN_USER_EMAIL + " = ?";
//        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                UserDetails userDetails = new UserDetails();
//                userDetails.setName(cursor.getString(0));
//                userDetails.setEmail(cursor.getString(1));
//                userDetails.setMobilenumber(cursor.getString(3));
//                userDetails.setResidentialAddress(cursor.getString(4));
//                userDetailsList.add(userDetails);
//            } while (cursor.moveToNext());
//        }
//
//
//        return userDetailsList;
//    }

    public Boolean insertBookingDetails(BookingDetails bookingDetails) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.COLUMN_BOARDING_POINT, bookingDetails.getBoardingPoint());
        Log.d("hii", bookingDetails.getBoardingPoint());
        values.put(Constants.COLUMN_DROPPING_POINT, bookingDetails.getDroppingPoint());
        values.put(Constants.COLUMN_DATE, bookingDetails.getDate());
        values.put(Constants.COLUMN_TIME, bookingDetails.getTime());
        long result = sqLiteDatabase.insert(TABLE_BOOKING_DETAILS, null, values);
        Log.d("hii", String.valueOf(result));
        return result != -1;

    }


    public boolean updatedPassword(String email, String password) {
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cur= sqLiteDatabase.rawQuery("UPDATE "+Constants.TABLE_NAME+" SET "+Constants.COLUMN_USER_PASSWORD + " = '"+ password +"' WHERE "+  Constants.COLUMN_USER_EMAIL +" = ? ",new String[]{email});
//        ContentValues values = new ContentValues();
//        values.put(Constants.COLUMN_USER_PASSWORD, password);
//        sqLiteDatabase.query(Constants.TABLE_NAME, values, Constants.COLUMN_USER_EMAIL + " = ? ", new String[]{email} ,null,null, null);
        int count =cur.getCount();
        return count > 0;
    }

    public boolean emailExists(String email) {
        sqLiteDatabase = this.getWritableDatabase();
        String[] column={Constants.COLUMN_USER_EMAIL};
        String selection = Constants.COLUMN_USER_EMAIL + " = ? ";
        String[] selectionArgs = {email};
        String limit="1";
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,
                                             column,
                                             selection,
                                             selectionArgs,
                                             null,
                                             null,
                                             null,
                                             limit);
        int cursorCount = cursor.getCount();
        cursor.close();
        sqLiteDatabase.close();

        return cursorCount > 0;
    }

}
