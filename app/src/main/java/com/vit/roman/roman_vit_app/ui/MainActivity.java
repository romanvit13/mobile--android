package com.vit.roman.roman_vit_app.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vit.roman.roman_vit_app.R;
import com.vit.roman.roman_vit_app.Validation;
import com.vit.roman.roman_vit_app.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String USER_PREF = "USER_PREF";
    private static final String USER_LIST = "USER_LIST";

    private EditText mFirstNameEdit;
    private EditText mLastNameEdit;
    private EditText mEmailEdit;
    private EditText mPhoneEdit;
    private EditText mPasswordEdit;
    private EditText mPasswordConfirmEdit;

    private String mFirstNameText;
    private String mLastNameText;
    private String mEmailText;
    private String mPhoneText;
    private String mPasswordText;
    private String mPasswordConfirmText;

    private Button mSubmitButton;
    private Button mListButton;
    
    private SharedPreferences mUserPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();
                register();
            }
        });

        mListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        mSubmitButton = findViewById(R.id.submit_button);
        mListButton = findViewById(R.id.list_button);
        mFirstNameEdit = findViewById(R.id.firstNameEditText);
        mLastNameEdit = findViewById(R.id.lastNameEditText);
        mEmailEdit = findViewById(R.id.emailEditText);
        mPhoneEdit = findViewById(R.id.phoneEditText);
        mPasswordEdit = findViewById(R.id.passwordEditText);
        mPasswordConfirmEdit = findViewById(R.id.passwordConfirmEditText);
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
            Toast.makeText(getApplicationContext(), "Registration has failed!",
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
