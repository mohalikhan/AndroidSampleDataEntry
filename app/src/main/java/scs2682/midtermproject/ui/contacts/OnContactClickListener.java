package scs2682.midtermproject.ui.contacts;

import android.support.annotation.NonNull;

import scs2682.midtermproject.data.Contact;

public interface OnContactClickListener {
    void onContactClick(@NonNull Contact contact, int positionInContacts);
}


