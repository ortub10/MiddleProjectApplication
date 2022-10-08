package com.example.middleprojectapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ContactListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list_layout);

        RecyclerView recyclerViewContact = findViewById(R.id.contact_list_recycle_view);
        recyclerViewContact.setHasFixedSize(true);
        recyclerViewContact.setLayoutManager(new LinearLayoutManager(this));

        ContactManager manager = ContactManager.getInstance(this);
        ContactAdapter contactAdapter = new ContactAdapter(manager.getContacts());
        contactAdapter.setListener(position -> {
            Contact contact = manager.getContacts().get(position);
            Intent intent = new Intent(this, ContactDetailsActivity.class);
            intent.putExtra("full_name",contact.getFullName());
            intent.putExtra("phone_number",contact.getPhoneNumber());
            intent.putExtra("email",contact.getEmail());
            intent.putExtra("home_address",contact.getHomeAddress());
            intent.putExtra("image",contact.getBitmap());
            intent.putExtra("web_site",contact.getWebAddress());
            intent.putExtra("birth_day",contact.getBirthday());
            intent.putExtra("time_call",contact.getTimeToCall());
            intent.putExtra("best_days",contact.getBestDays());
            startActivity(intent);
        });
        recyclerViewContact.setAdapter(contactAdapter);
    }
}
