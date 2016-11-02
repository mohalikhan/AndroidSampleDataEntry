package scs2682.midtermproject.ui.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import scs2682.midtermproject.R;
import scs2682.midtermproject.data.Contact;

public class ContactAdapter extends RecyclerView.Adapter<CellViewHolder> {
    final List<Contact> contacts = new ArrayList<>();

    private final LayoutInflater layoutInflater;
    private final OnContactClickListener onContactClickListener;

    ContactAdapter(Context context, OnContactClickListener onContactClickListener) {
        layoutInflater = LayoutInflater.from(context);
        this.onContactClickListener = onContactClickListener;

        setHasStableIds(true);
    }

    @Override
    public CellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.contacts_cell, parent, false);
        return new CellViewHolder(view, onContactClickListener);
    }

    @Override
    public void onBindViewHolder(CellViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.update(contact, position);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
