package com.example.middleprojectapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
        contactAdapter.setListener(new ContactListener() {
            @Override
            public void onClick(int position) {
                Contact contact = manager.getContacts().get(position);
                Intent intent = new Intent(ContactListActivity.this, ContactDetailsActivity.class);
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
            }

            @Override
            public void onLongClick(int position) {
                ContactManager manager = ContactManager.getInstance(ContactListActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(ContactListActivity.this);
                builder.setTitle(R.string.remove_contact).setMessage(R.string.please_choose).setCancelable(false);
                builder.setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    manager.removeContact(position);
                    contactAdapter.notifyItemRemoved(position);
                });
                builder.setNegativeButton(R.string.no,null);
                builder.show();

            }
        });

        recyclerViewContact.setAdapter(contactAdapter);
    }
}
