package com.aspiresys.makemyride.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aspiresys.makemyride.Activities.UserAuthenticationActivity;
import com.aspiresys.makemyride.Helper.DatabaseHelper;
import com.aspiresys.makemyride.Helper.InputValidateHelper;
import com.aspiresys.makemyride.R;

import java.util.Objects;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class ForgotPasswordFragment extends Fragment implements View.OnClickListener {
    private EditText mNewPassword, mConfirmPassword, mEmail;;
    private View mView;
    private Button button_ChangePassword;
    private DatabaseHelper databaseHelper;
    private InputValidateHelper inputValidateHelper;
    private String mConfirmPass,mEmailId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.forgot_password_fragment, container, false);
        initViews();
        button_ChangePassword.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(getContext());
        inputValidateHelper = new InputValidateHelper();
        getActivity().setTitle("Reset password");
        return mView;
    }

    private void initViews() {
        mEmail = mView.findViewById(R.id.email_id);
        button_ChangePassword = mView.findViewById(R.id.button_change_password);
        mNewPassword = mView.findViewById(R.id.new_password1);
        mConfirmPassword = mView.findViewById(R.id.new_password2);
    }

    private boolean getViewInputs() {
        int flag = 0;
        String mNewPass = mNewPassword.getText().toString().trim();
        if (!inputValidateHelper.isValidatePassword(mNewPass)) {
            mNewPassword.setError("Enter a valid password");
            flag = 1;
        }

        mConfirmPass = mConfirmPassword.getText().toString().trim();
        if (!inputValidateHelper.isValidateConfirmPassword(mConfirmPass)) {
            mConfirmPassword.setError("Enter a valid password");
            flag = 1;
        }
        mEmailId = mEmail.getText().toString().trim();
        if ( mEmailId.isEmpty()&& !inputValidateHelper.isValidateEmail(mEmailId)) {
            Toast.makeText(getActivity(), "Please fill the TextBox correctly", Toast.LENGTH_SHORT).show();
            flag=1;
        }

        if (mNewPass.equals(mConfirmPass)) {
            flag = 0;
        }
        return flag == 0;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_change_password) {
            if (getViewInputs()) {
                if (databaseHelper.emailExists(mEmailId)) {
                    Log.d("hi", mConfirmPass);
                    boolean isUpdated = databaseHelper.updatedPassword(mEmailId, mConfirmPass);
                    if (isUpdated) {
                        makeText(getActivity(), "Password changed Successfully...for ur account", LENGTH_SHORT).show();
                        ((UserAuthenticationActivity) getActivity()).replaceFragment(new LoginFragment());
                         emptyInputEditText();
                    }
                } else {
                    makeText(getActivity(), "Email....doesn't exists", LENGTH_SHORT).show();
                }
            }
        }
    }
    private void emptyInputEditText() {
        mEmail.setText(null);
        mConfirmPassword.setText(null);
        mNewPassword.setText(null);
    }
}

