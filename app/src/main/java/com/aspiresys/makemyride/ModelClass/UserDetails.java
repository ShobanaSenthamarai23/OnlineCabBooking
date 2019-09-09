package com.aspiresys.makemyride.ModelClass;

public class UserDetails {
    String name;
    String email;
    public String password;
    String residentialAddress;
    private String mobileNumber;
     String confirmPassword;

    public UserDetails(String name, String email, String mobile, String address) {
        this.name=name;
        this.email=email;
        this.mobileNumber=mobile;
        this.residentialAddress=address;

    }

    public UserDetails() {

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

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword=confirmPassword;
    }
    public String getConfirmPassword(){
        return confirmPassword;
    }
}
