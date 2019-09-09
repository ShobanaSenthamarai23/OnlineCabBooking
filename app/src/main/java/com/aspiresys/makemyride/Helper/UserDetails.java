package com.aspiresys.makemyride.Helper;

public class UserDetails {
    String name;
    String email;
    public String password;
    String residentialAddress;
    private String mobileNumber;

    public UserDetails(String user_name, String user_email, String user_mobileNumber, String user_address) {
        this.name=user_name;
        this.email=user_email;
        this.mobileNumber=user_mobileNumber;
        this.residentialAddress=user_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public String getMobilenumber() {
        return mobileNumber;
    }

    public void setMobilenumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
