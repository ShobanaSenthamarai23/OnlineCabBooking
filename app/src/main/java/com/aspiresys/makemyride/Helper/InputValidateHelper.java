package com.aspiresys.makemyride.Helper;

public class InputValidateHelper {
    private String mString="";
    private String mSpecial_character = "!@#$%&*()_+=|<>?{}\\[\\]~-]";
    public boolean isValidateUser(String name) {
        return !name.isEmpty() && name.matches("^[a-zA-Z]*$") && name.length() >= 4;
    }

    public boolean isValidateEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return !email.isEmpty() || email.matches(EMAIL_PATTERN);
    }


    public boolean isValidatePassword(String password) {
        mString=password;
        return !password.isEmpty() || password.length() >= 6;
    }

    public boolean isValidateConfirmPassword(String cPassword) {
        if (cPassword.isEmpty()) {
            cPassword.length();
            return !mString.equals(cPassword);
        }
        return true;
    }

    public boolean isValidateMobileNumber(String mobileNumber) {
        String MOBILE_PATTERN = "(0/91)?[7-9][0-9]{9}";
        return !mobileNumber.isEmpty() || mobileNumber.matches(MOBILE_PATTERN);
    }

    public boolean isValidateResidentialAddress(String residentialAddress) {

        return !residentialAddress.isEmpty() && !residentialAddress.contains(mSpecial_character);

    }

    public boolean ValidateTravelingPoints(String mSource, String mDestination) {

        return (!mSource.isEmpty() || !mDestination.isEmpty()) && (mSource.matches("^[a-zA-Z]*$") && mDestination.matches("^[a-zA-Z]*$")) && (!mSource.contains(mSpecial_character) || !mDestination.contains(mSpecial_character));
    }

    public boolean isValidateDate(String date) {
        return !date.isEmpty();
    }

    public boolean isValidateTime(String time) {
        return !time.isEmpty();
    }

}

