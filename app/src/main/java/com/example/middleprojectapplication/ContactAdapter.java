package com.example.middleprojectapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final List<Contact> contacts;
    private ContactListener listener;
    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setListener(ContactListener listener) {
        this.listener = listener;
    }

    public class  ContactViewHolder extends RecyclerView.ViewHolder{
        TextView nameCardTv;
        ImageView imageCardIv;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameCardTv = itemView.findViewById(R.id.name_card);
            imageCardIv = itemView.findViewById(R.id.image_card);
            itemView.setOnClickListener(view -> {
                if (listener !=null){
                    listener.onClick(getAdapterPosition());
                }
            });
        }
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameCardTv.setText(contact.getFullName());
        holder.imageCardIv.setImageBitmap(contact.getBitmap());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
