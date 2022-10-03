package com.example.middleprojectapplication;

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
        recyclerViewContact.setAdapter(contactAdapter);
    }
}
