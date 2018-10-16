package com.vit.roman.roman_vit_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vit.roman.roman_vit_app.model.User;


public class MainActivity extends AppCompatActivity {

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
    private String errorText;

    private Button submitButton;

    //private TextView errorList;

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
    }

    private void initViews() {
        submitButton = findViewById(R.id.button);
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
        errorText = "";
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
        Intent intent = new Intent(MainActivity.this, RegisteredActivity.class);
        User user = new User(1, firstNameText, lastNameText, phoneText);
        UserPref userPref = new UserPref(MainActivity.this);
        userPref.putUser(user);
        Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public boolean validate() {
        boolean valid = true;
        if (!Validation.isNameValid(firstNameText)) {
            firstNameEdit.setError("Please, enter valid first name");
            errorText += "Invalid firstName";
            valid = false;
        }
        if (!Validation.isNameValid(lastNameText)) {
            lastNameEdit.setError("Please, enter valid last name");
            errorText += "Invalid lastName";
            valid = false;
        }
        if (!Validation.isEmailValid(emailText)) {
            emailEdit.setError("Please, enter valid email");
            errorText += "Invalid email";
            valid = false;
        }
        if (!Validation.isPhoneValid(phoneText)) {
            phoneEdit.setError("Please, enter valid phone");
            valid = false;
        }
        if (!Validation.isPasswordValid(passwordText) || !(passwordText.equals(passwordConfirmText))) {
            passwordEdit.setError("Please, enter valid password");
            passwordConfirmEdit.setError("Please, enter valid password");
            errorText += "Invalid password";
            valid = false;
        }
        return valid;
    }
}
