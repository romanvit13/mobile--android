package com.vit.roman.roman_vit_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    private String nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        ImageButton clearButton = findViewById(R.id.clear_button);
        final MaterialEditText materialEditText = findViewById(R.id.materialEditText);
        final TextView nameTextView = findViewById(R.id.name_text);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameText = materialEditText.getText().toString();
                nameTextView.setText(nameText);
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialEditText.setText("");
            }
        });

    }
}
