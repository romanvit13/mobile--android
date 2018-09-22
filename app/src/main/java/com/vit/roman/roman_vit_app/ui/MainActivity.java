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
    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText emailEdit;
    private EditText phoneEdit;
    private EditText passwordEdit;
    private EditText passwordConfirmEdit;

    private String firstNameText;
    private String lastNameText;
    private String emailText;
    private String phoneText;
    private String passwordText;
    private String passwordConfirmText;

    private SharedPreferences mUserPref;

    private Button submitButton, listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initText();
                register();
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });


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

    private void initViews() {
        submitButton = findViewById(R.id.submit_button);
        listButton = findViewById(R.id.list_button);
        firstNameEdit = findViewById(R.id.firstNameEditText);
        lastNameEdit = findViewById(R.id.lastNameEditText);
        emailEdit = findViewById(R.id.emailEditText);
        phoneEdit = findViewById(R.id.phoneEditText);
        passwordEdit = findViewById(R.id.passwordEditText);
        passwordConfirmEdit = findViewById(R.id.passwordConfirmEditText);
    }

    private void initText() {
        firstNameText = firstNameEdit.getText().toString();
        lastNameText = lastNameEdit.getText().toString();
        emailText = emailEdit.getText().toString();
        phoneText = phoneEdit.getText().toString();
        passwordText = passwordEdit.getText().toString();
        passwordConfirmText = passwordConfirmEdit.getText().toString();
    }

    private void register() {
        if (!validate()) {
            Toast.makeText(getApplicationContext(), "Registration has failed!",
                    Toast.LENGTH_SHORT).show();
        } else {
            onSignUpSuccess();
        }
    }

    private void onSignUpSuccess() {
        ArrayList<User> userArrayList = getArrayList();
        int lastId = userArrayList.size() + 1;
        User user = new User(lastId, firstNameText, lastNameText, phoneText);

        userArrayList.add(user);
        saveArrayList(userArrayList);
        Toast.makeText(getApplicationContext(), "You have been registered!", Toast.LENGTH_SHORT).show();
    }

    public boolean validate() {
        boolean valid = true;
        if (!Validation.isNameValid(firstNameText)) {
            firstNameEdit.setError("Please, enter valid first name");
            valid = false;
        }
        if (!Validation.isNameValid(lastNameText)) {
            lastNameEdit.setError("Please, enter valid last name");
            valid = false;
        }
        if (!Validation.isEmailValid(emailText)) {
            emailEdit.setError("Please, enter valid email");
            valid = false;
        }
        if (!Validation.isPhoneValid(phoneText)) {
            phoneEdit.setError("Please, enter valid phone");
            valid = false;
        }
        if (!Validation.isPasswordValid(passwordText) || !(passwordText.equals(passwordConfirmText))) {
            passwordEdit.setError("Please, enter valid password");
            passwordConfirmEdit.setError("Please, enter valid password");
            valid = false;

        }
        return valid;
    }
}
