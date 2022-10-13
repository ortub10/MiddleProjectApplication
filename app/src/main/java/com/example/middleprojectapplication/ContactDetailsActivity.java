package com.example.middleprojectapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactDetailsActivity extends AppCompatActivity {
    int position;
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

        position = getIntent().getIntExtra("position",-1);

        Contact contact = ContactManager.getInstance(this).getContacts().get(position);
        fullNameOutputTv.setText(contact.getFullName());
        phoneNumberOutputTv.setText(contact.getPhoneNumber());
        emailAddressOutputTv.setText(contact.getEmail());
        homeAddressOutputTv.setText(contact.getHomeAddress());
        profileIv.setImageBitmap(contact.getBitmap());
        webAddressOutputTv.setText(contact.getWebAddress());
        dateOfBirthOutputTv.setText(contact.getBirthday());
        preferredHourOutputTv.setText(contact.getTimeToCall());
        preferredDaysOutputTv.setText(contact.getBestDays().toString());

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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detailes_contact_menue,menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.update_contact){
            Intent intent = new Intent(this,AddContactActivity.class);
            intent.putExtra("update","update");
            intent.putExtra("position",position);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
