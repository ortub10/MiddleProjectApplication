package com.example.middleprojectapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details_layout);
        TextView fullNameOutputTv = findViewById(R.id.full_name_output);
        TextView phoneNumberOutputTv = findViewById(R.id.number_phone_output);
        TextView emailAddressOutputTv = findViewById(R.id.email_address_output);
        TextView homeAddressOutputTv = findViewById(R.id.home_address_output);
        ImageView profileIv = findViewById(R.id.image_output);
        TextView webAddressOutputTv = findViewById(R.id.web_address_output);
        TextView dateOfBirthOutputTv = findViewById(R.id.birth_date_output);
        TextView preferredHourOutputTv = findViewById(R.id.time_to_call_output);
        TextView preferredDaysOutputTv = findViewById(R.id.best_days_output);

        fullNameOutputTv.setText(getIntent().getStringExtra("full_name"));
        phoneNumberOutputTv.setText(getIntent().getStringExtra("phone_number"));
        emailAddressOutputTv.setText(getIntent().getStringExtra("email"));
        homeAddressOutputTv.setText(getIntent().getStringExtra("home_address"));
        profileIv.setImageBitmap(getIntent().getParcelableExtra("image"));
        webAddressOutputTv.setText(getIntent().getStringExtra("web_site"));
        dateOfBirthOutputTv.setText(getIntent().getStringExtra("birth_day"));
        preferredHourOutputTv.setText(getIntent().getStringExtra("time_call"));
        preferredDaysOutputTv.setText(getIntent().getStringArrayListExtra("best_days").toString());
    }
}
