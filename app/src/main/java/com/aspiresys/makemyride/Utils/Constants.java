package com.aspiresys.makemyride.Utils;


public class Constants {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "MakeMyRide.db";
    public static final String TABLE_NAME = "UserDetails";
    public static final String TABLE_BOOKING_DETAILS = "BookingDetails";
    public static final String COLUMN_USER_NAME = "userName";
    public static final String COLUMN_USER_EMAIL = "userEmail";
    public static final String COLUMN_USER_PASSWORD = "userPassword";
    public static final String COLUMN_USER_MOBILE = "mobile";
    public static final String COLUMN_USER_RESIDENTIAL_ADDRESS = "residentialAddress";
    public static final String COLUMN_BOARDING_POINT = "BoardingPoint";
    public static final String COLUMN_DROPPING_POINT = "DroppingPoint";
    public static final String COLUMN_DATE = "Date_of_Ride";
    public static final String COLUMN_TIME = "Time_of_Ride";
    public static final String TABLE_LOCATION_DETAILS="LocationDetails";
    private static final String COLUMN_LOCATION_ID="LocationId";
    private static final String COLUMN_PLACE1="Location1";
    private static final String COLUMN_PLACE2="Location2";
    private static final String COLUMN_RATE="Rate";

    public static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_USER_NAME + " TEXT NOT NULL,"
            + COLUMN_USER_EMAIL + " TEXT  NOT NULL PRIMARY KEY,"
            + COLUMN_USER_PASSWORD + " TEXT  NOT NULL,"
            + COLUMN_USER_MOBILE + " INTEGER  NOT NULL,"
            + COLUMN_USER_RESIDENTIAL_ADDRESS + " TEXT  NOT NULL" + ");";


    public static final String CREATE_BOOKING_TABLE = "CREATE TABLE " + TABLE_BOOKING_DETAILS + " ("
            + COLUMN_BOARDING_POINT + " TEXT NOT NULL,"
            + COLUMN_DROPPING_POINT + " TEXT NOT NULL,"
            + COLUMN_DATE + " TEXT NOT NULL,"
            + COLUMN_TIME + " TEXT NOT NULL,"
            + "));";

     public static final String CREATE_TABLE_LOCATIONS= "CREATE TABLE " + TABLE_LOCATION_DETAILS + " ("
             + COLUMN_LOCATION_ID +" INTEGER NOT NULL AUTOINCREMENT PRIMARY KEY"
             + COLUMN_PLACE1 + " TEXT NOT NULL,"
             + COLUMN_PLACE2 + " TEXT NOT NULL,"
             + COLUMN_RATE + " TEXT NOT NULL,"
             + " );";

}
