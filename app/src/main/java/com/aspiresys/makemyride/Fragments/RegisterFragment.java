package com.aspiresys.makemyride.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aspiresys.makemyride.Activities.UserAuthenticationActivity;
import com.aspiresys.makemyride.Helper.DatabaseHelper;
import com.aspiresys.makemyride.Helper.InputValidateHelper;
import com.aspiresys.makemyride.Helper.SharedPreferenceManager;
import com.aspiresys.makemyride.ModelClass.UserDetails;
import com.aspiresys.makemyride.R;

import java.util.ArrayList;
import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class RegisterFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private EditText mName, mEmail, mPassword, mMobileNumber, mResidentialAddress, mConfirmPassword;
    private Button mRegisterButton;
    private String name, email, password, confirmPassword, residentialAddress, mobileNumber;
    private DatabaseHelper databaseHelper;
    private UserDetails user;
    private InputValidateHelper inputValidateHelper;
    private TextView mLinkLogin;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.register_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initViews();
        mRegisterButton.setOnClickListener(this);
        mLinkLogin.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(getContext());
        user = new UserDetails();
        inputValidateHelper = new InputValidateHelper();
        return mView;
    }

    private void initViews() {
        mName = mView.findViewById(R.id.name);
        mEmail = mView.findViewById(R.id.email);
        mPassword = mView.findViewById(R.id.password);
        mConfirmPassword = mView.findViewById(R.id.confirm_password);
        mMobileNumber = mView.findViewById(R.id.mobile);
        mResidentialAddress = mView.findViewById(R.id.residential_address);
        mRegisterButton = mView.findViewById(R.id.button_register);
        mLinkLogin = mView.findViewById(R.id.loginFragment);

    }

    private boolean getViewInputs() {
        int flag = 0;
        name = mName.getText().toString().trim();
        if (!inputValidateHelper.isValidateUser(name)) {
            mName.setError("Enter a valid Name");
            flag = 1;
        }
        email = mEmail.getText().toString().trim();
        if (!inputValidateHelper.isValidateEmail(email)) {
            mEmail.setError("Enter a valid Email");
            flag = 1;
        }
        password = mPassword.getText().toString().trim();
        if (!inputValidateHelper.isValidatePassword(password)) {
            mPassword.setError("Enter a valid password");
            flag = 1;
        }
        confirmPassword = mConfirmPassword.getText().toString().trim();
        if (!inputValidateHelper.isValidateConfirmPassword(confirmPassword)) {
            mConfirmPassword.setError("Enter a valid password");
            flag = 1;
        }
        mobileNumber = mMobileNumber.getText().toString().trim();
        if (!inputValidateHelper.isValidateMobileNumber(mobileNumber)) {
            mMobileNumber.setError("Enter a valid mobile number");
            flag = 1;
        }
        residentialAddress = mResidentialAddress.getText().toString().trim();
        if (!inputValidateHelper.isValidateResidentialAddress(residentialAddress)) {
            mResidentialAddress.setError("Enter a valid Address ");
            flag = 1;
        }
        return flag == 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_register:
                if (getViewInputs()) {
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setConfirmPassword(confirmPassword);
                    user.setMobilenumber(mobileNumber);
                    user.setResidentialAddress(residentialAddress);
//                    saveUserDetail(name,email,mobileNumber,residentialAddress);
                    boolean isInserted = databaseHelper.insertUserDetails(user);
                    if (isInserted) {
                        makeText(getActivity(), "Registered Successfully! Account Created ..", LENGTH_SHORT).show();
                        emptyInputEditText();
                        if (getActivity() != null) {
                            ((UserAuthenticationActivity) getActivity()).replaceFragment(new LoginFragment());
                        }
                    }
                } else {
                    makeText(getActivity(), "Not Registered.. Please fill out the fields correctly!", LENGTH_SHORT).show();
                    emptyInputEditText();
                }
                break;
            case R.id.loginFragment:
                ((UserAuthenticationActivity) Objects.requireNonNull(getActivity())).replaceFragment(new LoginFragment());
                break;
            default:
                makeText(getActivity(), " Registration failed!!!! ", LENGTH_SHORT).show();
        }
    }

//    private void saveUserDetail(String name, String email, String mobileNumber, String residentialAddress) {
//        new SharedPreferenceManager(getActivity()).saveUserDetails(name,email,mobileNumber,residentialAddress);
//    }

    private void emptyInputEditText() {
        mName.setText(null);
        mEmail.setText(null);
        mPassword.setText(null);
        mConfirmPassword.setText(null);
        mMobileNumber.setText(null);
        mResidentialAddress.setText(null);
    }
}
