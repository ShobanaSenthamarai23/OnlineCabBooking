package com.aspiresys.makemyride.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aspiresys.makemyride.Activities.MainActivity;
import com.aspiresys.makemyride.Activities.UserAuthenticationActivity;
import com.aspiresys.makemyride.Helper.DatabaseHelper;
import com.aspiresys.makemyride.Helper.InputValidateHelper;
import com.aspiresys.makemyride.Helper.SharedPreferenceManager;
import com.aspiresys.makemyride.ModelClass.UserDetails;
import com.aspiresys.makemyride.R;

import java.util.Objects;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

/*
 * In Login fragment user performs login action if he/she is an authenticated user,
 * shared preference is used to check whether user is logged in to the account*/
public class LoginFragment extends Fragment implements View.OnClickListener {
    private View mView;
    private EditText mEmail, mPassword;
    private Button mLoginButton;
    private String email, password;
    private TextView linkRegister, linkForgotPassword;
    private InputValidateHelper inputValidateHelper;
    private DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
    private UserDetails user = new UserDetails();
//    private SharedPreferences mSharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.login_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        initViews();
        mLoginButton.setOnClickListener(this);
        linkRegister.setOnClickListener(this);
        linkForgotPassword.setOnClickListener(this);
//        mSharedPreferences = Objects.requireNonNull(getContext()).getSharedPreferences("login", MODE_PRIVATE);
        databaseHelper = new DatabaseHelper(getActivity());
        user = new UserDetails();
        inputValidateHelper = new InputValidateHelper();

        return mView;
    }

    private void initViews() {
        mEmail = mView.findViewById(R.id.email);
        mPassword = mView.findViewById(R.id.password);
        mLoginButton = mView.findViewById(R.id.button_login);
        linkRegister = mView.findViewById(R.id.register);
        linkForgotPassword = mView.findViewById(R.id.forgot_password);

    }

    private boolean getViewInputs() {
        int flag = 0;
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
        return flag == 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                if (getViewInputs()) {
                    user.setEmail(email);
                    user.setPassword(password);
                    Log.d("tag", password);
                    boolean res = databaseHelper.checkUser(user);
                    if (res) {
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        saveLoginDetails(email);
                        emptyInputEditText();
                        makeText(getContext(), "***User Logged in Successfully..!*** ", LENGTH_SHORT).show();
                        startActivity(intent);
                        getActivity().finish();
//                        mSharedPreferences.edit().putBoolean("logged", true).apply();
                    } else {
                        mPassword.setError("Enter a valid password");
                        makeText(getActivity(), "OOPS!!, Username and Password didn't match!", LENGTH_SHORT).show();

                    }
                } else {
                    mEmail.setError("Enter a valid Email");
                    mPassword.setError("Enter a valid password");
                    makeText(getActivity(), "UnSuccessful LoginFragment!", LENGTH_SHORT).show();
                    emptyInputEditText();
                }
                break;
            case R.id.register:
                ((UserAuthenticationActivity) Objects.requireNonNull(getActivity())).replaceFragment(new RegisterFragment());
                break;
            case R.id.forgot_password:
                ((UserAuthenticationActivity) Objects.requireNonNull(getActivity())).replaceFragment(new ForgotPasswordFragment());
                break;
            default:
                ((UserAuthenticationActivity) Objects.requireNonNull(getActivity())).replaceFragment(new LoginFragment());
        }
    }

    private void saveLoginDetails(String email) {
        new SharedPreferenceManager(getActivity()).saveLoginDetails(email);
    }

    private void emptyInputEditText() {
        mEmail.setText(null);
        mPassword.setText(null);
    }
}