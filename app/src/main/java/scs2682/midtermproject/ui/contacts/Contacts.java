package scs2682.midtermproject.ui.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import scs2682.midtermproject.AppActivity;
import scs2682.midtermproject.R;
import scs2682.midtermproject.data.Contact;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Contacts extends LinearLayout implements OnContactClickListener{
    private AppActivity.Adapter adapter;

    private final List<Contact> contacts = new ArrayList<>();

    private final ContactAdapter contactsAdapter;

    public Contacts(Context context) {
        this(context, null, 0);
    }

    public Contacts(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Contacts(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        contactsAdapter = new ContactAdapter(context, this);
    }

    public void updateContact(Contact contact, int positionInContacts) {
        //hide the keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        if (contact != null) {
            if (positionInContacts > -1)
            {
                //we are update an already existing Contact in contacts
                contactsAdapter.contacts.set(positionInContacts, contact);
                contactsAdapter.notifyItemChanged(positionInContacts);
            }
            else
            {
                // -1 means add a new contact at the end of the list
                contactsAdapter.contacts.add(contact);
                contactsAdapter.notifyItemChanged(contactsAdapter.contacts.size() - 1);
            }
        }
    }

    public void setAdapter(AppActivity.Adapter adapter){
        this.adapter = adapter;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        findViewById(R.id.add).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.onOpenForm(null, -1);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactsAdapter);
    }

    @Override
    public void onContactClick(@NonNull Contact contact, int positionInContacts) {
        if (adapter != null){
            adapter.onOpenForm(contact, positionInContacts);
        }
    }

    /*private void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager
                = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }*/
}
