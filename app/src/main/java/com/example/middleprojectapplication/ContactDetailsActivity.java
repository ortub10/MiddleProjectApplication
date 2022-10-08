package com.example.middleprojectapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {
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

        phoneNumberOutputTv.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+phoneNumberOutputTv.getText().toString()));
            startActivity(intent);
        });

        emailAddressOutputTv.setOnClickListener(view -> {
            String address = emailAddressOutputTv.getText().toString();
            Intent intent  = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "This is the email body");
            intent.putExtra(Intent.EXTRA_SUBJECT,"This is the email subject");
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{address});
            intent.setType("text/html");
            startActivity(intent);
        });

        homeAddressOutputTv.setOnClickListener(view -> {
            try {
                String url = "https://waze.com/ul?q="+homeAddressOutputTv.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                // If Waze is not installed, open it in Google Play:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
                startActivity(intent);
            }
        });

        webAddressOutputTv.setOnClickListener(view -> {
            String site = webAddressOutputTv.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+site));
            startActivity(intent);
        });
    }
}
