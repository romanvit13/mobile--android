package com.vit.roman.roman_vit_app.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.Validation;
import com.vit.roman.roman_vit_app.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String USER_PREF = "USER_PREF";
    private static final String USER_LIST = "USER_LIST";

    @BindView(R.id.edit_text_first_name)
    EditText mFirstNameEdit;
    @BindView(R.id.edit_text_last_name)
    EditText mLastNameEdit;
    @BindView(R.id.edit_text_email)
    EditText mEmailEdit;
    @BindView(R.id.edit_text_phone)
    EditText mPhoneEdit;
    @BindView(R.id.edit_text_password)
    EditText mPasswordEdit;
    @BindView(R.id.edit_text_password_confirm)
    EditText mPasswordConfirmEdit;

    private String mFirstNameText;
    private String mLastNameText;
    private String mEmailText;
    private String mPhoneText;
    private String mPasswordText;
    private String mPasswordConfirmText;

    private SharedPreferences mUserPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_submit, R.id.button_list, R.id.button_cat})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.button_submit:
                getText();
                register();
                break;
            case R.id.button_list:
                Intent intent1 = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(intent1);
                break;
            case R.id.button_cat:
                Intent intent2 = new Intent(MainActivity.this, CatsActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void getText() {
        mFirstNameText = mFirstNameEdit.getText().toString();
        mLastNameText = mLastNameEdit.getText().toString();
        mEmailText = mEmailEdit.getText().toString();
        mPhoneText = mPhoneEdit.getText().toString();
        mPasswordText = mPasswordEdit.getText().toString();
        mPasswordConfirmText = mPasswordConfirmEdit.getText().toString();
    }

    private ArrayList<User> getArrayList() {
        ArrayList<User> userArrayList = new ArrayList<>();
        mUserPref = MainActivity.this.getApplicationContext()
                .getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        if (mUserPref.contains(USER_LIST)) {
            Gson gson = new Gson();
            String json = mUserPref.getString(USER_LIST, null);
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();
            userArrayList = gson.fromJson(json, type);
        }
        return userArrayList;
    }

    private void saveArrayList(ArrayList arrayList) {
        mUserPref = MainActivity.this.getApplicationContext()
                .getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mUserPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(USER_LIST, json);
        editor.apply();
    }

    private void onSignUpSuccess() {
        ArrayList<User> userArrayList = getArrayList();
        int lastId = userArrayList.size() + 1;
        User user = new User(lastId, mFirstNameText, mLastNameText, mPhoneText);

        userArrayList.add(user);
        saveArrayList(userArrayList);
        Toast.makeText(getApplicationContext(),
                R.string.register_success, Toast.LENGTH_SHORT).show();
    }

    private void register() {
        if (!validate()) {
            Toast.makeText(getApplicationContext(), R.string.register_error,
                    Toast.LENGTH_SHORT).show();
        } else {
            onSignUpSuccess();
        }
    }

    public boolean validate() {
        boolean valid = true;
        Validation validation = new Validation();
        if (validation.isNameValid(mFirstNameText)) {
            mFirstNameEdit.setError(getResources().getString(R.string.first_name_valid));
            valid = false;
        }
        if (validation.isNameValid(mLastNameText)) {
            mLastNameEdit.setError(getResources().getString(R.string.last_name_valid));
            valid = false;
        }
        if (!validation.isEmailValid(mEmailText)) {
            mEmailEdit.setError(getResources().getString(R.string.email_valid));
            valid = false;
        }
        if (!validation.isPhoneValid(mPhoneText)) {
            mPhoneEdit.setError(getResources().getString(R.string.phone_valid));
            valid = false;
        }
        if (!validation.isPasswordValid(mPasswordText)
                || !(mPasswordText.equals(mPasswordConfirmText))) {
            mPasswordEdit.setError(getResources().getString(R.string.password_valid));
            mPasswordConfirmEdit.setError(getResources().getString(R.string.password_valid));
            valid = false;

        }
        return valid;
    }
}
