package com.example.middleprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addContactBtn = findViewById(R.id.add_contact_btn);
        addContactBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            intent.putExtra("update","add");
            startActivity(intent);
        });
        Button showContactBtn = findViewById(R.id.show_contact_btn);
        showContactBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this,ContactListActivity.class);
            startActivity(intent);
        });
    }
}