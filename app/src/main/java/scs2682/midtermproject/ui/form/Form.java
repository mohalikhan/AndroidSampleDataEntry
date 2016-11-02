package scs2682.midtermproject.ui.form;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import scs2682.midtermproject.AppActivity;
import scs2682.midtermproject.R;
import scs2682.midtermproject.data.Contact;

public class Form extends LinearLayout{
    private TextView firstName;
    private TextView lastName;

    private AppActivity.Adapter adapter;
    private int positionInContacts = -1;

    public Form(Context context) {
        this(context, null, 0);
    }

    public Form(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Form(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(AppActivity.Adapter adapter) {
        this.adapter = adapter;
    }

    public void updateContact(Contact contact, int positionInContacts) {
        this.positionInContacts = positionInContacts;

        if (contact != null) {
            // updating a contact
            firstName.setText(contact.firstName);
            lastName.setText(contact.lastName);
        }

        firstName.requestFocus();

        // open the keyboard
        InputMethodManager inputMethodManager
                = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(firstName, 0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);

        //Cancel Click
        findViewById(R.id.cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.onContactUpdated(null, -1);
                firstName.setText("");
                lastName.setText("");
            }
        });

        //Update Click
        findViewById(R.id.update).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstNameValue = firstName.getText().toString();
                String lastNameValue = lastName.getText().toString();

                //Validation
                if (TextUtils.isEmpty(firstNameValue) || TextUtils.isEmpty(lastNameValue)) {
                    Toast.makeText(getContext(), "First and Last Name is mandatory", Toast.LENGTH_SHORT);
                }
                else {
                    Contact contact = new Contact(firstNameValue, lastNameValue);
                    adapter.onContactUpdated(contact, positionInContacts);
                    firstName.setText("");
                    lastName.setText("");
                }
            }
        });
    }
}