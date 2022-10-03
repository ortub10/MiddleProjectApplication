package com.example.middleprojectapplication;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ContactManager {

    @SuppressLint("StaticFieldLeak")
    private  static ContactManager instance;
    private final Context context;
    private ArrayList<Contact> contacts = new ArrayList<>();

    static final String FILE_NAME = "contacts";

    private ContactManager(Context context) {
        this.context = context;

        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            ObjectInputStream ois  = new ObjectInputStream(fis);
            contacts = (ArrayList<Contact>)ois.readObject();

            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static ContactManager getInstance(Context context) {
        if(instance==null)
            instance = new ContactManager(context);

        return instance;
    }

    public void addContact(Contact contact) {

        contacts.add(contact);
        saveContacts();
    }

    private void saveContacts() {

        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME,Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(contacts);

            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
