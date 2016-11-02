package scs2682.midtermproject.ui.contacts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import scs2682.midtermproject.R;
import scs2682.midtermproject.data.Contact;

public class CellViewHolder extends RecyclerView.ViewHolder {
    private final TextView firstName;
    private final TextView lastName;

    private int positionInContacts = -1;

    public CellViewHolder(View view, final OnContactClickListener onContactClickListener) {
        super(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (onContactClickListener != null) {
                    Contact contact = new Contact(firstName.getText().toString(), lastName.getText().toString());
                    onContactClickListener.onContactClick(contact, positionInContacts);
                }
            }
        });

        firstName = (TextView) view.findViewById(R.id.firstName);
        lastName = (TextView) view.findViewById(R.id.lastName);
    }

    public void update(Contact contact, int positionInContacts) {
        firstName.setText(contact.firstName);
        lastName.setText(contact.lastName);
        this.positionInContacts = positionInContacts;
    }
}
